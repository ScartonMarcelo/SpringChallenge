package br.com.meli.service;

import br.com.meli.dto.ArticlesDTO;
import br.com.meli.dto.ArticlesPurchaseDTO;
import br.com.meli.entity.Articles;
import br.com.meli.entity.ArticlesPurchase;
import br.com.meli.entity.Carrinho;
import br.com.meli.entity.Produto;
import br.com.meli.entity.Ticket;
import br.com.meli.repository.ArticleRepository;
import br.com.meli.response.PurchaseResponse;
import br.com.meli.util.OrdenadorProdutos;
import br.com.meli.util.OrdenadorProdutos.Filtro;
import br.com.meli.util.OrdenadorProdutos.Ordenador;

import java.net.URI;
import java.util.List;

import exception.ResponseEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class ArticlesService {

	@Autowired
	private ArticleRepository articlesRepository;

	/**
	 * Chama função para serializar Produtos em JSON
	 *
	 * @author Marcelo Scarton
	 * @throws ResponseEntityException
	 * @param articles
	 */
	public void salvarProdutos(Articles articles) {
		List<Produto> novosProdutos = articles.getArticles();
		List<Produto> produtosExistentes = articlesRepository.desserializaProdutos();
		for (Produto p : novosProdutos) {
			Produto produto = produtosExistentes.stream().filter(x -> x.getProductId().equals(p.getProductId()))
					.findAny().orElse(null);
			if (produto != null) {
				throw new ResponseEntityException("O produto com o id " + p.getProductId() + " já existe.", "400");
			}
		}
		produtosExistentes.addAll(articles.getArticles());
		articlesRepository.serializaProdutos(produtosExistentes);
	}

	/**
	 * Retorna lista de Produtos solicitados para compra
	 *
	 * @author Marcelo Scarton
	 * @param articlesPurchaseList
	 * @throws ResponseEntityException
	 * @return List
	 */
	private List<Produto> retornarProdutosPurchase(ArticlesPurchase articlesPurchaseList) {
		List<Produto> produtos = articlesRepository.desserializaProdutos();
		List<Produto> purchaseList = Carrinho.produtos;
		for (ArticlesPurchaseDTO a : articlesPurchaseList.getArticlesPurchaseRequest()) {
			if (a.getQuantity() <= 0)
				throw new ResponseEntityException("Quantidade a ser comprada deve ser maior que 0", "400");
			Produto produto = produtos.stream().filter(p -> p.getProductId().equals(a.getProductId())).findAny()
					.orElse(null);
			if (produto == null) {
				throw new ResponseEntityException("O produto com o id " + a.getProductId() + " não existe.", "400");
			}
			if (!produto.getName().equalsIgnoreCase(a.getName())) {
				throw new ResponseEntityException("O produto nome " + a.getName()
						+ " não corresponde ao nome do produto com id " + produto.getProductId(), "400");
			}
			if (produto.getQuantity() < a.getQuantity()) {
				throw new ResponseEntityException(
						"Não há estoque suficiente para o produto " + a.getName() + ", " +
								"a quantidade atual é de " + produto.getQuantity() + " unidades(s).",
						"400");
			}
			Produto produtoCarrinho = purchaseList.stream().filter(p -> p.getProductId().equals(produto.getProductId()))
					.findAny()
					.orElse(null);
			if (produtoCarrinho == null) {
				produto.setQuantity(a.getQuantity());
				purchaseList.add(produto);
			} else {
				produtoCarrinho.setQuantity(produtoCarrinho.getQuantity() + a.getQuantity());
			}
		}
		return purchaseList;
	}

	/**
	 * Retorna valor total da lista de Produtos solicitados para compra
	 *
	 * @author Marcelo Scarton
	 * @param articles
	 * @return BigDecimal
	 */
	private BigDecimal retornarTotalPurchase(List<Produto> articles) {
		BigDecimal total = new BigDecimal("0");
		for (Produto p : articles) {
			total = total.add(p.getPrice().multiply(BigDecimal.valueOf(p.getQuantity())));
		}
		return total;
	}

	/**
	 * Retorna todos os produtos cadastrados
	 *
	 * @author Francisco Alves
	 * @return List
	 */
	public List<Produto> getProdutos() {
		return articlesRepository.desserializaProdutos();
	}

	/**
	 * Condicionais para filtros & ordenadores
	 *
	 * @author André Arroxellas
	 * @param categoryName
	 * @param productName
	 * @param brandName
	 * @param freeShipping
	 * @param orderFilter
	 * @throws ResponseEntityException
	 * @return List<Produto>
	 * @see OrdenadorProdutos
	 */
	public List<Produto> trateRequestQuery(String categoryName, String productName,
			String brandName, Boolean freeShipping, Integer orderFilter) {

		List<Produto> listaProdutos = articlesRepository.desserializaProdutos();

		/*
		 * OrdenadorValues()
		 * [ORDENA_ALFABETICAMENTE_CRESCENTE, ORDENA_ALFABETICAMENTE_DECRESCENTE,
		 * ORDENA_MAIOR_PRECO, ORDENA_MENOR_PRECO]
		 */
		if (orderFilter != null) {
			if (orderFilter <= 3 && orderFilter >= 0) {

				listaProdutos = OrdenadorProdutos.odernarProdutos(
						listaProdutos, Ordenador.values()[orderFilter]);
			} else {
				throw new ResponseEntityException("param order aceita 0 a 3 como entrada", "400");
			}
		}
		if (freeShipping != null) {
			if (freeShipping.equals(true)) {
				listaProdutos = OrdenadorProdutos.filtrarProdutos(
						listaProdutos, null, Filtro.FILTRA_FREE_SHIPPING);
			} else if (freeShipping.equals(false)) {
				listaProdutos = OrdenadorProdutos.filtrarProdutos(
						listaProdutos, null, Filtro.FILTRA_NON_FREE_SHIPPING);
			} else {
				throw new ResponseEntityException("param order aceita 0 a 3 como entrada", "400");
			}
		}
		if (categoryName != null) {
			listaProdutos = OrdenadorProdutos.filtrarProdutos(
					listaProdutos, categoryName.trim(), Filtro.FILTRA_CATEGORY_NAME);
		}
		if (productName != null) {
			listaProdutos = OrdenadorProdutos.filtrarProdutos(
					listaProdutos, productName.trim(), Filtro.FILTRA_PRODUCT_NAME);
		}
		if (brandName != null) {
			listaProdutos = OrdenadorProdutos.filtrarProdutos(
					listaProdutos, brandName.trim(), Filtro.FILTRA_BRAND_NAME);
		}
		return listaProdutos;
	}

	/**
	 * Cadastra produtos informados no payload
	 *
	 * @author Thomaz Ferreira
	 * @param articles
	 * @param uri
	 * @throws ResponseEntityException
	 * @return ResponseEntity
	 */
	public ResponseEntity<ArticlesDTO> cadastraProdutos(Articles articles, URI uri) {
		if (articles.getArticles().size() == 0) {
			throw new ResponseEntityException("Não existe nenhum produto na lista", "400");
		}
		validaJsonCadastroProdutos(articles);
		articlesRepository.serializaProdutos(articles.getArticles());
		return ResponseEntity.created(uri).body(ArticlesDTO.converte(articles));
	}

	/**
	 * Valida JSON de cadastro de produtos
	 *
	 * @author Thomaz Ferreira
	 * @param articles
	 * @throws ResponseEntityException
	 */
	private void validaJsonCadastroProdutos(Articles articles) {

		ArrayList tmpId = new ArrayList();

		for (Produto p : articles.getArticles()) {
			if (p.getProductId() == null || p.getProductId().equals(""))
				throw new ResponseEntityException("O valor do atributo productId não foi informado ou é nulo", "400");

			if (tmpId.contains(p.getProductId()))
				throw new ResponseEntityException("Não é possível cadastrar mais de 1 produto com o mesmo Id", "400");
			tmpId.add(p.getProductId());

			if (p.getName() == null || p.getName().equals(""))
				throw new ResponseEntityException("O valor do atributo name não foi informado ou é nulo", "400");
			if (p.getCategory() == null || p.getCategory().equals(""))
				throw new ResponseEntityException("O valor do atributo category não foi informado ou é nulo", "400");
			if (p.getBrand() == null || p.getBrand().equals(""))
				throw new ResponseEntityException("O valor do atributo brand não foi informado ou é nulo", "400");
			if (p.getPrice() == null || p.getPrice().equals(""))
				throw new ResponseEntityException("O valor do atributo price não foi informado ou é nulo", "400");
			if (p.getQuantity() == null || p.getQuantity().equals(""))
				throw new ResponseEntityException("O valor do atributo quantity não foi informado ou é nulo", "400");
			if (p.getQuantity() <= 0)
				throw new ResponseEntityException("A quantidade a ser cadastrada deve ser maior que 0", "400");
			if (p.getFreeShipping() == null)
				throw new ResponseEntityException("O valor do atributo freeShipping não foi informado ou é nulo",
						"400");
			if (p.getPrestige() == null || p.getPrestige().equals(""))
				throw new ResponseEntityException("O valor do atributo prestige não foi informado ou é nulo", "400");
			else {
				for (int i = 0; i < p.getPrestige().length(); i++) {
					if (p.getPrestige().charAt(i) != '*')
						throw new ResponseEntityException("O atributo prestige deve aceitar apenas *", "400");
				}
			}
		}
	}

	/**
	 * Função orquestradora do carrinho
	 *
	 * @author Marcelo Scarton, Francisco Alves
	 * @param articlesPurchaseList
	 * @param uri
	 * @return ResponseEntity
	 */
	public ResponseEntity<PurchaseResponse> adicionaCarrinho(ArticlesPurchase articlesPurchaseList, URI uri) {
		List<Produto> articles = this.retornarProdutosPurchase(articlesPurchaseList);
		this.validaEstoque(articlesPurchaseList);
		BigDecimal total = this.retornarTotalPurchase(articles);
		Ticket ticket = Ticket.builder().Id((long) 530).articles(articles).total(total).build();
		return ResponseEntity.created(uri).body(PurchaseResponse.builder().ticket(ticket).build());
	}

	/**
	 * Metodo que faz o controle e validação de estoque
	 *
	 * @author Francisco Alves , Thomaz Ferreira
	 * @param articlesPurchase
	 * @throws ResponseEntityException
	 */
	private void validaEstoque(ArticlesPurchase articlesPurchase) {
		ArrayList<Produto> produtos = new ArrayList<Produto>(articlesRepository.desserializaProdutos());
		ArrayList<ArticlesPurchaseDTO> listaArticlesPuschase = new ArrayList<ArticlesPurchaseDTO>();

		for (ArticlesPurchaseDTO dto : articlesPurchase.getArticlesPurchaseRequest()) {
			listaArticlesPuschase.add(dto);
		}

		for (Produto p : produtos) {
			for (ArticlesPurchaseDTO apd : listaArticlesPuschase) {
				if (apd.getQuantity() <= 0)
					throw new ResponseEntityException("Quantidade a ser comprada deve ser maior que 0", "400");
				if (p.getProductId().equals(apd.getProductId())) {
					if (p.getQuantity() >= apd.getQuantity() && p.getQuantity() > 0) {
						p.setQuantity(p.getQuantity() - apd.getQuantity());
						break;
					} else {
						throw new ResponseEntityException("Quantidade indisponivel", "400");
					}
				}
			}
		}
		articlesRepository.serializaProdutos(produtos);
	}
}

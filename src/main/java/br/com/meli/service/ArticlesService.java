package br.com.meli.service;

import br.com.meli.dto.ArticlesDTO;
import br.com.meli.dto.ArticlesPurchaseDTO;
import br.com.meli.entity.Articles;
import br.com.meli.entity.ArticlesPurchase;
import br.com.meli.entity.Produto;
import br.com.meli.repository.ArticleRepository;
import br.com.meli.util.ResponseEntityErrorsUtils;
import br.com.meli.util.OrdenadorProdutos;
import br.com.meli.util.OrdenadorProdutos.Filtro;
import br.com.meli.util.OrdenadorProdutos.Ordenador;
import br.com.meli.util.OrdenadorProdutos.Shipping;

import java.net.URI;
import java.util.List;

import exception.BadRequestException;
import exception.ResponseEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.Pattern;

@Service
public class ArticlesService {

	@Autowired
	private ArticleRepository articlesRepository;


	/**
	 * @param articles
	 * @return void
	 * @author ?????
	 * @description Chama função para serializar Produtos em JSON
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
	 * @param articlesPurchaseList
	 * @return List
	 * @author Marcelo Scarton
	 * @description Retorna lista de Produtos solicitados para compra
	 */
	public List<Produto> retornarProdutosPurchase(ArticlesPurchase articlesPurchaseList) {
		List<Produto> produtos = articlesRepository.desserializaProdutos();
		List<Produto> purchaseList = new ArrayList<>();
		for (ArticlesPurchaseDTO a : articlesPurchaseList.getArticlesPurchaseRequest()) {
			Produto produto = produtos.stream().filter(p -> p.getProductId().equals(a.getProductId())).findAny()
					.orElse(null);
			if (produto == null) {
				throw new ResponseEntityException("O produto com o id " + a.getProductId() + " não existe.", "400");
			}
			if (produto.getQuantity() < a.getQuantity()) {
				throw new ResponseEntityException(
						"Não há estoque suficiente para o produto " + a.getName() + ", " +
								"a quantidade atual é de " + produto.getQuantity() + " unidades(s).", "400");
			}
			purchaseList.add(produto);
		}
		return purchaseList;
	}

	/**
	 * @param articles
	 * @return BigDecimal
	 * @author Marcelo Scarton
	 * @description Retorna valor total da lista de Produtos solicitados para compra
	 */
	public BigDecimal retornarTotalPurchase(List<Produto> articles) {
		BigDecimal total = new BigDecimal("0");
		for (Produto p : articles) {
			total = total.add(p.getPrice().multiply(BigDecimal.valueOf(p.getQuantity())));
		}
		return total;
	}

	/**
	 * @return List
	 * @author Francisco Alves
	 * @description Retorna todos os produtos cadastrados
	 */
	public List<Produto> getProdutos() {
		return articlesRepository.desserializaProdutos();
	}

	/**
	 * Author: André Arroxellas
	 *
	 * @param categoryName
	 * @param productName
	 * @param brandName
	 * @param freeShipping
	 * @param orderFilter
	 * @return List
	 * @Description Condicionais para filtros & ordenadores
	 */
	public List<Produto> trateRequestQuery(String categoryName, String productName,
			String brandName, Boolean freeShipping, Integer orderFilter) {

		List<Produto> listaProdutos = articlesRepository.desserializaProdutos();

		OrdenadorProdutos ordenadorProdutos = new OrdenadorProdutos();

		if (orderFilter != null) {
			if (orderFilter <= 3 && orderFilter >= 0) {
				listaProdutos = ordenadorProdutos.odernarProdutos(
						listaProdutos, null, Ordenador.values()[orderFilter]);
			} else {
				throw new IllegalArgumentException("Order não pode ser: " + orderFilter);
			}
		}
		if (freeShipping != null) {
			if (freeShipping.equals(true)) {
				listaProdutos = ordenadorProdutos.filtrarShipping(
						listaProdutos, null, Shipping.FILTRA_FREE_SHIPPING);
			} else if (freeShipping.equals(false)) {
				listaProdutos = ordenadorProdutos.filtrarShipping(
						listaProdutos, null, Shipping.FILTRA_NON_FREE_SHIPPING);
			}
		}
		if (categoryName != null) {
			listaProdutos = ordenadorProdutos.filtrarProdutos(
					listaProdutos, categoryName, Filtro.FILTRA_CATEGORY_NAME);
		}
		if (productName != null) {
			listaProdutos = ordenadorProdutos.filtrarProdutos(
					listaProdutos, productName, Filtro.FILTRA_PRODUCT_NAME);
		}
		if (brandName != null) {
			listaProdutos = ordenadorProdutos.filtrarProdutos(
					listaProdutos, brandName, Filtro.FILTRA_BRAND_NAME);
		}

		return listaProdutos;
	}


	/**
	 * @author Thomaz Ferreira
	 * @description Valida JSON e cadastra produtos
	 * @param articles
	 * @return ArticlesDTO
	 */
	public ResponseEntity<ArticlesDTO> cadastraProdutos(Articles articles, URI uri) {
		if(articles.getArticles().size() == 0){
			throw new ResponseEntityException("Não existe nenhum produto na lista", "400");
		}

		for(Produto p : articles.getArticles()){
			if(p.getProductId() == null || p.getProductId().equals(""))
				throw new ResponseEntityException("O atributo productId não foi informado ou é nulo", "400");
			if(p.getName() == null || p.getName().equals(""))
				throw new ResponseEntityException("O atributo name não foi informado ou é nulo", "400");
			if(p.getCategory() == null || p.getCategory().equals(""))
				throw new ResponseEntityException("O atributo category não foi informado ou é nulo", "400");
			if(p.getBrand() == null || p.getBrand().equals(""))
				throw new ResponseEntityException("O atributo brand não foi informado ou é nulo", "400");
			if(p.getPrice() == null || p.getPrice().equals(""))
				throw new ResponseEntityException("O atributo price não foi informado ou é nulo", "400");
			if(p.getQuantity() == null || p.getQuantity().equals(""))
				throw new ResponseEntityException("O atributo quantity não foi informado ou é nulo", "400");
			if(p.getFreeShipping() == null)
				throw new ResponseEntityException("O atributo freeShipping não foi informado ou é nulo", "400");
			if(p.getPrestige() == null || p.getPrestige().equals(""))
				throw new ResponseEntityException("O atributo prestige não foi informado ou é nulo", "400");
			else{
				for(int i=0; i<p.getPrestige().length(); i++){
					if(p.getPrestige().charAt(i) != '*')
						throw new ResponseEntityException("O atributo prestige deve aceitar apenas *", "400");
				}
			}
		}

		this.salvarProdutos(articles);
		return ResponseEntity.created(uri).body(ArticlesDTO.converte(articles));
	}
}

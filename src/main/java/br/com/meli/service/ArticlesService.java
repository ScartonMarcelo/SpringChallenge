package br.com.meli.service;

import br.com.meli.dto.ArticlesPurchaseDTO;
import br.com.meli.entity.Articles;
import br.com.meli.entity.ArticlesPurchase;
import br.com.meli.entity.Produto;
import br.com.meli.repository.ArticleRepository;
import br.com.meli.util.OrdenadorProdutos;
import br.com.meli.util.OrdenadorProdutos.Filtro;
import br.com.meli.util.OrdenadorProdutos.Ordenador;
import br.com.meli.util.OrdenadorProdutos.Shipping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticlesService {

	@Autowired
	private ArticleRepository articlesRepository;

	/**
	 * @author Thomaz Ferreira
	 * @description Chama função para serializar Produtos em JSON
	 * @param Articles articles
	 * @return void
	 */
	public void salvarProdutos(Articles articles) {
		articlesRepository.serializaProdutos(articles.getArticles());
	}

	/**
	 * @author Marcelo Scarton
	 * @description Retorna lista de Produtos solicitados para compra
	 * @param ArticlesPurchase articlesPurchaseList
	 * @return List<Produto>
	 */
	public List<Produto> retornarProdutosPurchase(ArticlesPurchase articlesPurchaseList) {
		List<Produto> produtos = articlesRepository.desserializaProdutos();
		List<Produto> purchaseList = new ArrayList<>();
		for (ArticlesPurchaseDTO a : articlesPurchaseList.getArticlesPurchaseRequest()) {
			purchaseList
					.add(produtos.stream().filter(p -> p.getProductId() == a.getProductId()).findAny().orElse(null));
		}
		return purchaseList;
	}

	/**
	 * @author Marcelo Scarton
	 * @description Retorna valor total da lista de Produtos solicitados para compra
	 * @param List<Produto> articles
	 * @return BigDecimal
	 */
	public BigDecimal retornarTotalPurchase(List<Produto> articles) {
		BigDecimal total = new BigDecimal("0");
		for (Produto p : articles) {
			total = total.add(p.getPrice().multiply(BigDecimal.valueOf(p.getQuantity())));
		}
		return total;
	}

	/**
	 * @author Francisco Alves
	 * @description Retorna todos os produtos cadastrados
	 * @return List<Produto>
	 */
	public List<Produto> getProdutos() {
		return articlesRepository.desserializaProdutos();
	}

	/**
	 * Author: André Arroxellas
	 *
	 * @Description Condicionais para filtros & ordenadores
	 * @param categoryName
	 * @param productName
	 * @param brandName
	 * @param freeShipping
	 * @param orderFilter
	 * @return List<Produto>
	 */
	public static List<Produto> trateRequestQuery(String categoryName, String productName,
			String brandName, Boolean freeShipping, Integer orderFilter) {

		ArticleRepository articleRepository = new ArticleRepository();
		List<Produto> listaProdutos = articleRepository.desserializaProdutos();

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
}

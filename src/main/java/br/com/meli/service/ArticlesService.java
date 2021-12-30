package br.com.meli.service;

import br.com.meli.entity.Articles;
import br.com.meli.entity.Produto;
import br.com.meli.repository.ArticleRepository;
import br.com.meli.util.OrdenadorProdutos;
import br.com.meli.util.OrdenadorProdutos.Filtro;
import br.com.meli.util.OrdenadorProdutos.Ordenador;
import br.com.meli.util.OrdenadorProdutos.Shipping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticlesService {

	@Autowired
	private ArticleRepository articlesRepository;

	public void salvarProdutos(Articles articles) {
		articlesRepository.serializaProdutos(articles.getArticles());
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
	 * @return
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

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

	public static List<Produto> trateRequest(String categoryName, String productName,
			String brandName, Boolean freeShipping, Integer orderFilter) {

		// TODO: por que static ?
		ArticleRepository articleRepository = new ArticleRepository();
		List<Produto> listaProdutos = articleRepository.desserializaProdutos();

		OrdenadorProdutos ordenadorProdutos = new OrdenadorProdutos();

		// for (int i = 0; i < Filter.values().length; i++)
		if (orderFilter <= 3 && orderFilter >= 0) {
			listaProdutos = ordenadorProdutos.odernarProdutos(
					listaProdutos, null, Ordenador.values()[orderFilter]);
		}

		if (freeShipping.equals(true)) {
			listaProdutos = ordenadorProdutos.filtrarShipping(
					listaProdutos, null, Shipping.FILTRA_FREE_SHIPPING);
		} else if (freeShipping.equals(false)) {
			listaProdutos = ordenadorProdutos.filtrarShipping(
					listaProdutos, null, Shipping.FILTRA_NON_FREE_SHIPPING);
		}

		if (!categoryName.isBlank()) {
			listaProdutos = ordenadorProdutos.filtrarProdutos(
					listaProdutos, categoryName, Filtro.FILTRA_CATEGORY_NAME);
		}
		if (!productName.isBlank()) {
			listaProdutos = ordenadorProdutos.filtrarProdutos(
					listaProdutos, productName, Filtro.FILTRA_PRODUCT_NAME);
		}
		if (!brandName.isBlank()) {
			listaProdutos = ordenadorProdutos.filtrarProdutos(
					listaProdutos, brandName, Filtro.FILTRA_BRAND_NAME);
		}

		return listaProdutos;
	}
}

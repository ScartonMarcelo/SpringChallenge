package br.com.meli.service;

import br.com.meli.entity.Produto;
import br.com.meli.repository.ArticleRepository;
import exception.ResponseEntityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

	@Autowired
	private ArticleRepository articleRepository;

	/**
	 * Recupera a lista completa de produtos e filtra por categoria
	 *
	 * @author Thiago Campos
	 * @param categoryName
	 * @throws ResponseEntityException
	 * @return List
	 */
	public List<Produto> filterByCategory(String categoryName) {
		List<Produto> products = articleRepository.desserializaProdutos()
				.stream()
				.filter(produto -> produto.getCategory().equalsIgnoreCase(categoryName))
				.collect(Collectors.toList());
		if (products.size() == 0)
			throw new ResponseEntityException("O parametro " + categoryName + " não foi localizado", "400");
		else
			return products;
	}

}

package br.com.meli.service;

import br.com.meli.entity.Produto;
import br.com.meli.repository.ArticleRepository;
import br.com.meli.repository.ProdutoRepository;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

	@Autowired
	private ArticleRepository articleRepository;

	/**
	 * @Author Thiago Campos
	 * @Description Recupera a lsita completa de produtos e filtra por categoria
	 * @param  categoryName
	 * @return List<Produto>
	 */
	public List<Produto> filterByCategory(String categoryName) {
		List<Produto> products = articleRepository.desserializaProdutos().stream().filter(produto -> produto.getCategory().equalsIgnoreCase(categoryName)).collect(Collectors.toList());
		if(products.size() == 0)
			throw new ResourceNotFoundException(String.format("O parametro %s não foi localizado", categoryName));
		else
			return products;
	}
}

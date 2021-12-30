package br.com.meli.service;

import br.com.meli.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
//	public List<Produto> filterByCategory(String categoryName) {
//		List<Produto> products = articleRepository.desserializaProdutos().stream().filter(produto -> produto.getCategory().equalsIgnoreCase(categoryName)).collect(Collectors.toList());
//		if(products.size() == 0)
//			throw new ResourceNotFoundException();
//		else
//			return products;
//	}
}

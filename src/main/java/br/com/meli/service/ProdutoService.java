package br.com.meli.service;

import br.com.meli.entity.Produto;
import br.com.meli.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	/**
	 * @Author Thiago Campos
	 * @Description Recupera a lsita completa de produtos e filtra por categoria
	 * @param String categoryName
	 * @return List<Produto>
	 */
	public static List<Produto> filterByCategory(String categoryName) {
		return ProdutoRepository.getAll()
			.stream().filter(produto -> produto.getCategory().equalsIgnoreCase(categoryName))
			.collect(Collectors.toList());
	}
}

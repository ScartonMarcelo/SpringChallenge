package br.com.meli.service;

import br.com.meli.entity.Articles;
import br.com.meli.entity.Produto;
import br.com.meli.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticlesService {

	@Autowired
	private ArticleRepository articlesRepository;

	public void salvarProdutos(Articles articles){
		articlesRepository.serializaProdutos(articles.getArticles());
	}

	public List<Produto> getProdutos(){
		return articlesRepository.desserializaProdutos();
	}
}

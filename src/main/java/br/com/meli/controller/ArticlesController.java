package br.com.meli.controller;

import br.com.meli.dto.ArticlesDTO;
import br.com.meli.entity.Articles;
import br.com.meli.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ArticlesController {

	@Autowired
	private ArticlesService articleService;


	@PostMapping("/insert-articles-request")
	private ResponseEntity<List<ArticlesDTO>> cadastraProduto(@RequestBody Articles articlesList, UriComponentsBuilder uriBuilder) {
		articleService.salvarProdutos(articlesList);
		URI uri = uriBuilder.path("/api/v1/articles").build().toUri();
		return ResponseEntity.created(uri).body(ArticlesDTO.converte(articlesList.getArticles()));
	}
}

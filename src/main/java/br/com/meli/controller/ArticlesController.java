package br.com.meli.controller;

import br.com.meli.dto.ArticlesDTO;
import br.com.meli.entity.Articles;
import br.com.meli.repository.ArticleRepository;
import br.com.meli.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class ArticlesController {

  @Autowired
  private ArticlesService articleService;


	/**
	 * Author: Thomaz Ferreira
	 * @Description Rota para cadastrar produtos
	 * @param ArticlesDTO dto
	 * @param UriComponentsBuilder uriBuilder
	 * @return ResponseEntity<ArticlesDTO>
	 */
  @PostMapping("/insert-articles-request")
  private ResponseEntity<ArticlesDTO> cadastraProduto(@RequestBody ArticlesDTO dto, UriComponentsBuilder uriBuilder){
  	  Articles article = ArticlesDTO.converte(dto);
	  articleService.salvarProdutos(article);
	  URI uri = uriBuilder.path("/api/v1/articles").build().toUri();
	  return ResponseEntity.created(uri).body(ArticlesDTO.converte(article));
  }
}

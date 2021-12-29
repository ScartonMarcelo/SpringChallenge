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

@RestController
@RequestMapping("/api/v1")
public class ArticlesController {

  @Autowired
  private ArticleRepository articleRepository;


  @PostMapping("/insert-articles-request")
  private ResponseEntity<ArticlesDTO> cadastraProduto(@RequestBody ArticlesDTO dto){
	Articles article = ArticlesDTO.converte(dto);
	articleRepository.salvaProdutoCarrinho(article.getArticles());
	return ResponseEntity.ok(ArticlesDTO.converte(article));
  }
}

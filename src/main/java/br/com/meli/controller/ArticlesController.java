package br.com.meli.controller;

import br.com.meli.dto.ArticlesDTO;
import br.com.meli.entity.Articles;
import br.com.meli.entity.Produto;
import br.com.meli.repository.ArticleRepository;
import br.com.meli.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ArticlesController {

	@Autowired
	private ArticlesService articleService;

	List<Produto> produtos = new ArrayList<Produto>();


	/**
	 * Author: Thomaz Ferreira
	 * @Description Rota para cadastrar produtos
	 * @param ArticlesDTO dto
	 * @param UriComponentsBuilder uriBuilder
	 * @return ResponseEntity<ArticlesDTO>
	 */
	  @PostMapping("/insert-articles-request")
	  private ResponseEntity<ArticlesDTO> cadastraProduto(@RequestBody Articles articles, UriComponentsBuilder uriBuilder){
		  articleService.salvarProdutos(articles);
		  URI uri = uriBuilder.path("/api/v1/articles").build().toUri();
		  return ResponseEntity.created(uri).body(ArticlesDTO.converte(articles));
	  }

	/**
	 * Author: Francisco Alves , Thomaz Ferreira
	 * @Description Rota para listar produtos
	 *
	 */
	@GetMapping("/articles")
	public ResponseEntity<List<Produto>> getAll()
	{
        produtos = articleService.getProdutos();
		return ResponseEntity.ok(produtos);
	}
}

package br.com.meli.controller;

import br.com.meli.dto.ArticlesDTO;
import br.com.meli.dto.ProdutoDTO;
import br.com.meli.entity.Articles;
import br.com.meli.entity.Produto;
import br.com.meli.service.ArticlesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ArticlesController {

	@Autowired
	private ArticlesService articleService;

	/**
	 * Author: Thomaz Ferreira
	 *
	 * @Description Rota para cadastrar produtos
	 * @param ArticlesDTO          dto
	 * @param UriComponentsBuilder uriBuilder
	 * @return ResponseEntity<ArticlesDTO>
	 */
	@PostMapping("/insert-articles-request")
	private ResponseEntity<ArticlesDTO> cadastraProduto(@RequestBody Articles articles,
			UriComponentsBuilder uriBuilder) {
		articleService.salvarProdutos(articles);
		URI uri = uriBuilder.path("/api/v1/articles").build().toUri();
		return ResponseEntity.created(uri).body(ArticlesDTO.converte(articles));
	}

	/**
	 * Author: André Arroxellas
	 *
	 * @Description Rota para pesquisa em query de produtos
	 * @param categoryName
	 * @param productName
	 * @param brandName
	 * @param freeShipping
	 * @param orderFilter
	 * @param uriBuilder
	 * @return ResponseEntity<List<ProdutoDTO>>
	 */
	@GetMapping("/articles")
	// @Validated
	private ResponseEntity<List<Produto>> getListaProdutosFiltradoOrdenado(
			@RequestParam(value = "category", required = false) String categoryName,
			@RequestParam(value = "product", required = false) String productName,
			@RequestParam(value = "brand", required = false) String brandName,
			@RequestParam(value = "freeShipping", required = false) Boolean freeShipping,
			@RequestParam(value = "order", required = false) Integer orderFilter
	// @RequestParam(value = "order", required = false) @Max(3) Integer orderFilter,
	// Depends on javax.validation.constraints
	) {
		List<Produto> articles = ArticlesService.trateRequestQuery(categoryName, productName, brandName, freeShipping,
				orderFilter);
		return ResponseEntity.accepted().body(articles);
	}
}

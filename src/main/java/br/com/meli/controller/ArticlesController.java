package br.com.meli.controller;

import br.com.meli.dto.ArticlesDTO;
import br.com.meli.dto.ProdutoDTO;
import br.com.meli.entity.Articles;
import br.com.meli.entity.ArticlesPurchase;
import br.com.meli.entity.Produto;
import br.com.meli.service.ArticlesService;
import br.com.meli.response.PurchaseResponse;

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
	private ArticlesService articlesService;

	/**
	 * Rota para cadastrar produtos
	 *
	 * @author Thomaz Ferreira
	 * @param articles
	 * @param uriBuilder
	 * @return ArticlesDTO
	 */
	@PostMapping("/insert-articles-request")
	private ResponseEntity<ArticlesDTO> cadastraProduto(@RequestBody Articles articles,
			UriComponentsBuilder uriBuilder) {
		URI uri = uriBuilder.path("/api/v1/articles").build().toUri();
		return articlesService.cadastraProdutos(articles, uri);
	}

	/**
	 * Endpoint responsavel pelo envio de pedido de compra
	 *
	 * @author Marcelo Scarton
	 * @param articlesPurchaseList
	 * @param uriBuilder
	 * @return ResponseEntity<PurchaseResponse>
	 */
	@PostMapping("/purchase-request")
	private ResponseEntity<PurchaseResponse> solicitarCompra(@RequestBody ArticlesPurchase articlesPurchaseList,
			UriComponentsBuilder uriBuilder) {
		URI uri = uriBuilder.path("/api/v1/articles").build().toUri();
		return articlesService.adicionaCarrinho(articlesPurchaseList, uri);
	}

	/**
	 * Rota para pesquisa em query de produtos
	 *
	 * @author André Arroxellas , Francisco Alves , Thomaz Ferreira
	 * @param categoryName
	 * @param productName
	 * @param brandName
	 * @param freeShipping
	 * @param orderFilter
	 * @return ResponseEntity
	 */
	@GetMapping("/articles")
	private ResponseEntity<List<ProdutoDTO>> getListaProdutosFiltradoOrdenado(
			@RequestParam(value = "category", required = false) String categoryName,
			@RequestParam(value = "product", required = false) String productName,
			@RequestParam(value = "brand", required = false) String brandName,
			@RequestParam(value = "freeShipping", required = false) Boolean freeShipping,
			@RequestParam(value = "order", required = false) Integer orderFilter) {
		List<Produto> articles = articlesService.trateRequestQuery(
				categoryName, productName, brandName, freeShipping, orderFilter);
		return ResponseEntity.ok().body(articles.stream()
				.map(ProdutoDTO::converte)
				.collect(Collectors.toList()));
	}
}

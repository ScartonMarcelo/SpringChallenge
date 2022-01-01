package br.com.meli.controller;

import br.com.meli.dto.ArticlesDTO;
import br.com.meli.entity.Articles;
import br.com.meli.entity.ArticlesPurchase;
import br.com.meli.entity.Produto;
import br.com.meli.entity.Ticket;
import br.com.meli.service.ArticlesService;
import br.com.meli.response.PurchaseResponse;
import br.com.meli.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ArticlesController {

	@Autowired
	private ArticlesService articlesService;

	@Autowired
	private ProdutoService produtoService;

	List<Produto> produtos = new ArrayList<Produto>();

	/**
	 * Author: Thomaz Ferreira
	 *
	 * @param \
	 * @param \ uriBuilder
	 * @return ResponseEntity<ArticlesDTO>
	 * @Description Rota para cadastrar produtos
	 */
	@PostMapping("/insert-articles-request")
	private ResponseEntity<ArticlesDTO> cadastraProduto(@RequestBody Articles articles,
			UriComponentsBuilder uriBuilder) {
		articlesService.salvarProdutos(articles);
		URI uri = uriBuilder.path("/api/v1/articles").build().toUri();
		return ResponseEntity.created(uri).body(ArticlesDTO.converte(articles));
	}

	/**
	 * @param articlesPurchaseList
	 * @param uriBuilder
	 * @return ResponseEntity<PurchaseResponse>
	 * @Author Marcelo Scarton
	 * @description Endpoint responsavel pelo envio de pedido de compra
	 */

	@PostMapping("/purchase-request")
	private ResponseEntity<PurchaseResponse> solicitarCompra(@RequestBody ArticlesPurchase articlesPurchaseList,
			UriComponentsBuilder uriBuilder) {
		URI uri = uriBuilder.path("/api/v1/articles").build().toUri();
		List<Produto> articles = articlesService.retornarProdutosPurchase(articlesPurchaseList);
		BigDecimal total = articlesService.retornarTotalPurchase(articles);
		Ticket ticket = Ticket.builder().Id((long) 530).articles(articles).total(total).build();
		return ResponseEntity.created(uri).body(PurchaseResponse.builder().ticket(ticket).build());
	}

	/**
	 * @Author: Francisco Alves , Thomaz Ferreira
	 * @Description Rota para listar produtos
	 * @return ResponseEntity<List < Produto>>
	 */
	/*
	 * @GetMapping("/articles")
	 * public ResponseEntity<List<Produto>> getAll() {
	 * produtos = articleService.getProdutos();
	 * return ResponseEntity.ok(produtos);
	 * }
	 */

	/**
	 * Author: André Arroxellas
	 *
	 * @param categoryName
	 * @param productName
	 * @param brandName
	 * @param freeShipping
	 * @param orderFilter
	 * @param
	 * @return ResponseEntity<List < ProdutoDTO>>
	 * @Description Rota para pesquisa em query de produtos
	 */
	@GetMapping("/articles")
	private ResponseEntity<List<Produto>> getListaProdutosFiltradoOrdenado(
			@RequestParam(value = "category", required = false) String categoryName,
			@RequestParam(value = "product", required = false) String productName,
			@RequestParam(value = "brand", required = false) String brandName,
			@RequestParam(value = "freeShipping", required = false) Boolean freeShipping,
			@RequestParam(value = "order", required = false) Integer orderFilter) {
		List<Produto> articles = articlesService.trateRequestQuery(
				categoryName, productName, brandName, freeShipping, orderFilter);
		return ResponseEntity.ok().body(articles);
	}
}

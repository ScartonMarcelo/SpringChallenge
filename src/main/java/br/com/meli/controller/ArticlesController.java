package br.com.meli.controller;

import br.com.meli.dto.ArticlesDTO;
import br.com.meli.entity.Articles;
import br.com.meli.entity.ArticlesPurchase;
import br.com.meli.entity.Produto;
import br.com.meli.entity.Ticket;
import br.com.meli.service.ArticlesService;
import br.com.meli.response.PurchaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ArticlesController {

	@Autowired
	private ArticlesService articleService;


	/**
	 * Author: Thomaz Ferreira
	 *
	 * @param ArticlesDTO          dto
	 * @param UriComponentsBuilder uriBuilder
	 * @return ResponseEntity<ArticlesDTO>
	 * @Description Rota para cadastrar produtos
	 */
	@PostMapping("/insert-articles-request")
	private ResponseEntity<ArticlesDTO> cadastraProduto(@RequestBody Articles articles, UriComponentsBuilder uriBuilder) {
		articleService.salvarProdutos(articles);
		URI uri = uriBuilder.path("/api/v1/articles").build().toUri();
		return ResponseEntity.created(uri).body(ArticlesDTO.converte(articles));
	}

	@PostMapping("/purchase-request")
	private ResponseEntity<PurchaseResponse> solicitarCompra(@RequestBody ArticlesPurchase articlesPurchaseList, UriComponentsBuilder uriBuilder) {
		URI uri = uriBuilder.path("/api/v1/articles").build().toUri();
		List<Produto> articles = articleService.retornarProdutosPurchase(articlesPurchaseList);
		BigDecimal total = articleService.retornarTotalPurchase(articles);
		Ticket ticket = Ticket.builder().Id((long) 530).articles(articles).total(total).build();
		return ResponseEntity.created(uri).body(PurchaseResponse.builder().ticket(ticket).build());
	}
}

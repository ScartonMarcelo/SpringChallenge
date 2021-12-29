package br.com.meli.controller;


import br.com.meli.entity.Produto;
import br.com.meli.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ProdutoController{

	List<Produto> produtos = new ArrayList<Produto>();

    @Autowired
    private ArticleRepository articleRepository;


	@GetMapping("/articles")
	public ResponseEntity<List<Produto>> getAll()
	{
		produtos = articleRepository.desserializaProdutos();
		return ResponseEntity.ok(produtos);
	}

}





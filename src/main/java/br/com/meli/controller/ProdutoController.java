package br.com.meli.controller;

import br.com.meli.dto.ProdutoDTO;
import br.com.meli.entity.Produto;
import br.com.meli.repository.ProdutoRepository;
import br.com.meli.service.ProdutoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ProdutoController {

    @Autowired

    private ProdutoService produtoService;

@GetMapping("/articles")
public List<Produto>getAll()
{
   List<Produto> produtos =Arrays.asList(

			 Produto.builder()
				 .id(1)
				 .name("Serra de Bancada")
				 .category("Ferramentas")
				 .brand("Black & Decker")
				 .price(new BigDecimal(500))
				 .quantity(7)
				 .freeShipping(true)
				 .prestige("****")
				 .build(),
			 Produto.builder()
				 .id(2)
				 .name("Furadeira")
				 .category("Ferramentas")
				 .brand("Starboard")
				 .price(new BigDecimal(183))
				 .quantity(4)
				 .freeShipping(true)
				 .prestige("****")
				 .build(),
			 Produto.builder()
				 .id(3)
				 .name("Mini Cama elastica")
				 .category("Esportes")
				 .brand("FORTGPRO")
				 .price(new BigDecimal(1800))
				 .quantity(5)
				 .freeShipping(true)
				 .prestige("*****")
				 .build()
	 );
		return produtos;
}
}

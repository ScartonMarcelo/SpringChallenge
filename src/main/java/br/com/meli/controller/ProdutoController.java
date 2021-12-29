package br.com.meli.controller;


import br.com.meli.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
<<<<<<< HEAD
public class ProdutoController{

    //@Autowired
    //private ProdutoService produtoService;
=======
public class ProdutoController<cea93af71f0d7867ec88fdd95673b20826723cb> {

    @Autowired

    private ProdutoService produtoService;
>>>>>>> a3e50efe2ec11ff699380244fe1ebea6c2cf0433


@GetMapping("/articles")
public List<Produto>getAll()
{
   List<Produto> produtos =Arrays.asList(

			 Produto.builder()
				 .productId(1)
				 .name("Serra de Bancada")
				 .category("Ferramentas")
				 .brand("Black & Decker")
				 .price(new BigDecimal(500))
				 .quantity(7)
				 .freeShipping(true)
				 .prestige("****")
				 .build(),
			 Produto.builder()
				 .productId(2)
				 .name("Furadeira")
				 .category("Ferramentas")
				 .brand("Starboard")
				 .price(new BigDecimal(183))
				 .quantity(4)
				 .freeShipping(true)
				 .prestige("****")
				 .build(),
			 Produto.builder()
				 .productId(3)
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





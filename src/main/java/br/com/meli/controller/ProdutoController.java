package br.com.meli.controller;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

@GetMapping("/articles")
public List<Produto>getProdutos()
{
	List<Produto>produtoList = new ArrayList<Produto>();
  return produtoList;
}
}

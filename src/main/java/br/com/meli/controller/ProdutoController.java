package br.com.meli.controller;

import br.com.meli.dto.ProdutoDTO;
import br.com.meli.entity.Produto;
import br.com.meli.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/api/v1")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

}

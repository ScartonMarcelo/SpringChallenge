package br.com.meli.repository;

import br.com.meli.entity.Produto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProdutoRepository {
	private List<Produto> produtos = new ArrayList<>();

}

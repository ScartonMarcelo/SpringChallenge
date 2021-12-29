package br.com.meli.controller;

import br.com.meli.dto.ProdutoDTO;
import br.com.meli.entity.Produto;
import br.com.meli.repository.ProdutoRepository;
import br.com.meli.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	/**
	 * @param String categoryName
	 * @return ResponseEntitu<< List>ProdutoDTO>>
	 * @author thiago campos
	 */
	@GetMapping("/category")
	public ResponseEntity<List<ProdutoDTO>> getListByCategory(@RequestParam String categoryName) {
		List<Produto> filteredProducts = ProdutoService.filterByCategory(categoryName);
		return ResponseEntity.ok(ProdutoDTO.convert(filteredProducts));
	}
}

package br.com.meli.repository;

import br.com.meli.entity.Produto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class ProdutoRepository {

	public static List<Produto> getAll() {
		//JSON_Mock
		List<Produto> produtos = Arrays.asList(
			Produto.builder()
				.productId(1l)
				.name("Serra de Bancada")
				.category("Ferramentas")
				.brand("Black & Decker")
				.price(new BigDecimal(500))
				.quantity(7)
				.freeShipping(true)
				.prestige("****")
				.build(),
			Produto.builder()
				.productId(2l)
				.name("Furadeira")
				.category("Ferramentas")
				.brand("Starboard")
				.price(new BigDecimal(183))
				.quantity(4)
				.freeShipping(true)
				.prestige("****")
				.build(),
			Produto.builder()
				.productId(3l)
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

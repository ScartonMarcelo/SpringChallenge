package br.com.meli.repository;

import br.com.meli.entity.Produto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleRepository {

	private static final String JSON_FILE_NAME = "carrinho.json";

	// Serializa produtos em JSON
	public static void salvaProdutoCarrinho(List<Produto> produtos) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(new File(JSON_FILE_NAME), produtos);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	// Desserializa Produtos do carrinho
	public List<Produto> desserializaProdutos() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<Produto> listaProdutos = new ArrayList<Produto>();
		try {
			listaProdutos = mapper.readValue(new File("carrinho.json"),
					mapper.getTypeFactory().constructCollectionType(
							List.class, Produto.class));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaProdutos;
	}

}

package br.com.meli.repository;

import br.com.meli.entity.Produto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.ResponseEntityException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleRepository {

	private final String JSON_FILE_NAME = "produtos.json";

	/**
	 * @author
	 * ESCRIÇÃO AQUI
	 * @param produtos
	 */
	public void serializaProdutos(List<Produto> produtos) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(new File(JSON_FILE_NAME), produtos);
		} catch (IOException e) {
			throw new ResponseEntityException("Erro ao criar arquivo JSON", e.getMessage(), "400");
		}
	}

	/**
	 * @author
	 * DESCRIÇÃO AQUI
	 * @return List
	 */
	public List<Produto> desserializaProdutos() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<Produto> listaProdutos = new ArrayList<Produto>();
		try {
			File file = new File(JSON_FILE_NAME);
			if (file.exists()) {
				listaProdutos = mapper.readValue(file,
						mapper.getTypeFactory().constructCollectionType(
								List.class, Produto.class));
			}
		} catch (IOException e) {
			throw new ResponseEntityException("Erro ao recuperar dados do JSON", e.getMessage(), "400");
		}
		return listaProdutos;
	}
}

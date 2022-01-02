package br.com.meli.repository;

import br.com.meli.entity.Cliente;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class ClienteRepository {

	Cliente cliente = new Cliente();

	List<Cliente> mock = Arrays.asList(
		cliente.builder()
			.nome("a")
			.estado("SP")
			.build(),
		cliente.builder()
			.nome("b")
			.estado("SP")
			.build(),
		cliente.builder()
			.nome("c")
			.estado("SP")
			.build(),
		cliente.builder()
			.nome("D")
			.estado("MG")
			.build(),
		cliente.builder()
			.nome("e")
			.estado("MG")
			.build(),
		cliente.builder()
			.nome("F")
			.estado("RJ")
			.build()
	);

	public List<Cliente> getAll() {
		return mock;
	}
}

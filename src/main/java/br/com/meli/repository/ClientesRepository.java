package br.com.meli.repository;

import br.com.meli.entity.Cliente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientesRepository {
	private List<Cliente> clientes = new ArrayList<>();

	/**
	 * @author ???
	 * DESCRIÇÃO AQUI
	 * @param cliente
	 */
	public void save(Cliente cliente) {
		clientes.add(cliente);
	}


	/**
	 * @author ???
	 * DESCRIÇÃO AQUI
	 * @param cliente
	 */
	public void delete(Cliente cliente) {
		clientes.remove(cliente);
	}


	/**
	 * @author ???
	 * DESCRIÇÃO AQUI
	 * @return List
	 */
	public List<Cliente> getAll() {
		return clientes;
	}
}

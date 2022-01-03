package br.com.meli.repository;

import br.com.meli.entity.Cliente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientesRepository {
	private List<Cliente> clientes = new ArrayList<>();

	/**
	 * Salva em memória dados de Cliente
	 *
	 * @author André Arroxellas
	 * @param cliente
	 */
	public void save(Cliente cliente) {
		clientes.add(cliente);
	}

	/**
	 * Remove em memória dados de Cliente
	 *
	 * @author André Arroxellas
	 * @param cliente
	 */
	public void delete(Cliente cliente) {
		clientes.remove(cliente);
	}

	/**
	 * Busca em memória dados de Clientes
	 *
	 * @author André Arroxellas
	 * @return List
	 */
	public List<Cliente> getAll() {
		return clientes;
	}
}

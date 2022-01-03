package br.com.meli.repository;

import br.com.meli.entity.Cliente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientesRepository {
	private List<Cliente> clientes = new ArrayList<>();

	public void save(Cliente cliente) {
		clientes.add(cliente);
	}

	public void delete(Cliente cliente) {
		clientes.remove(cliente);
	}

	public List<Cliente> getAll() {
		return clientes;
	}
}

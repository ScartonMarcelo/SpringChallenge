package br.com.meli.repository;

import br.com.meli.entity.Cliente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientesRepository {
	private List<Cliente> clientes = new ArrayList<>();

	public void save(Cliente cliente) {
		System.out.println("\n\n ================ " + cliente + " ================\n\n");
		clientes.add(cliente);
	}

	public void delete(Cliente cliente) {
		clientes.remove(cliente);
	}

	public List<Cliente> listaClientes() {
		return clientes;
	}
}

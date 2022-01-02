package br.com.meli.service;

import br.com.meli.entity.Cliente;
import br.com.meli.repository.ClienteRepository;
import exception.ResponseEntityException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

	ClienteRepository clienteRepository = new ClienteRepository();

	public List<Cliente> getAll(){
		return clienteRepository.getAll();
	}

	public List<Cliente> filteredByState(String state) {
		List<Cliente> result = getAll().stream().filter(user -> user.getEstado().equalsIgnoreCase(state)).collect(Collectors.toList());
		if(result.size() == 0)
			throw new ResponseEntityException(String.format("A pesquisa pelo valor %s retornou nula",state), "404");
		else
			return result;
	}
}

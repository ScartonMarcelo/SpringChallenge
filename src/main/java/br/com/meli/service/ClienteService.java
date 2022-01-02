package br.com.meli.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meli.dto.ClienteDTO;
import br.com.meli.entity.Cliente;
import br.com.meli.entity.Cliente.StatusClient;
import br.com.meli.repository.ClientesRepository;
import exception.ResponseEntityException;

@Service
public class ClienteService {
	@Autowired
	private ClientesRepository clienteRepository;

	// Change Email
	// Change Status
	public ClienteDTO cadastraCliente(ClienteDTO clienteDTO) {
		Boolean isEmailTaken = clienteRepository.listaClientes().stream()
				.filter(c -> c.getEmail().equals(clienteDTO.getEmail()))
				.findFirst().isPresent();

		if (isEmailTaken) {
			throw new ResponseEntityException("Email já cadastrado: " + clienteDTO.getEmail(), "404");
		}

		String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
		Boolean isValidEmail = clienteDTO.getEmail().matches(EMAIL_PATTERN);
		if (!isValidEmail) {
			throw new ResponseEntityException("Email no formato errado: " + clienteDTO.getEmail(), "400");
		}
		if (clienteDTO.getName() == null || clienteDTO.getName().trim() == "") {
			throw new ResponseEntityException("Nome não informado: " + clienteDTO.getName(), "400");
		}

		clienteRepository.save(new Cliente(clienteDTO.getName(), clienteDTO.getEmail()));

		clienteDTO.setStatus(StatusClient.ACTIVE);

		return clienteDTO;
	}

	public StatusClient buscaCliente(String email) {
		return clienteRepository.listaClientes().stream()
				.filter(c -> c.getEmail().equals(email))
				.findFirst()
				.map(Cliente::getStatus)
				.orElse(null);
	}

	public Cliente buscaAdminCliente(String email) {
		return clienteRepository.listaClientes().stream()
				.filter(c -> c.getEmail().equals(email))
				.findFirst()
				.orElse(null);
	}

	public List<ClienteDTO> listaClientes() {
		// TODO: Deve ser rota Admin
		if (clienteRepository.listaClientes().isEmpty()) {
			throw new ResponseEntityException("Não existe usuários no sistema", "400");
		}

		return clienteRepository.listaClientes().stream()
				.map(ClienteDTO::converteToDTO)
				.collect(Collectors.toList());
	}
}

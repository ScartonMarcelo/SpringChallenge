package br.com.meli.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meli.dto.ClienteDTO;
import br.com.meli.entity.Cliente;
import br.com.meli.entity.Cliente.StatusClient;
import br.com.meli.repository.ClientesRepository;
import br.com.meli.util.ValidarUsuario;
import exception.ResponseEntityException;

@Service
public class ClienteService {
	@Autowired
	private ClientesRepository clienteRepository;

	@Autowired
	private ValidarUsuario validarUsuario;

	public ClienteDTO cadastraCliente(ClienteDTO clienteDTO) {
		validarUsuario.isEmailValid(clienteDTO.getEmail());
		validarUsuario.isNameValid(clienteDTO.getName());

		clienteRepository.save(new Cliente(clienteDTO.getName(), clienteDTO.getEmail()));
		clienteDTO.setStatus(StatusClient.ACTIVE);

		return clienteDTO;
	}

	public StatusClient buscaCliente(String email) {
		validarUsuario.isEmailValid(email);
		return clienteRepository.listaClientes().stream()
				.filter(c -> c.getEmail().equals(email))
				.findFirst()
				.map(Cliente::getStatus)
				.orElse(null); // TODO: implement orElseThrow(error)
	}

	public Cliente buscaAdminCliente(String email) {
		validarUsuario.isEmailValid(email);
		return clienteRepository.listaClientes().stream()
				.filter(c -> c.getEmail().equals(email))
				.findFirst()
				.orElse(null); // TODO: implement orElseThrow(error)
	}

	public List<ClienteDTO> listaClientes() {
		// Deve ser rota Admin
		if (clienteRepository.listaClientes().isEmpty()) {
			throw new ResponseEntityException("Não existe usuários no sistema", "400");
		}

		return clienteRepository.listaClientes().stream()
				.map(ClienteDTO::converteToDTO)
				.collect(Collectors.toList());
	}

	public ClienteDTO mudaAtributo(String email, String emailChange, String password) {
		Cliente c = this.buscaAdminCliente(email);
		validarUsuario.isRegistered(c);

		if (emailChange != null) {
			validarUsuario.isEmailValid(emailChange);
			validarUsuario.isEmailTaken(emailChange);
			c.setEmail(emailChange);
		}

		validarUsuario.isPasswordValid(password);
		c.setPassword(password);

		return ClienteDTO.converteToDTO(c);
	}

	// TODO: implement route for session + cart
	public String sessionCliente(String email, String password) {
		Optional<Cliente> clienteAuth = clienteRepository.listaClientes().stream()
				.filter(c -> c.getEmail().equals(email))
				.filter(c -> c.getPassword().equals(password))
				.findFirst();

		return "Sessão para:" + "iniciada";
	}
}

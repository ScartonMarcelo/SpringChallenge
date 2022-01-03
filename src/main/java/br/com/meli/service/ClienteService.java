package br.com.meli.service;

import java.util.List;
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

	/**
	 * @author André Arroxellas
	 *         Método de cadastro de cliente. Chama método save em Repositório
	 * @param clienteDTO
	 * @return ClienteDTO
	 */
	public ClienteDTO cadastraCliente(ClienteDTO clienteDTO) {
		validarUsuario.isEmailValid(clienteDTO.getEmail());
		validarUsuario.isEmailTaken(clienteDTO.getEmail());
		validarUsuario.isNameValid(clienteDTO.getName());

		clienteRepository.save(new Cliente(clienteDTO.getName(),
				clienteDTO.getEmail(), clienteDTO.getEstado()));
		clienteDTO.setStatus(StatusClient.ACTIVE);

		return clienteDTO;
	}

	/**
	 * Método de busca de cliente por email (Admin)
	 *
	 * @author André Arroxellas
	 * @param email
	 * @throws ResponseEntityException
	 * @return CLiente
	 */
	public Cliente buscaAdminCliente(String email) {
		validarUsuario.isEmailValid(email);
		return clienteRepository.getAll().stream()
				.filter(c -> c.getEmail().equals(email))
				.findFirst()
				.orElseThrow(() -> new ResponseEntityException("Email inválido", "400"));
	}

	/**
	 * Lista todos os usuários do sistema
	 *
	 * @author André Arroxellas, Thiago Campos
	 * @throws ResponseEntityException
	 * @return List<ClienteDTO>
	 */
	public List<ClienteDTO> listaClientes() {
		// Deve ser rota Admin
		if (clienteRepository.getAll().isEmpty()) {
			throw new ResponseEntityException("Não existe usuários no sistema", "400");
		}

		return clienteRepository.getAll().stream()
				.map(ClienteDTO::converteToDTO)
				.collect(Collectors.toList());
	}

	/**
	 * @author André Arroxellas
	 *         Método para mudança de atributos não 'final' de cliente em memória
	 * @param email
	 * @param emailChange
	 * @param password
	 * @return CLienteDTO
	 */
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

	/**
	 * Filtra por Estado
	 *
	 * @author Thiago Campos
	 * @param estado
	 * @throws ResponseEntityException
	 * @return List
	 */
	public List<Cliente> filteredByState(String estado) {
		List<Cliente> result = clienteRepository.getAll().stream()
				.filter(c -> c.getEstado().equalsIgnoreCase(estado))
				.collect(Collectors.toList());

		if (result.size() == 0)
			throw new ResponseEntityException(String.format("A pesquisa pelo valor %s retornou nula", estado), "404");
		else
			return result;
	}

}

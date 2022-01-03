package br.com.meli.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.meli.dto.ClienteDTO;
import br.com.meli.entity.Cliente;
import br.com.meli.service.ClienteService;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

	@Autowired
	private ClienteService clienteService;

	/**
	 * Rota de listagem de todos os usuários
	 *
	 * @author André Arroxellas
	 * @return ResponseEntity
	 */
	@GetMapping("/users")
	private ResponseEntity<List<ClienteDTO>> listaClientes() {
		return ResponseEntity.ok().body(clienteService.listaClientes());
	}

	/**
	 * Rota de cadastro de Cliente
	 *
	 * @author André Arroxellas
	 * @param clienteDTO
	 * @param uriBuilder
	 * @return ResponseEntity
	 */
	@PostMapping("/users/register")
	private ResponseEntity<ClienteDTO> cadastraCliente(@RequestBody ClienteDTO clienteDTO,
			UriComponentsBuilder uriBuilder) {
		URI uri = uriBuilder.path("/api/v1/users").build().toUri();
		return ResponseEntity.created(uri).body(clienteService.cadastraCliente(clienteDTO));
	}

	/**
	 * Rota para mudanças de atributos
	 *
	 * @author André Arroxellas
	 * @param email
	 * @param emailChange
	 * @param password
	 * @return ResponseEntity
	 */
	@GetMapping("/user/attributes")
	private ResponseEntity<ClienteDTO> mudaAtributo(
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "emailChange", required = false) String emailChange,
			@RequestParam(value = "password", required = false) String password) {
		return ResponseEntity.ok().body(clienteService.mudaAtributo(email, emailChange, password));
	}

	/**
	 * Rota para busca de dados de cliente (Admin)
	 *
	 * @author André Arroxellas
	 * @param email
	 * @return ResponseEntity
	 */
	@GetMapping(value = "/user")
	private ResponseEntity<Cliente> buscaCliente(
			@RequestParam(value = "email", required = false) String email) {
		return ResponseEntity.ok().body(clienteService.buscaAdminCliente(email));
	}
}

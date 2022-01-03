package br.com.meli.controller;

import java.net.URI;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.meli.dto.ClienteDTO;
import br.com.meli.entity.Cliente;
import br.com.meli.entity.Cliente.StatusClient;
import br.com.meli.service.ClienteService;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/users")
	private ResponseEntity<List<ClienteDTO>> listaClientes() {
		return ResponseEntity.ok().body(clienteService.listaClientes());
	}

	@PostMapping("/users/register")
	private ResponseEntity<ClienteDTO> cadastraCliente(@RequestBody ClienteDTO clienteDTO,
			UriComponentsBuilder uriBuilder) {
		URI uri = uriBuilder.path("/api/v1/users").build().toUri();
		return ResponseEntity.created(uri).body(clienteService.cadastraCliente(clienteDTO));
	}

	@GetMapping("/user")
	private ResponseEntity<StatusClient> buscaStatusCliente(
			@RequestParam(value = "email", required = false) String email) {
		return ResponseEntity.ok().body(clienteService.buscaCliente(email));
	}

	@GetMapping("/user/{estado}")
	private ResponseEntity<List<ClienteDTO>> buscaClienteEstado(
			@PathVariable("estado") String estado) {
		return ResponseEntity.ok().body(clienteService.buscaClienteEstado(estado));
	}

	@PatchMapping("/user/session")
	private ResponseEntity<String> sessionCliente(
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password) {
		return ResponseEntity.ok().body(clienteService.sessionCliente(email, password));
	}

	@GetMapping("/user/attributes")
	private ResponseEntity<ClienteDTO> mudaAtributo(
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "estado", required = true) String estado,
			@RequestParam(value = "emailChange", required = false) String emailChange,
			@RequestParam(value = "password", required = false) String password) {
		return ResponseEntity.ok().body(clienteService.mudaAtributo(email, emailChange, password));
	}

	@GetMapping(value = "/admin")
	private ResponseEntity<Cliente> buscaCliente(
			@RequestParam(value = "email", required = false) String email) {
		return ResponseEntity.ok().body(clienteService.buscaAdminCliente(email));
	}

}

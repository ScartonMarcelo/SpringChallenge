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
import br.com.meli.entity.Cliente.StatusClient;
import br.com.meli.service.ClienteService;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

	@Autowired
	private ClienteService clienteService;

	@PostMapping("/users/register")
	private ResponseEntity<ClienteDTO> cadastraCliente(@RequestBody ClienteDTO clienteDTO,
			UriComponentsBuilder uriBuilder) {
		URI uri = uriBuilder.path("/api/v1/users").build().toUri();
		return ResponseEntity.created(uri).body(clienteService.cadastraCliente(clienteDTO));
	}

	@GetMapping("/users")
	private ResponseEntity<List<ClienteDTO>> listaClientes() {
		return ResponseEntity.ok().body(clienteService.listaClientes());
	}

	@GetMapping("/user")
	private ResponseEntity<StatusClient> buscaStatusCliente(
			@RequestParam(value = "email", required = true) String email) {
		return ResponseEntity.ok().body(clienteService.buscaCliente(email));
	}

	@GetMapping(value = "/admin")
	private ResponseEntity<Cliente> buscaCliente(
			@RequestParam(value = "email", required = true) String email) {
		return ResponseEntity.ok().body(clienteService.buscaAdminCliente(email));
	}

}

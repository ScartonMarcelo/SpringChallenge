package br.com.meli.dto;

import br.com.meli.entity.Cliente;
import br.com.meli.entity.Cliente.StatusClient;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteDTO {
	private String name;
	private String email;
	private StatusClient status;

	public static ClienteDTO converteToDTO(Cliente c) {
		return new ClienteDTO(
				c.getName(),
				c.getEmail(),
				c.getStatus());
	}

	public ClienteDTO() {
	}
}

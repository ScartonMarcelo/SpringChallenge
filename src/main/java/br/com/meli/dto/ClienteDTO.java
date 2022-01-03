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
	private String estado;
	private StatusClient status;

	/**
	 * Converte obejto Cliente em ClienteDTO
	 *
	 * @author André Arroxellas
	 * @param cliente
	 * @return ClienteDTO
	 */
	public static ClienteDTO converteToDTO(Cliente cliente) {
		return new ClienteDTO(
				cliente.getName(),
				cliente.getEmail(),
				cliente.getEstado(),
				cliente.getStatus());
	}
}

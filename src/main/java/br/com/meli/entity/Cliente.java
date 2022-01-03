package br.com.meli.entity;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Cliente {
	private final String name;
	private String email;
	private String estado;
	private String password;
	private final UUID id;
	private StatusClient status;

	/**
	 * Instancia um Cliente com base em um ClienteDTO
	 *
	 * @author André Arroxellas
	 * @param name
	 * @param email
	 * @param estado
	 * @return Cliente
	 */
	public Cliente(String name, String email, String estado) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.estado = estado;
		this.email = email;
		this.status = StatusClient.INACTIVE;
	}

	public enum StatusClient {
		ACTIVE,
		INACTIVE;
	}
}

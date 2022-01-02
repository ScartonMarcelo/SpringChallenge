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
	private String password;
	private final UUID id;
	private StatusClient status;

	public Cliente(String name, String email) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.email = email;
		this.status = StatusClient.INACTIVE;
	}

	public enum StatusClient {
		ACTIVE,
		INACTIVE;
	}
}

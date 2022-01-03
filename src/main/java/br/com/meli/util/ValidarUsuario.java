package br.com.meli.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.meli.entity.Cliente;
import br.com.meli.repository.ClientesRepository;
import exception.ResponseEntityException;

@Component
public class ValidarUsuario {
	@Autowired
	ClientesRepository clientesRepository;

	/**
	 * Valida se email já está cadastrado
	 *
	 * @author André Arroxellas
	 * @param email
	 * @throws ResponseEntityException se email existente
	 */
	public void isEmailTaken(String email) {
		Boolean isEmailTaken = clientesRepository.getAll().stream()
				.filter(c -> c.getEmail().equals(email))
				.findFirst().isPresent();

		if (isEmailTaken) {
			throw new ResponseEntityException("Email já cadastrado: " + email, "404");
		}
	}

	/**
	 * Valida se email está padronizado
	 *
	 * @author André Arroxellas
	 * @param email
	 * @throws ResponseEntityException se email fora do padrão
	 */
	public void isEmailValid(String email) {
		if (email == null) {
			throw new ResponseEntityException("Email nulo: " + email, "400");
		}
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Boolean isValidEmail = email.matches(EMAIL_PATTERN);
		if (!isValidEmail) {
			throw new ResponseEntityException("Email no formato errado: " + email, "400");
		}
	}

	/**
	 * Valida se nome do parâmetro está padronizado
	 *
	 * @author André Arroxellas
	 * @param name
	 * @throws ResponseEntityException se nome de parâmetro fora do padrão
	 */
	public void isNameValid(String name) {
		if (name == null || name.trim() == "") {
			throw new ResponseEntityException("Nome não informado: " + name, "400");
		}
	}

	/**
	 * Valida se password está padronizado
	 *
	 * @author André Arroxellas
	 * @param password
	 * @throws ResponseEntityException se password fora do padrão
	 */
	public void isPasswordValid(String password) {
		if (password == null || password.trim().equals("")) {
			throw new ResponseEntityException("Password não informado: " + password, "400");
		}
	}

	/**
	 * Valida se Cliente não está cadastrado
	 *
	 * @author André Arroxellas
	 * @param cliente
	 * @throws ResponseEntityException se usuário existente no sistema
	 */
	public void isRegistered(Cliente cliente) {
		if (cliente == null) {
			throw new ResponseEntityException("Email não registrado no sistema", "400");
		}
	}
}

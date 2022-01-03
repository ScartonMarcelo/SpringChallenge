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
	 * @author ??
	 * DESCRIÇÃO AQUI
	 * @param email
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
	 * @author ???
	 * DESCRIÇÃO AQUI
	 * @param email
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
	 * @author ???
	 * DESCRIÇÃO AQUI
	 * @param name
	 */
	public void isNameValid(String name) {
		if (name == null || name.trim() == "") {
			throw new ResponseEntityException("Nome não informado: " + name, "400");
		}
	}


	/**
	 * @author ???
	 * DESCRIÇÃO AQUI
	 * @param password
	 */
	public void isPasswordValid(String password) {
		if (password == null || password.trim().equals("")) {
			throw new ResponseEntityException("Password não informado: " + password, "400");
		}
	}


	/**
	 * @author ???
	 * DESCRIÇÃO AQUI
	 * @param c
	 */
	public void isRegistered(Cliente c) {
		if (c == null) {
			throw new ResponseEntityException("Email não registrado no sistema", "400");
		}
	}
}

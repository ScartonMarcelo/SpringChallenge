package br.com.meli.advice;

import exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class ProdutoExceptionAdvice {

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<?> handlePersistencia(ResourceNotFoundException resourse) {
		String bodyOfResponse = resourse.getMessage();
		return ResponseEntity.badRequest().body(bodyOfResponse);
	}
}

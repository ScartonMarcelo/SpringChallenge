package br.com.meli.advice;

import exception.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class ProdutoExceptionAdvice {

	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<?> handlePersistencia(BadRequestException resourse) {
		String bodyOfResponse = resourse.getMessage();
		return ResponseEntity.badRequest().body(bodyOfResponse);
	}
}

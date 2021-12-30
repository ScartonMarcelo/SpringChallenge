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
		ResourceNotFoundException msg = resourse.builder()
			.timeStamp(LocalDate.now())
			.title("Not Found")
			.httpCode(HttpStatus.NOT_FOUND.value())
			.message("O parametro retornou nulo na pesquisa").build();
		return new ResponseEntity<>(msg.getMessage(),HttpStatus.NOT_FOUND);
	}
}

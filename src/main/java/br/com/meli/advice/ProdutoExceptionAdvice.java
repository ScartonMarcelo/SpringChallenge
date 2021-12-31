package br.com.meli.advice;

import br.com.meli.util.ResponseEntityErrorsUtils;
import exception.BadRequestException;
import exception.ResponseEntityException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProdutoExceptionAdvice extends ResponseEntityErrorsUtils {


	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<?> handlePersistencia(BadRequestException resourse) {
		String bodyOfResponse = resourse.getMessage();
		return super.responseEntityFactory(bodyOfResponse, "404");
	}

	/**
	 * @author Thiago Campos, Thomaz Ferreira
	 * @description Funçao responsável por lançar uma exception personalizada
	 * @param e
	 * @return ResponseEntity
	 */
	@ExceptionHandler(value = ResponseEntityException.class)
	public ResponseEntity<?> factoryExceptions(ResponseEntityException e) {
		return super.responseEntityFactory(e.getMessage(),e.getStatusCode());
	}


	/**
	 * @author Thomaz Ferreira
	 * @description Trata exceptions de HttpMessageNotReadableException e retorna payload no modelo definido em responseEntityFactory
	 * @param e
	 * @return ResponseEntity
	 */
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResponseEntity<?> JsonExceptions(HttpMessageNotReadableException e) {
		return super.responseEntityFactory("JSON inválido", "400");
	}

}

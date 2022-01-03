package br.com.meli.advice;

import br.com.meli.util.ResponseEntityErrorsUtils;
import exception.ResponseEntityException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ProdutoExceptionAdvice extends ResponseEntityErrorsUtils {

	/**
	 * Funçao responsável por lançar uma exception personalizada
	 *
	 * @author Thiago Campos, Thomaz Ferreira
	 * @param e
	 * @return ResponseEntity
	 */
	@ExceptionHandler(value = ResponseEntityException.class)
	public ResponseEntity<?> factoryExceptions(ResponseEntityException e) {
		if (e.getException() == null)
			return super.responseEntityFactory(e.getMessage(), e.getStatusCode());
		return super.responseEntityFactory(e.getMessage(), e.getException(), e.getStatusCode());
	}

	/**
	 * Trata exceptions de HttpMessageNotReadableException e retorna payload no
	 * modelo definido em responseEntityFactory
	 *
	 * @author Thomaz Ferreira
	 * @param e
	 * @return ResponseEntity
	 */
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResponseEntity<?> JsonExceptions(HttpMessageNotReadableException e) {
		return super.responseEntityFactory("JSON inválido", "400");
	}

	/**
	 * Trata exceptions MethodArgumentTypeMismatchException em parâmetros de rota
	 *
	 * @author André Arroxellas
	 * @param e
	 * @return ResponseEntity
	 */
	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> InvalidArgumentException(MethodArgumentTypeMismatchException e) {
		return super.responseEntityFactory("Argumento inválido", "400");
	}
}

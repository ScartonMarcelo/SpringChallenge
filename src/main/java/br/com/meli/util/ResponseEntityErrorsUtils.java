package br.com.meli.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseEntityErrorsUtils {

	private Map<String, String> objectResponse = new HashMap<>();

	/**
	 * @author Thomaz Ferreira
	 * Retorna ResponseEntity correspondente ao statusCode de erro informado
	 * @param exceptionMsg
	 * @param statusCode
	 * @return ResponseEntity
	 */
	public ResponseEntity<?> responseEntityFactory(String exceptionMsg, String statusCode) {
		objectResponse.put("mensagem", exceptionMsg);
		if (statusCode.equals("400")) {
			objectResponse.put("statusCode", Integer.toString(HttpStatus.BAD_REQUEST.value()));
			return ResponseEntity.badRequest().body(objectResponse);
		} else if (statusCode.equals("404")) {
			objectResponse.put("statusCode", Integer.toString(HttpStatus.NOT_FOUND.value()));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(objectResponse);
		} else {
			objectResponse.put("statusCode", Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()));
			return ResponseEntity.internalServerError().body(objectResponse);
		}
	}


	/**
	 * @author Thomaz Ferreira
	 * Retorna ResponseEntity correspondente ao statusCode de erro informado
	 * @param exceptionMsg
	 * @param statusCode
	 * @return ResponseEntity
	 */
	public ResponseEntity<?> responseEntityFactory(String customMsg, String exceptionMsg, String statusCode) {
		objectResponse.put("mensagem", customMsg);
		objectResponse.put("exception", exceptionMsg);
		if (statusCode.equals("400")) {
			objectResponse.put("statusCode", Integer.toString(HttpStatus.BAD_REQUEST.value()));
			return ResponseEntity.badRequest().body(objectResponse);
		} else if (statusCode.equals("404")) {
			objectResponse.put("statusCode", Integer.toString(HttpStatus.NOT_FOUND.value()));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(objectResponse);
		} else {
			objectResponse.put("statusCode", Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()));
			return ResponseEntity.internalServerError().body(objectResponse);
		}
	}
}

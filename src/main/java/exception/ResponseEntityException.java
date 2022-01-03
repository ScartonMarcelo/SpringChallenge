package exception;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public final class ResponseEntityException extends RuntimeException {

	@Getter
	private String statusCode;
	@Getter
	private String exception;

	/**
	 * Método construtor da função de exception
	 *
	 * @author Thomaz Ferreira
	 * @param msg
	 * @param statusCode
	 */
	public ResponseEntityException(String msg, String statusCode) {
		super(msg);
		this.statusCode = statusCode;
	}

	/**
	 * Overload método construtor da função de exception
	 *
	 * @author Thomaz Ferreira
	 * @param msg
	 * @param exception
	 * @param statusCode
	 */
	public ResponseEntityException(String msg, String exception, String statusCode) {
		super(msg);
		this.exception = exception;
		this.statusCode = statusCode;
	}
}

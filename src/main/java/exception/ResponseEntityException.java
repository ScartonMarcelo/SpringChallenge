package exception;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public final class ResponseEntityException extends RuntimeException{

	@Getter
	private String statusCode;

	public ResponseEntityException(String msg, String statusCode){
		super(msg);
		this.statusCode = statusCode;
	}
}

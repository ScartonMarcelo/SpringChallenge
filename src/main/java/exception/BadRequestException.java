package exception;


public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = -1013775922197850159L;

	public BadRequestException(String msg) {
		super(msg);
	}
}

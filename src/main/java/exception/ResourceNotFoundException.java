package exception;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -1013775922197850159L;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}

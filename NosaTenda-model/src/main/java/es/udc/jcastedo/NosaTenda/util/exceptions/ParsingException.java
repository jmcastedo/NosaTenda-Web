package es.udc.jcastedo.NosaTenda.util.exceptions;

public class ParsingException extends RuntimeException {

	public ParsingException() {
		super();
	}
	
	public ParsingException(String message) {
		super(message);
	}
	
	public ParsingException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ParsingException(Throwable cause) {
		super(cause);
	}
	
}

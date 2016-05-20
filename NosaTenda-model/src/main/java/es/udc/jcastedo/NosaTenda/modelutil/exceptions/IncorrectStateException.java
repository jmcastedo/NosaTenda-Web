package es.udc.jcastedo.NosaTenda.modelutil.exceptions;

@SuppressWarnings("serial")
public class IncorrectStateException extends Exception {
	
	private String state;

	public IncorrectStateException(String state) {
		super("Incorrect state exception => state = " + state);
		this.state = state;
	}
	
	public String getState() {
		return state;
	}
}

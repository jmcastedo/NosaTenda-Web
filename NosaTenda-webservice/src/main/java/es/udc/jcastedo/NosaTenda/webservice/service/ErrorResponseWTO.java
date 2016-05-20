package es.udc.jcastedo.NosaTenda.webservice.service;

public class ErrorResponseWTO {

	private String type;
	private String className;
	private String message;
	
	public ErrorResponseWTO() {}
	
	public ErrorResponseWTO(String type, String className, String message) {
		
		this.type = type;
		this.className = className;
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}

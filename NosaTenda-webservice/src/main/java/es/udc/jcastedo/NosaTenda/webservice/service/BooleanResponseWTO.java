package es.udc.jcastedo.NosaTenda.webservice.service;

public class BooleanResponseWTO {

	private Boolean response;
	
	public BooleanResponseWTO() {}
	
	public BooleanResponseWTO(Boolean response) {
		
		this.response = response;
	}

	public Boolean getResponse() {
		return response;
	}

	public void setResponse(Boolean response) {
		this.response = response;
	}
}

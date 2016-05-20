package es.udc.jcastedo.NosaTenda.webservice.service;

public class PayPalConfirmResponseWTO {

	private String state;
	
	public PayPalConfirmResponseWTO() {}
	
	public PayPalConfirmResponseWTO(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}

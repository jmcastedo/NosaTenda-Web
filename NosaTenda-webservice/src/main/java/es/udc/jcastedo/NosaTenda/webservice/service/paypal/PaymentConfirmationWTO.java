package es.udc.jcastedo.NosaTenda.webservice.service.paypal;


public class PaymentConfirmationWTO {

	private ResponseWTO response;
	private ClientePaypalWTO client;
	private String response_type;

	
	public ResponseWTO getResponse() {
		return response;
	}
	public void setResponse(ResponseWTO response) {
		this.response = response;
	}
	public ClientePaypalWTO getClient() {
		return client;
	}
	public void setClient(ClientePaypalWTO client) {
		this.client = client;
	}
	public String getResponse_type() {
		return response_type;
	}
	public void setResponse_type(String response_type) {
		this.response_type = response_type;
	}

	
}

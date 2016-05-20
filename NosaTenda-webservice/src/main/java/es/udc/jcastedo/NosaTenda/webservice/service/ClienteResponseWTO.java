package es.udc.jcastedo.NosaTenda.webservice.service;

public class ClienteResponseWTO {

	private Long clienteId;
	
	public ClienteResponseWTO() {}
	
	public ClienteResponseWTO(Long clienteId) {
		
		this.clienteId = clienteId;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	
	
}

package es.udc.jcastedo.NosaTenda.webservice.service;

public class ReservaResponseWTO {

	private Long reservaId;
	
	public ReservaResponseWTO() {}
	
	public ReservaResponseWTO(Long reservaId) {
		
		this.reservaId = reservaId;
	}

	public Long getReservaId() {
		return reservaId;
	}

	public void setReservaId(Long reservaId) {
		this.reservaId = reservaId;
	}
	
}

package es.udc.jcastedo.NosaTenda.webservice.service;

public class StockResponseWTO {

	private Long stock;
	
	public StockResponseWTO() {}
	
	public StockResponseWTO(Long stock) {
		
		this.stock = stock;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}
}

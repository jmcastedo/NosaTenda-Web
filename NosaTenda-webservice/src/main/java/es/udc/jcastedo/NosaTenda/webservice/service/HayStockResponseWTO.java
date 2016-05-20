package es.udc.jcastedo.NosaTenda.webservice.service;

public class HayStockResponseWTO {

	private Boolean hayStock;
	
	public HayStockResponseWTO() {}
	
	public HayStockResponseWTO(Boolean hayStock) {
		
		this.hayStock = hayStock;
	}

	public Boolean getHayStock() {
		return hayStock;
	}

	public void setHayStock(Boolean hayStock) {
		this.hayStock = hayStock;
	}
}

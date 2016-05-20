package es.udc.jcastedo.NosaTenda.webservice.service.paypal;

public class DetailsWTO {

	private Double tax;
	private Double subtotal;
	private Double shipping;
	
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Double getShipping() {
		return shipping;
	}
	public void setShipping(Double shipping) {
		this.shipping = shipping;
	}
	
}

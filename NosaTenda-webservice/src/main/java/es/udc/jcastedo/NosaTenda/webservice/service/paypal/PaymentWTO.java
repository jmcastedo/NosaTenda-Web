package es.udc.jcastedo.NosaTenda.webservice.service.paypal;


public class PaymentWTO {

	private PaypalPaymentWTO paypalPayment;
	private PaymentConfirmationWTO paymentConfirmation;
	private Long productoId;
	private Long clienteId;
	
	
	public PaypalPaymentWTO getPaypalPayment() {
		return paypalPayment;
	}
	public void setPaypalPayment(PaypalPaymentWTO paypalPayment) {
		this.paypalPayment = paypalPayment;
	}
	public PaymentConfirmationWTO getPaymentConfirmation() {
		return paymentConfirmation;
	}
	public void setPaymentConfirmation(PaymentConfirmationWTO paymentConfirmation) {
		this.paymentConfirmation = paymentConfirmation;
	}
	public Long getProductoId() {
		return productoId;
	}
	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}
	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	
}

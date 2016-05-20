package es.udc.jcastedo.NosaTenda.webservice.service.utils;

import com.paypal.api.payments.Payment;

import es.udc.jcastedo.NosaTenda.webservice.service.paypal.PaymentWTO;

public class PayPalServletUtils {

	private PayPalServletUtils() {}
	
	public static boolean confirmPayment(Payment payment, PaymentWTO paymentWTO) {
		
		// Paypal response
		String state = payment.getState();
		Double amount = Double.valueOf(payment.getTransactions().get(0).getAmount().getTotal());
		String currency = payment.getTransactions().get(0).getAmount().getCurrency();
		String salesState = payment.getTransactions().get(0).getRelatedResources().get(0).getSale().getState();
		
		// Android response
		String expectedState = "approved";
		Double expectedAmount = paymentWTO.getPaypalPayment().getAmount();
		String expectedCurrency = paymentWTO.getPaypalPayment().getCurrency_code();
		String expectedSalesState = "completed";
		
		if (!state.equals(expectedState)) {
			return false;
		}
		if (!amount.equals(expectedAmount)) {
			return false;
		}
		if (!currency.equals(expectedCurrency)) {
			return false;
		}
		if (!salesState.equals(expectedSalesState)) {
			return false;
		}
		
		return true;
	}
}

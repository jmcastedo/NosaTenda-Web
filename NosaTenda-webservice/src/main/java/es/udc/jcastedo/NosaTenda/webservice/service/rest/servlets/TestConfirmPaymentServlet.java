package es.udc.jcastedo.NosaTenda.webservice.service.rest.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.base.rest.PayPalResource;

import es.udc.jcastedo.NosaTenda.webservice.service.paypal.PaymentWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpPaypalServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.PayPalServletUtils;

@SuppressWarnings("serial")
public class TestConfirmPaymentServlet extends JSONHttpPaypalServlet {
	
	private static final Logger logger = LoggerFactory.getLogger(TestConfirmPaymentServlet.class);
	
	@Override
	public void doPost(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doPost de TestConfirmPaymentServlet");
		
		ObjectMapper mapper = new ObjectMapper();
		
		PaymentWTO paymentWTO = new PaymentWTO();
		
		paymentWTO = mapper.readValue(request.getInputStream(), PaymentWTO.class);
		
		logger.debug("paymentConfirmation: "+ paymentWTO.getPaymentConfirmation().getResponse().getId());
		logger.debug("paymentInfo, short_description: "+ paymentWTO.getPaypalPayment().getShort_description());
		logger.debug("paymentConfirmation, response type: "+ paymentWTO.getPaymentConfirmation().getResponse_type());
		
		// Test
		//Map<String, String> sdkConfig = new HashMap<String, String>();
		
		//sdkConfig.put("mode", "sandbox");
		
		/*OAuthTokenCredential tokenCredential = new OAuthTokenCredential(
				"AflmuYXeePCTrLTN6Nv40PHDVrQIhQHGMSl01Dz6EGPD5bjovbdYYD39I-066t-9bbFslE91acRNankD",
				"EIIeaIMvLdTLnESUvWg1DPm5M9PMTvvSfLPPDzsJWcoOdj2xPt_veWjJpzX2iOUQ5f7NFVCpV_TITdaw",
				sdkConfig);*/
		
		
		OAuthTokenCredential tokenCredential = PayPalResource.getOAuthTokenCredential();
		
		try {
			logger.debug("tokenCredential: " + tokenCredential.getAccessToken());
			Payment payment = Payment.get(tokenCredential.getAccessToken(), paymentWTO.getPaymentConfirmation().getResponse().getId());
			// state = approved
			// dentro de amount, total y currency coinciden con los originales
			// state = completed
			// state de sales (en related_resources) = "completed"
			Double amount = Double.valueOf(payment.getTransactions().get(0).getAmount().getTotal());
			String currency = payment.getTransactions().get(0).getAmount().getCurrency();
			
			// prueba de error
			//payment.setState("denied");
			if ((payment.getState().equals("approved")) &&
				(amount.equals(paymentWTO.getPaypalPayment().getAmount())) &&
				currency.equals(paymentWTO.getPaypalPayment().getCurrency_code())) {
				logger.debug("COMPRA CONFIRMADA!!");
				logger.debug("PaypalServletUtils confirmación: " + PayPalServletUtils.confirmPayment(payment, paymentWTO));
			} else {
				logger.debug("COMPRA NO CONFIRMADA!!");
				logger.debug("amount: " + amount);
				logger.debug("paymentWTO amount: " + paymentWTO.getPaypalPayment().getAmount());
				logger.debug("currency: " + currency);
				logger.debug("paymentWTO currency: " + paymentWTO.getPaypalPayment().getCurrency_code());
				logger.debug("state: " + payment.getState());
				logger.debug("PaypalServletUtils confirmación: " + PayPalServletUtils.confirmPayment(payment, paymentWTO));
			}
		} catch (PayPalRESTException e) {
			logger.debug("tokenCredential: rest error");
			e.printStackTrace();
		}
		
		// TODO construir respuesta para Android (writeServiceResponse)
	}
}

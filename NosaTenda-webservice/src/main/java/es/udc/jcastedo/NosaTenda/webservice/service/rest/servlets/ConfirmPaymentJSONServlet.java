package es.udc.jcastedo.NosaTenda.webservice.service.rest.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.base.rest.PayPalResource;

import es.udc.jcastedo.NosaTenda.model.CompraTO.RecogidaStateTO;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InsufficientStockException;
import es.udc.jcastedo.NosaTenda.webservice.service.ErrorResponseWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.PayPalConfirmResponseWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.paypal.PaymentWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpPaypalServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.PayPalServletUtils;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils;

@SuppressWarnings("serial")
public class ConfirmPaymentJSONServlet extends JSONHttpPaypalServlet {

	private static final Logger logger = LoggerFactory.getLogger(ConfirmPaymentJSONServlet.class);
	
	@Override
	public void doPost(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doPost de ConfirmPaymentJSONServlet");
		
		try {
			
			PayPalConfirmResponseWTO confirmWTO = new PayPalConfirmResponseWTO();
			
			ObjectMapper mapper = new ObjectMapper();
			
			PaymentWTO paymentWTO = mapper.readValue(request.getInputStream(), PaymentWTO.class);
			
			logger.debug("Payment ID: "+ paymentWTO.getPaymentConfirmation().getResponse().getId());
			
			// TODO hacer esta llamada cuando el token haya expirado (campo expires_in, valor usual 28800 segundos (8 horas))
			OAuthTokenCredential tokenCredential = PayPalResource.getOAuthTokenCredential();
			
			logger.debug("tokenCredential: " + tokenCredential.getAccessToken());
			
			Payment payment = Payment.get(tokenCredential.getAccessToken(), paymentWTO.getPaymentConfirmation().getResponse().getId());
			
			if (PayPalServletUtils.confirmPayment(payment, paymentWTO)) {
				
				logger.debug("COMPRA CONFIRMADA!!");
				// TODO opcion, meter compraId y newStock en la respuesta, por si Android le es útil
				confirmWTO.setState("confirmada");
				compraService.guardarCompra(
						paymentWTO.getPaypalPayment().getItem_list().getItems()[0].getQuantity(),
						RecogidaStateTO.NO_RECOGIDA,
						paymentWTO.getPaymentConfirmation().getResponse().getCreate_time(),
						paymentWTO.getPaypalPayment().getDetails().getSubtotal(),
						paymentWTO.getPaypalPayment().getAmount(),
						paymentWTO.getPaymentConfirmation().getResponse().getId(),
						payment.getState(),
						payment.getPayer().getPaymentMethod(),
						paymentWTO.getPaypalPayment().getCurrency_code(),
						paymentWTO.getProductoId(),
						paymentWTO.getClienteId());
				
			} else {
				
				logger.debug("COMPRA NO CONFIRMADA!!");
				confirmWTO.setState("denegada");
				// TODO no se confirma el pago, por tanto no se guarda nada en BD
			}
			
			ServletUtils.writeServiceResponse(confirmWTO, response);
			
		} catch (PayPalRESTException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("PayPalRESTException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 500);
			
		} catch (InstanceNotFoundException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("InstanceNotFoundException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 500);
			
		} catch (DataIntegrityViolationException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("DataIntegrityViolationException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 500);
			
		} catch (InsufficientStockException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("InsufficientStockException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 500);
			
		} catch (BadFormatRequestException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("BadFormatRequestException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 400);
			
		} catch (JsonParseException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("JsonParseException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 400);
			
		}
		
	}
}

package es.udc.jcastedo.NosaTenda.webservice.service.rest.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.IncorrectStateException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InsufficientStockException;
import es.udc.jcastedo.NosaTenda.webservice.service.ErrorResponseWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.ReservaResponseWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils;

@SuppressWarnings("serial")
public class CancelarReservaByIdJSONServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(CancelarReservaByIdJSONServlet.class);
	
	@Override
	public void doPost(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doPost de CancelarReservaByIdJSONServlet");
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			
			ReservaResponseWTO reservaResponseWTO = mapper.readValue(
					request.getInputStream(), ReservaResponseWTO.class);
			
			reservaService.cancelarReserva(reservaResponseWTO.getReservaId());
			
			ServletUtils.writeServiceResponse(reservaResponseWTO, response);
			
		} catch (InstanceNotFoundException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("InstanceNotFoundException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 500);
			
		} catch (InsufficientStockException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("InsufficientStockException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 500);
			
		} catch (BadFormatRequestException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("BadFormatRequestException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 400);
			
		} catch (IncorrectStateException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("IncorrectStateException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 400);
			
		} catch (JsonParseException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("JsonParseException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 400);
			
		}
	}
}

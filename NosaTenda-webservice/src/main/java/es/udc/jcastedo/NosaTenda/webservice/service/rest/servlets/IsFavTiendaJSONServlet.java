package es.udc.jcastedo.NosaTenda.webservice.service.rest.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.webservice.service.BooleanResponseWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.ErrorResponseWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils;

@SuppressWarnings("serial")
public class IsFavTiendaJSONServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(IsFavTiendaJSONServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de IsFavTiendaJSONServlet");
		
		try {
			
			Long clienteId = Long.valueOf(request.getParameter("clienteId"));
			Long tiendaId = Long.valueOf(request.getParameter("tiendaId"));
			
			BooleanResponseWTO isFav = new BooleanResponseWTO(tiendaService.isFavTienda(clienteId, tiendaId));
			
			ServletUtils.writeServiceResponse(isFav, response);
			
		} catch (InstanceNotFoundException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("InstanceNotFoundException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 500);
			
		} catch (NumberFormatException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("NumberFormatException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 400);
			
		}
	}
}

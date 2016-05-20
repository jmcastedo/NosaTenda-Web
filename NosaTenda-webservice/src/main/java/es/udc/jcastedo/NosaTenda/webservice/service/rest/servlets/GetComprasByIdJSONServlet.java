package es.udc.jcastedo.NosaTenda.webservice.service.rest.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.webservice.service.CompraWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.ErrorResponseWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.CompraWebserviceConversor;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils;

@SuppressWarnings("serial")
public class GetComprasByIdJSONServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(GetComprasByIdJSONServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de GetComprasByIdJSONServlet");
		
		try {
			
			Long clienteId = Long.valueOf(request.getParameter("clienteId"));
			
			List<CompraWTO> compras = CompraWebserviceConversor.toCompraWTO(
					compraService.getComprasByCliente(clienteId));
			
			ServletUtils.writeServiceResponse(compras, response);
			
		} catch (BadFormatRequestException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("BadFormatRequestException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 400);
			
		} catch (NumberFormatException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("NumberFormatException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 400);
			
		}
		
	}
}

package es.udc.jcastedo.NosaTenda.webservice.service.web.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.webservice.service.CompraWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.CompraWebserviceConversor;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;

@SuppressWarnings("serial")
public class GenerateFacturaServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(GenerateFacturaServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de GenerateFacturaServlet");
		
		try {
			
			Long compraId = Long.valueOf(request.getParameter("id"));
			
			CompraWTO compra = CompraWebserviceConversor.toCompraWTO(compraService.getCompraById(compraId));
			
			request.setAttribute("compra", compra);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("Factura.jsp");
			
			requestDispatcher.forward(request, response);
			
		} catch (NumberFormatException e) {
			
			handleException(e);
			
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
			
		} catch (BadFormatRequestException e) {
			
			handleException(e);
			
		}
		
	}
}

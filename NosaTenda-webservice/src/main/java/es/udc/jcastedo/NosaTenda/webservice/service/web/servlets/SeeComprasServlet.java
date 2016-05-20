package es.udc.jcastedo.NosaTenda.webservice.service.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.webservice.service.CompraWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.CompraWebserviceConversor;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;

@SuppressWarnings("serial")
public class SeeComprasServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(SeeComprasServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de SeeComprasServlet");
		
		try {
			
			List<CompraWTO> compraWTOs = CompraWebserviceConversor.toCompraWTO(
					compraService.getCompras());
		
			request.setAttribute("compraWTOs", compraWTOs);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("SeeCompras.jsp");
			
			requestDispatcher.forward(request, response);
		
		} catch (BadFormatRequestException e) {
			
			handleException(e);
			
		}
	}
}

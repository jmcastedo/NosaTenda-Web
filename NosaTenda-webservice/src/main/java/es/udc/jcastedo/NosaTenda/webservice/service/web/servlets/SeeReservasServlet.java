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
import es.udc.jcastedo.NosaTenda.webservice.service.ReservaInfoWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ReservaWebserviceConversor;

@SuppressWarnings("serial")
public class SeeReservasServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(SeeReservasServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de SeeReservasServlet");
		
		try {
			
			List<ReservaInfoWTO> reservaWTOs = ReservaWebserviceConversor.toReservaInfoWTO(
					reservaService.getReservas());
		
		
			request.setAttribute("reservaWTOs", reservaWTOs);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("SeeReservas.jsp");
			
			requestDispatcher.forward(request, response);
		
		} catch (BadFormatRequestException e) {
			
			handleException(e);
			
		}
	}
	
	
}

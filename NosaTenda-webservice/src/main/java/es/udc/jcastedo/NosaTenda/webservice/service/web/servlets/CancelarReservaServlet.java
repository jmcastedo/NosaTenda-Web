package es.udc.jcastedo.NosaTenda.webservice.service.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.IncorrectStateException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InsufficientStockException;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;

@SuppressWarnings("serial")
public class CancelarReservaServlet extends JSONHttpServlet {
	
	private static final Logger logger = LoggerFactory.getLogger(CancelarReservaServlet.class);

	@Override
	public void doPost(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doPost de CancelarReservaServlet");
		
		try {
			
			Long reservaId = Long.valueOf((String)request.getParameter("reservaId"));
		
			reservaService.cancelarReserva(reservaId);
			
			response.sendRedirect("CancelarReservaExito.jsp?id="
					+ reservaId);
			
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
			
		} catch (InsufficientStockException e) {

			handleException(e);
			
		} catch (BadFormatRequestException e) {

			handleException(e);
			
		} catch (IncorrectStateException e) {

			handleException(e);
			
		}

	}
}

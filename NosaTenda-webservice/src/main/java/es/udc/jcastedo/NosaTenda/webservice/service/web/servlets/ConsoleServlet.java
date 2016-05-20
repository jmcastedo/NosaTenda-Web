package es.udc.jcastedo.NosaTenda.webservice.service.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.webservice.service.TiendaWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.TiendaWebserviceConversor;

@SuppressWarnings("serial")
public class ConsoleServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(ConsoleServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de ConsoleServlet");
		
		try {
		
			if (this.hasRole("ROLE_ADMIN")) {
				
				List<TiendaWTO> tiendaWTOs = TiendaWebserviceConversor.toTiendaWTO(
						tiendaService.getTiendas());
				
				request.setAttribute("tiendaWTOs", tiendaWTOs);
				
			} else {
				
				if (this.hasRole(new String[]{"ROLE_ADMIN_TIENDA", "ROLE_USUARIO"})) {
					
					List<TiendaWTO> tiendaWTOs = TiendaWebserviceConversor.toTiendaWTO(
							tiendaService.getTiendasByEmpleado(request.getUserPrincipal().getName()));
					
					request.setAttribute("tiendaWTOs", tiendaWTOs);
					
				}
			}
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("Console.jsp");
			
			requestDispatcher.forward(request, response);
		
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
			
		}
		
	}
}

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
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.webservice.service.EmpleadoWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.RoleWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.EmpleadoWebserviceConversor;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.RoleWebserviceConversor;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.TiendaWebserviceConversor;

@SuppressWarnings("serial")
public class SeeEmpleadosServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(SeeEmpleadosServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de SeeEmpleadosServlet");
		
		try {
			
			List<EmpleadoWTO> empleadoWTOs = EmpleadoWebserviceConversor.toEmpleadoWTO(userService.getEmpleados());
			for (EmpleadoWTO empleado: empleadoWTOs) {
				
				 empleado.setTiendas(TiendaWebserviceConversor.toTiendaWTO(
						tiendaService.getTiendasByEmpleado(empleado.getCorreo())));
				
			}
			request.setAttribute("empleadoWTOs", empleadoWTOs);
		
			List<RoleWTO> roleWTOs = RoleWebserviceConversor.toRoleWTO(userService.getRoles());
			request.setAttribute("roleWTOs", roleWTOs);			
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("SeeEmpleados.jsp");
			
			requestDispatcher.forward(request, response);
		
		} catch (BadFormatRequestException e) {
			
			handleException(e);
			
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
		}
	}
}

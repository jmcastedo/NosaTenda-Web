package es.udc.jcastedo.NosaTenda.webservice.service.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;

@SuppressWarnings("serial")
public class ActivarEmpleadoServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(ActivarEmpleadoServlet.class);
		
	@Override
	public void doPost(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doPost de ActivarEmpleadoServlet");
		
		try {
			Long empleadoId = Long.valueOf((String)request.getParameter("id"));
			
			String operacion = (String)request.getParameter("op");
			
			Boolean result = false;
			
			if (operacion.equals("activar")) {
				result = userService.activateEmpleado(empleadoId);
			} else {
				result = userService.deactivateEmpleado(empleadoId);
			}
			
			if (result) {
				response.sendRedirect("SeeEmpleados");
			}
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
			
		}
	}
}

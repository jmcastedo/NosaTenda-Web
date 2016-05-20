package es.udc.jcastedo.NosaTenda.webservice.service.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;

@SuppressWarnings("serial")
public class EditarRoleServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(EditarRoleServlet.class);
	
	@Override
	public void doPost(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doPost de EditarRoleServlet");
		
		try {
			
			Long empleadoId = Long.valueOf((String)request.getParameter("empleadoId"));
			String role = (String)request.getParameter("role");
			
			Boolean result = userService.editEmpleadoRole(empleadoId, role);
			
			if (result) {
				response.sendRedirect("SeeEmpleados");
			}
			
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
			
		} catch (BadFormatRequestException e) {
			
			handleException(e);
			
		}
		
	
	}
}

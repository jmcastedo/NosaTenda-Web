package es.udc.jcastedo.NosaTenda.webservice.service.web.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.DuplicateInstanceException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.webservice.service.EmpleadoWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.EmpleadoWebserviceConversor;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.PropertyValidator;

@SuppressWarnings("serial")
public class EditarCorreoServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(EditarPasswordServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de EditarCorreoServlet");	
		
		try {
			
			String correo = request.getUserPrincipal().getName();
			
			EmpleadoWTO empleadoWTO = EmpleadoWebserviceConversor.toEmpleadoWTO(
					userService.getEmpleado(correo));
			
			request.setAttribute("empleado", empleadoWTO);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("EditarCorreo.jsp");
			
			requestDispatcher.forward(request, response);
			
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
			
		} catch (BadFormatRequestException e) {
			
			handleException(e);
			
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doPost de EditarCorreoServlet");
		
		Map<String, String> errors = new HashMap<String, String>();
		
		String correo = request.getUserPrincipal().getName();
		
		try {
			
			String correoNew = request.getParameter("correonew");
			String ccorreoNew = request.getParameter("ccorreonew");
			
			// validamos campos
			PropertyValidator.validateMandatory(errors, "correonew", correoNew);
			PropertyValidator.validateMandatory(errors, "ccorreonew", ccorreoNew);
			PropertyValidator.validateConfirmCorreo(errors, "correonew", correoNew, "ccorreonew", ccorreoNew);
			
			if (!errors.isEmpty()) {
				
				request.setAttribute("errors", errors);
				
				EmpleadoWTO empleadoWTO = EmpleadoWebserviceConversor.toEmpleadoWTO(
						userService.getEmpleado(correo));
				
				request.setAttribute("empleado", empleadoWTO);
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("EditarCorreo.jsp");
				
				requestDispatcher.forward(request, response);
				
			} else {
				
				Boolean result = userService.editEmpleadoCorreo(correo, correoNew);
				
				if (result) {
					request.logout();
					response.sendRedirect("EditarCorreoExito.jsp");
				}
			}
			
			
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
			
		} catch (DuplicateInstanceException e) {
			
			errors.put("correonew", "Correo duplicado");
			
			request.setAttribute("errors", errors);
			
			request.setAttribute("correo", correo);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("EditarCorreo.jsp");
			
			requestDispatcher.forward(request, response);
			
		} catch (BadFormatRequestException e) {
			
			handleException(e);
			
		}
	}
}

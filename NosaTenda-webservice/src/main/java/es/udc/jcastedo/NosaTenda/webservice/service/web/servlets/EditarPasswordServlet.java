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

import es.udc.jcastedo.NosaTenda.modelutil.exceptions.IncorrectPasswordException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.PropertyValidator;

@SuppressWarnings("serial")
public class EditarPasswordServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(EditarPasswordServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de EditarPasswordServlet");
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("EditarPassword.jsp");
		
		requestDispatcher.forward(request, response);

		
	}
	
	@Override
	public void doPost(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doPost de EditarPasswordServlet");
		
		Map<String, String> errors = new HashMap<String, String>();
		
		try {
			
			String correo = request.getUserPrincipal().getName();
			String password = request.getParameter("password");
			String passwordNew = request.getParameter("passwordnew");
			String cpasswordNew = request.getParameter("cpasswordnew");
			
			// validamos campos
			PropertyValidator.validateMandatory(errors, "password", password);
			PropertyValidator.validateMandatory(errors, "passwordnew", passwordNew);
			PropertyValidator.validateMandatory(errors, "cpasswordnew", cpasswordNew);
			PropertyValidator.validateConfirmPassword(errors, "passwordnew", passwordNew, "cpasswordnew", cpasswordNew);
			
			if (!errors.isEmpty()) {
				
				request.setAttribute("errors", errors);
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("EditarPassword.jsp");
				
				requestDispatcher.forward(request, response);
				
			} else {
				
				Boolean result = userService.editEmpleadoPassword(correo, password, passwordNew);
				
				if (result) {
					response.sendRedirect("EditarPasswordExito.jsp");
				}
			}
			
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
			
		} catch (IncorrectPasswordException e) {
			
			errors.put("password", "Contraseña incorrecta");
			
			request.setAttribute("errors", errors);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("EditarPassword.jsp");
			
			requestDispatcher.forward(request, response);
			
		}
		
	}
	
}

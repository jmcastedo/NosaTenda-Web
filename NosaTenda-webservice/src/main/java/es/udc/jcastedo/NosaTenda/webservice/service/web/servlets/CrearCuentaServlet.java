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

import es.udc.jcastedo.NosaTenda.modelutil.exceptions.DuplicateInstanceException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.PropertyValidator;

@SuppressWarnings("serial")
public class CrearCuentaServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(CrearCuentaServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de CrearCuentaServlet");
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("CrearCuenta.jsp");
		
		requestDispatcher.forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doPost de CrearCuentaServlet");
		
		Map<String, String> errors = new HashMap<String, String>();
		
		String correo = request.getParameter("correo");
		String password = request.getParameter("password");
		String cpassword = request.getParameter("cpassword");
		String nombre = request.getParameter("nombre");
		String nif = request.getParameter("nif");
		String apellidos = request.getParameter("apellidos");
		String direccion = request.getParameter("direccion");
		String localidad = request.getParameter("localidad");
		String cp = request.getParameter("cp");
		String provincia = request.getParameter("provincia");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		
		PropertyValidator.validateMandatory(errors, "correo", correo);
		PropertyValidator.validateMandatory(errors, "password", password);
		PropertyValidator.validateMandatory(errors, "cpassword", cpassword);
		PropertyValidator.validateConfirmPassword(errors, "password", password, "cpassword", cpassword);
		
		if (!errors.isEmpty()) {
			
			request.setAttribute("errors", errors);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("CrearCuenta.jsp");
			
			requestDispatcher.forward(request, response);
			
		} else {
			
			try {
			
				Long empleadoId = userService.newEmpleado(correo, cpassword);
			
			
				Boolean result = userService.editEmpleado(correo, nif, nombre,
						apellidos, direccion, localidad, provincia, cp, phone1, phone2);
				
				if (result) {
					
					response.sendRedirect("CrearCuentaExito.jsp?id="
							+ empleadoId);
					
				}
			} catch (InstanceNotFoundException e) {
				
				handleException(e);
				
			} catch (DuplicateInstanceException e) {
				
				errors.put("correo", "Correo duplicado");
				
				request.setAttribute("errors", errors);
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("CrearCuenta.jsp");
				
				requestDispatcher.forward(request, response);
				
			}
			
			
		}
		
		
	}
}

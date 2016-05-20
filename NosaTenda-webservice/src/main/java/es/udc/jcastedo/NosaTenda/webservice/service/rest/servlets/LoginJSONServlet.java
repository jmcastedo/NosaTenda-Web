package es.udc.jcastedo.NosaTenda.webservice.service.rest.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.modelutil.exceptions.IncorrectPasswordException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;

import es.udc.jcastedo.NosaTenda.webservice.service.ClienteResponseWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.ErrorResponseWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils;

@SuppressWarnings("serial")
public class LoginJSONServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(LoginJSONServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de LoginJSONServlet");
		
		try {
			
			String correo = request.getParameter("correo");
			String password = request.getParameter("password");
			
			Long clienteId = userService.loginCliente(correo, password);
			
			ClienteResponseWTO clienteResponseWTO = new ClienteResponseWTO(clienteId);
			
			ServletUtils.writeServiceResponse(clienteResponseWTO, response);
			
		} catch (InstanceNotFoundException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("InstanceNotFoundException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 500);
			
		} catch (IncorrectPasswordException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("IncorrectPasswordException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 400);
			
		}
	}
}

package es.udc.jcastedo.NosaTenda.webservice.service.rest.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.udc.jcastedo.NosaTenda.modelutil.exceptions.DuplicateInstanceException;
import es.udc.jcastedo.NosaTenda.webservice.service.ClienteResponseWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.ClienteWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.ErrorResponseWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils;

@SuppressWarnings("serial")
public class NewClienteJSONServlet extends JSONHttpServlet {
	
	private static final Logger logger = LoggerFactory.getLogger(NewClienteJSONServlet.class);
	
	@Override
	public void doPost(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doPost de NewClienteJSONServlet");
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			
			ClienteWTO clienteWTO = new ClienteWTO();
			
			clienteWTO = mapper.readValue(request.getInputStream(), ClienteWTO.class);
			
			/*Long clienteId = productoService.newCliente(clienteWTO.getNombre(),
														clienteWTO.getCorreo(),
														clienteWTO.getPassword());*/
		
		
			Long clienteId = userService.newCliente(clienteWTO.getCorreo(),
														clienteWTO.getPassword());
			
			ClienteResponseWTO clienteResponseWTO = new ClienteResponseWTO(clienteId);
			
			ServletUtils.writeServiceResponse(clienteResponseWTO, response);
			
		} catch (DuplicateInstanceException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("DuplicateInstanceException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 400);
			
		} catch (JsonParseException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("JsonParseException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 400);
			
		}
		
		
	}
}

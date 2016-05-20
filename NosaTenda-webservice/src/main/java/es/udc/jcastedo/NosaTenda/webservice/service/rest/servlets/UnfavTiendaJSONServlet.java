package es.udc.jcastedo.NosaTenda.webservice.service.rest.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.udc.jcastedo.NosaTenda.webservice.service.ErrorResponseWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.FavoritoWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils;

@SuppressWarnings("serial")
public class UnfavTiendaJSONServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(UnfavTiendaJSONServlet.class);
	
	@Override
	public void doPost(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doPost de UnfavTiendaJSONServlet");
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			
			FavoritoWTO favoritoWTO = new FavoritoWTO();
			
			favoritoWTO = mapper.readValue(request.getInputStream(), FavoritoWTO.class);
			
			tiendaService.unfavTienda(favoritoWTO.getClienteId(), favoritoWTO.getTiendaId());
			
		} catch (JsonParseException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("JsonParseException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 400);
			
		}
	}
}

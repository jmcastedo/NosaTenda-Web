package es.udc.jcastedo.NosaTenda.webservice.service.rest.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.webservice.service.ErrorResponseWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.TiendaWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.TiendaWebserviceConversor;

@SuppressWarnings("serial")
public class GetTiendasCercanasJSONServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(GetTiendasCercanasJSONServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de GetTiendasCercanasJSONServlet");
		
		try {
			
			Double lat = Double.valueOf(request.getParameter("lat"));
			Double lon = Double.valueOf(request.getParameter("lon"));
			double distance = Double.valueOf(request.getParameter("dist"));
			
			List<TiendaWTO> tiendasCercanas = TiendaWebserviceConversor.toTiendaWTO(
									tiendaService.getTiendasCercanas(lat, lon, distance));
			
			ServletUtils.writeServiceResponse(tiendasCercanas, response);
			
		} catch (NumberFormatException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("NumberFormatException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 400);
			
		}
	}
}

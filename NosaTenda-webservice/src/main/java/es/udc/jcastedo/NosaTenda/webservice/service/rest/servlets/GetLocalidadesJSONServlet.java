package es.udc.jcastedo.NosaTenda.webservice.service.rest.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.webservice.service.LocalidadWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.LocalidadWebserviceConversor;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils;

@SuppressWarnings("serial")
public class GetLocalidadesJSONServlet extends JSONHttpServlet {
	
	private static final Logger logger = LoggerFactory.getLogger(GetLocalidadesJSONServlet.class);

	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de GetLocalidadesJSONServlet");
		
		List<LocalidadWTO> localidades = new ArrayList<LocalidadWTO>();
		
		localidades = LocalidadWebserviceConversor.toLocalidadWTO(
				productoService.getLocalidades());
		
		ServletUtils.writeServiceResponse(localidades, response);
	}
}

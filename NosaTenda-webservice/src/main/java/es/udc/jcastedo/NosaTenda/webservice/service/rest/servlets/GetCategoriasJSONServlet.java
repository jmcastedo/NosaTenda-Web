package es.udc.jcastedo.NosaTenda.webservice.service.rest.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.webservice.service.CategoriaWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.CategoriaWebserviceConversor;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils;

@SuppressWarnings("serial")
public class GetCategoriasJSONServlet extends JSONHttpServlet {
	
	private static final Logger logger = LoggerFactory.getLogger(GetCategoriasJSONServlet.class);

	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doPost de GetCategoriasJSONServlet");
		
		List<CategoriaWTO> categoriaWTOs = new ArrayList<CategoriaWTO>();
		
		categoriaWTOs = CategoriaWebserviceConversor.toCategoriaWTO(
				productoService.getCategorias());
		
		ServletUtils.writeServiceResponse(categoriaWTOs, response);
	}
}

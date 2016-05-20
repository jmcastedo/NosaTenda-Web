package es.udc.jcastedo.NosaTenda.webservice.service.rest.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.webservice.service.ProductoWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ProductoWebserviceConversor;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils;

@SuppressWarnings("serial")
public class GetProductosJSONServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(GetProductosJSONServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de GetProductosJSONServlet");
		
		List<ProductoWTO> productoWTOs = new ArrayList<ProductoWTO>();
		
		productoWTOs = ProductoWebserviceConversor.toProductoWTO(
				productoService.getProductos());

		ServletUtils.writeServiceResponse(productoWTOs, response);
	}

}

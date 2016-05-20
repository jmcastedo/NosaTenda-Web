package es.udc.jcastedo.NosaTenda.webservice.service.rest.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.webservice.service.ErrorResponseWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.ProductoWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ProductoWebserviceConversor;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils;

@SuppressWarnings("serial")
public class GetProductosByLocalidadJSONServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(GetProductosByLocalidadJSONServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de GetProductosByLocalidadJSONServlet");
		
		try {
			
			Long localidadId = Long.valueOf(request.getParameter("localidadId"));
			
			List<ProductoWTO> productos = new ArrayList<ProductoWTO>();
			
			productos = ProductoWebserviceConversor.toProductoWTO(
					productoService.getProductosByLocalidadEnVenta(localidadId));
			
			ServletUtils.writeServiceResponse(productos, response);
			
		} catch (NumberFormatException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("NumberFormatException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 400);
			
		}
	}
}

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
public class GetProductosByCategoriaTiendaJSONServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(GetProductosByCategoriaTiendaJSONServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de GetProductosByCategoriaTiendaJSONServlet");
		
		try {
			
			Long categoriaId = Long.valueOf(request.getParameter("categoriaId"));
			
			List<ProductoWTO> productoWTOs = new ArrayList<ProductoWTO>();
			
			productoWTOs = ProductoWebserviceConversor.toProductoWTO(
					productoService.getProductosByCategoriaTiendaEnVenta(categoriaId));
			
			ServletUtils.writeServiceResponse(productoWTOs, response);
			
		} catch (NumberFormatException e) {

			ErrorResponseWTO error = new ErrorResponseWTO("NumberFormatException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 400);
			
		}
	}
}

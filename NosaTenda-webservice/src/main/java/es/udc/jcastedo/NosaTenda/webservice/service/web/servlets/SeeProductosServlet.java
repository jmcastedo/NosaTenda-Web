package es.udc.jcastedo.NosaTenda.webservice.service.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.webservice.service.ProductoWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ProductoWebserviceConversor;

@SuppressWarnings("serial")
public class SeeProductosServlet extends JSONHttpServlet {
	
	private static final Logger logger = LoggerFactory.getLogger(SeeProductosServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de SeeProductosServlet");
		
		List<ProductoWTO> productoWTOs = new ArrayList<ProductoWTO>();
		
		productoWTOs = ProductoWebserviceConversor.toProductoWTO(
				productoService.getProductos());
		
		request.setAttribute("productoWTOs", productoWTOs);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("SeeProductos.jsp");
		
		requestDispatcher.forward(request, response);
		
	}
}

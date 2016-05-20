package es.udc.jcastedo.NosaTenda.webservice.service.web.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.webservice.service.ProductoWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ProductoWebserviceConversor;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.PropertyValidator;

@SuppressWarnings("serial")
public class SearchProductoServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(SearchProductoServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de SearchProductoServlet");
		
		Map<String, String> errors = new HashMap<String, String>();
		
		String nombre = request.getParameter("nombre");
		String stock = request.getParameter("stock");
		
		Long stockAsLong;
		
		if (stock.equals("")) {
			stockAsLong = null;
		} else {
			stockAsLong = PropertyValidator.validateLong(
					errors, "stock", stock, false, 0, Long.MAX_VALUE);
		}
		
		if (!errors.isEmpty()) {
			
			request.setAttribute("errors", errors);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("SearchProducto.jsp");
			
			requestDispatcher.forward(request, response);
			
		} else {
		
			List<ProductoWTO> productoWTOs = ProductoWebserviceConversor.toProductoWTO(
					productoService.searchProductos(nombre, stockAsLong));
			
			request.setAttribute("productoWTOs", productoWTOs);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("SearchProductoResultados.jsp");
			
			requestDispatcher.forward(request, response);
			
		}
	}
}

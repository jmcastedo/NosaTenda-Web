package es.udc.jcastedo.NosaTenda.webservice.service.web.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.webservice.service.ProductoWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ProductoWebserviceConversor;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.PropertyValidator;

@SuppressWarnings("serial")
public class ModificarPrecioServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(ModificarPrecioServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de ModificarPrecioServlet");
		
		try {
			
			Long productoId = Long.valueOf((String)request.getParameter("id"));
			
			ProductoWTO productoWTO = ProductoWebserviceConversor.toProductoWTO(productoService.findProductoById(productoId));
			request.setAttribute("producto", productoWTO);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("ModificarPrecio.jsp");
			
			requestDispatcher.forward(request, response);
			
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
			
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doPost de ModificarPrecioServlet");
		
		Map<String, String> errors = new HashMap<String, String>();
		
		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String precio = request.getParameter("precio");
		
		// validamos campos
		Long idAsLong = PropertyValidator.validateLong(errors,
				"id", id, true, 0, Long.MAX_VALUE);
		PropertyValidator.validateMandatory(errors, "nombre", nombre);
		Double precioAsDouble = PropertyValidator.validateDouble(errors,
				"precio", precio, true, 0, Double.MAX_VALUE);
		
		if (!errors.isEmpty()) {
			
			request.setAttribute("errors", errors);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("ModificarPrecio.jsp");
			
			requestDispatcher.forward(request, response);
			
		} else {
			
			try {
				ProductoWTO editedProducto = ProductoWebserviceConversor.toProductoWTO(
						productoService.editPrecio(idAsLong, precioAsDouble));
				
				response.sendRedirect("EditarProductoExito.jsp?id="
						+ editedProducto.getId());
				
			} catch (InstanceNotFoundException e) {
				
				handleException(e);
			}
		}
	}
}

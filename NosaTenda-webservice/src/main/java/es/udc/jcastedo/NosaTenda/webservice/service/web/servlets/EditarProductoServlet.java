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

import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.webservice.service.ProductoWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.TiendaWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ProductoWebserviceConversor;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.PropertyValidator;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.TiendaWebserviceConversor;

@SuppressWarnings("serial")
public class EditarProductoServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(EditarProductoServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de EditarProductoServlet");
		
		try {
			
			Long productoId = Long.valueOf((String)request.getParameter("id"));
		
			ProductoWTO productoWTO = ProductoWebserviceConversor.toProductoWTO(productoService.findProductoById(productoId));
			request.setAttribute("producto", productoWTO);
			
			List<TiendaWTO> tiendaWTOs = TiendaWebserviceConversor.toTiendaWTO(
					tiendaService.getTiendasByEmpleado(request.getUserPrincipal().getName()));
			request.setAttribute("tiendas", tiendaWTOs);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("EditarProducto.jsp");
			
			requestDispatcher.forward(request, response);
			
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
			
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doPost de EditarProductoServlet");
		
		try {
			
			Map<String, String> errors = new HashMap<String, String>();
			
			String id = request.getParameter("id");
			String nombre = request.getParameter("nombre");
			String descripcion = request.getParameter("descripcion");
			String imagen = request.getParameter("imagen");
	//		String precio = request.getParameter("precio");
	//		String stock = request.getParameter("stock");
	//		String tiendaId = request.getParameter("tiendaId");
			
			// validamos campos
			Long idAsLong = PropertyValidator.validateLong(errors,
					"id", id, true, 0, Long.MAX_VALUE);
			PropertyValidator.validateMandatory(errors, "nombre", nombre);
			PropertyValidator.validateMandatory(errors, "imagen", imagen);
	//		Double precioAsDouble = PropertyValidator.validateDouble(errors,
	//				"precio", precio, true, 0, Double.MAX_VALUE);
	//		Long stockAsLong = PropertyValidator.validateLong(errors,
	//				"stock", stock, true, 0, Long.MAX_VALUE);
	//		Long tiendaIdAsLong = PropertyValidator.validateLong(errors,
	//				"tiendaId", tiendaId, true, 0, Long.MAX_VALUE);
			
			if (!errors.isEmpty()) {
				
				request.setAttribute("errors", errors);
				
				List<TiendaWTO> tiendaWTOs = TiendaWebserviceConversor.toTiendaWTO(
						tiendaService.getTiendasByEmpleado(request.getUserPrincipal().getName()));
				request.setAttribute("tiendas", tiendaWTOs);
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("EditarProducto.jsp");
				
				requestDispatcher.forward(request, response);
				
			} else {
					
					ProductoWTO producto = ProductoWebserviceConversor.toProductoWTO(
							productoService.findProductoById(idAsLong));
					
					/*ProductoWTO editedProducto = ProductoWebserviceConversor.toProductoWTO(
							productoService.editProducto(
														idAsLong,
														nombre,
														descripcion,
														imagen,
														precioAsDouble,
														stockAsLong,
														tiendaIdAsLong));
					*/
					ProductoWTO editedProducto = ProductoWebserviceConversor.toProductoWTO(
							productoService.editProducto(
														idAsLong,
														nombre,
														descripcion,
														imagen,
														producto.getPrecio(),
														producto.getStock(),
														producto.getTienda().getId()));
					
					response.sendRedirect("EditarProductoExito.jsp?id="
							+ editedProducto.getId());
				
			}
		
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
			
		}
	}
}

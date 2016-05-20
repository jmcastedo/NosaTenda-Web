package es.udc.jcastedo.NosaTenda.webservice.service.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
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
import es.udc.jcastedo.NosaTenda.webservice.service.CategoriaWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.ProductoWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.TiendaWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.CategoriaWebserviceConversor;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ProductoWebserviceConversor;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.PropertyValidator;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.TiendaWebserviceConversor;

@SuppressWarnings("serial")
public class AddProductoServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(AddProductoServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de AddProductoServlet");
		
		try {
			
			List<TiendaWTO> tiendaWTOs = new ArrayList<TiendaWTO>();
			
			if (this.hasRole("ROLE_ADMIN")) {
				
				tiendaWTOs = TiendaWebserviceConversor.toTiendaWTO(
						tiendaService.getTiendas());
				
			} else {
				
				tiendaWTOs = TiendaWebserviceConversor.toTiendaWTO(
						tiendaService.getTiendasByEmpleado(request.getUserPrincipal().getName()));
				
			}

			request.setAttribute("tiendas", tiendaWTOs);
			
			List<CategoriaWTO> categoriaWTOs = CategoriaWebserviceConversor.toCategoriaWTO(
					productoService.getCategorias());
			request.setAttribute("categorias", categoriaWTOs);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("AddProducto.jsp");
			
			requestDispatcher.forward(request, response);
		
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doPost de AddProductoServlet");
		
		try {
		
			Map<String, String> errors = new HashMap<String, String>();
			
			String nombre = request.getParameter("nombre");
			String descripcion = request.getParameter("descripcion");
			String imagen = request.getParameter("imagen");
			String precio = request.getParameter("precio");
			String tax = request.getParameter("tax");
			String stock = request.getParameter("stock");
			String tiendaId = request.getParameter("tiendaId");
			
			// validamos campos
			PropertyValidator.validateMandatory(errors, "nombre", nombre);
			PropertyValidator.validateMandatory(errors, "imagen", imagen);
			Double precioAsDouble = PropertyValidator.validateDouble(errors,
					"precio", precio, true, 0, Double.MAX_VALUE);
			Long stockAsLong = PropertyValidator.validateLong(errors,
					"stock", stock, true, 0, Long.MAX_VALUE);
			Long tiendaIdAsLong = PropertyValidator.validateLong(errors,
					"tiendaId", tiendaId, true, 0, Long.MAX_VALUE);
			Double taxAsDouble = PropertyValidator.validateDouble(errors,
					"tax", tax, true, 0, 99);
			
			if (!errors.isEmpty()) {
				
				request.setAttribute("errors", errors);
				
				List<TiendaWTO> tiendaWTOs = TiendaWebserviceConversor.toTiendaWTO(
						tiendaService.getTiendasByEmpleado(request.getUserPrincipal().getName()));
				request.setAttribute("tiendas", tiendaWTOs);
				
				List<CategoriaWTO> categoriaWTOs = CategoriaWebserviceConversor.toCategoriaWTO(
						productoService.getCategorias());
				request.setAttribute("categorias", categoriaWTOs);
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("AddProducto.jsp");
				
				requestDispatcher.forward(request, response);
				
			} else {
				
				
					
					ProductoWTO insertedProducto = ProductoWebserviceConversor.toProductoWTO(
							productoService.newProducto(
														nombre,
														descripcion,
														imagen,
														precioAsDouble,
														stockAsLong,
														taxAsDouble,
														tiendaIdAsLong));
					
					response.sendRedirect("AddProductoExito.jsp?id="
							+ insertedProducto.getId());
					
				
				
			}
		
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
			
		}
	}
}

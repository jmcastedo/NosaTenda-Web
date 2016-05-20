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

import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.webservice.service.ProductoWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.TiendaWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ProductoWebserviceConversor;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.TiendaWebserviceConversor;

@SuppressWarnings("serial")
public class SeeAllProductosServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(SeeAllProductosServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de SeeAllProductosServlet");
		
		try {
			
			List<ProductoWTO> productoWTOs = new ArrayList<ProductoWTO>();
			
			// if admin buscar todos los productos en BD
			//if (request.isUserInRole("ROLE_ADMIN")) {
			if (this.hasRole("ROLE_ADMIN")) {
				
				productoWTOs = ProductoWebserviceConversor.toProductoWTO(
						productoService.getProductos());
				
			// else buscar los productos de la tienda del empleado
			} else {
				
				// obtenemos el correo del empleado y a partir de el las tiendas para las que trabaja
				String empleadoCorreo = request.getUserPrincipal().getName();
				List<TiendaWTO> tiendaWTOs = TiendaWebserviceConversor.toTiendaWTO(
						tiendaService.getTiendasByEmpleado(empleadoCorreo));
				
				for (TiendaWTO tienda: tiendaWTOs) {
					
					productoWTOs.addAll(ProductoWebserviceConversor.toProductoWTO(productoService.getProductosByTienda(tienda.getId())));
				}
				
			}
			
			List<ProductoWTO> productoWTOsEnVenta = ProductoWebserviceConversor.toProductoWTO(
					productoService.getProductosEnVentaYAgotados());
			
			request.setAttribute("productoWTOs", productoWTOs);
			request.setAttribute("productoWTOsEnVenta", productoWTOsEnVenta);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("SeeAllProductos.jsp");
			
			requestDispatcher.forward(request, response);
		
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
			
		}
		
		
		
	}
}

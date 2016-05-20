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
import es.udc.jcastedo.NosaTenda.webservice.service.CategoriaWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.LocalidadWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.TiendaWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.CategoriaWebserviceConversor;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.LocalidadWebserviceConversor;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.PropertyValidator;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.TiendaWebserviceConversor;

@SuppressWarnings("serial")
public class EditarTiendaServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(EditarTiendaServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de EditarTiendaServlet");
		
		try {
			
			Long tiendaId = Long.valueOf((String)request.getParameter("id"));
			
			TiendaWTO tiendaWTO = TiendaWebserviceConversor.toTiendaWTO(
					tiendaService.getTiendaById(tiendaId));
			request.setAttribute("tienda", tiendaWTO);
			
			// categorias de la tienda
			List<CategoriaWTO> tiendaCategoriaWTOs = CategoriaWebserviceConversor.toCategoriaWTO(
					tiendaService.getCategoriasByTienda(tiendaId));
			request.setAttribute("categoriasTienda", tiendaCategoriaWTOs);
			
			// todas las categorias disponibles
			List<CategoriaWTO> categoriaWTOs = CategoriaWebserviceConversor.toCategoriaWTO(
					productoService.getCategorias());
			request.setAttribute("categorias", categoriaWTOs);
			
			List<LocalidadWTO> localidadWTOs = LocalidadWebserviceConversor.toLocalidadWTO(
					productoService.getLocalidades());
			request.setAttribute("localidades", localidadWTOs);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("EditarTienda.jsp");
			
			requestDispatcher.forward(request, response);
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
			
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doPost de EditarTiendaServlet");
		
		Map<String, String> errors = new HashMap<String, String>();
		
		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String nif = request.getParameter("nif");
		String correo = request.getParameter("correo");
		String web = request.getParameter("web");
		String imagen = request.getParameter("imagen");
		String direccion = request.getParameter("direccion");
		String localidadId = request.getParameter("localidadId");
		String cp = request.getParameter("cp");
		String provincia = request.getParameter("provincia");
		String lat = request.getParameter("lat");
		String lon = request.getParameter("lon");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String fax = request.getParameter("fax");
		String[] categorias = request.getParameterValues("categorias_group");
		
		
		// validamos campos
		Long idAsLong = PropertyValidator.validateLong(
				errors, "id", id, true, 0, Long.MAX_VALUE);
		PropertyValidator.validateMandatory(errors, "nombre", nombre);
		PropertyValidator.validateMandatory(errors, "nif", nif);
		PropertyValidator.validateMandatory(errors, "correo", correo);
		PropertyValidator.validateMandatory(errors, "imagen", imagen);
		PropertyValidator.validateMandatory(errors, "direccion", direccion);
		PropertyValidator.validateMandatory(errors, "phone1", phone1);
		Long localidadIdAsLong = PropertyValidator.validateLong(
				errors, "localidadId", localidadId, true, 0, Long.MAX_VALUE);
		Double latAsDouble = PropertyValidator.validateDouble(
				errors, "lat", lat, true, Double.MIN_VALUE, Double.MAX_VALUE);
		Double lonAsDouble = PropertyValidator.validateDouble(
				errors, "lon", lon, true, Double.NEGATIVE_INFINITY, Double.MAX_VALUE);
		
		if (!errors.isEmpty()) {
			
			request.setAttribute("errors", errors);
			
			// categorias de la tienda
			List<CategoriaWTO> tiendaCategoriaWTOs = CategoriaWebserviceConversor.toCategoriaWTO(
					tiendaService.getCategoriasByTienda(idAsLong));
			request.setAttribute("categoriasTienda", tiendaCategoriaWTOs);
			
			// todas las categorias disponibles
			List<CategoriaWTO> categoriaWTOs = CategoriaWebserviceConversor.toCategoriaWTO(
					productoService.getCategorias());
			request.setAttribute("categorias", categoriaWTOs);
			
			List<LocalidadWTO> localidadWTOs = LocalidadWebserviceConversor.toLocalidadWTO(
					productoService.getLocalidades());
			request.setAttribute("localidades", localidadWTOs);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("EditarTienda.jsp");
			
			requestDispatcher.forward(request, response);
			
		} else {
			
			try {
				
				TiendaWTO tiendaWTO = TiendaWebserviceConversor.toTiendaWTO(
						tiendaService.editTienda(idAsLong, nombre, direccion, provincia, cp, nif,
								correo, web, phone1, phone2, fax, imagen,
								latAsDouble, lonAsDouble, localidadIdAsLong));
				
				response.sendRedirect("EditarTiendaExito.jsp?id="
						+ tiendaWTO.getId());
				
			} catch (InstanceNotFoundException e) {
				
				handleException(e);
				
			}
			
			
			
		}

	}
}

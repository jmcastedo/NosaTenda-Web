package es.udc.jcastedo.NosaTenda.webservice.service.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.webservice.service.CategoriaWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.TiendaWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.CategoriaWebserviceConversor;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.TiendaWebserviceConversor;

@SuppressWarnings("serial")
public class DetallesTiendaServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(DetallesTiendaServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de DetallesTiendaServlet");
		
		try {
			
			Long idTienda = Long.valueOf((String)request.getParameter("id"));
			
			TiendaWTO tiendaWTO = TiendaWebserviceConversor.toTiendaWTO(
					tiendaService.getTiendaById(idTienda));
			
			request.setAttribute("tienda", tiendaWTO);
			
			List<CategoriaWTO> categoriaWTOs = CategoriaWebserviceConversor.toCategoriaWTO(
					tiendaService.getCategoriasByTienda(idTienda));
			
			request.setAttribute("categorias", categoriaWTOs);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("DetallesTienda.jsp");
			
			requestDispatcher.forward(request, response);
			
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
		}
	}
}

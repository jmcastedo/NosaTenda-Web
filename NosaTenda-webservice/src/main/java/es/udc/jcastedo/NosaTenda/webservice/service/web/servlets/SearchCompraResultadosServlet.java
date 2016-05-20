package es.udc.jcastedo.NosaTenda.webservice.service.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.webservice.service.CompraWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.TiendaWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.CompraWebserviceConversor;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.TiendaWebserviceConversor;

@SuppressWarnings("serial")
public class SearchCompraResultadosServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(SearchCompraResultadosServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de SearchCompraResultadosServlet");
		
		try {
			
			Long tiendaId = Long.valueOf(request.getParameter("tiendaId"));
			
			TiendaWTO tienda = TiendaWebserviceConversor.toTiendaWTO(
					tiendaService.getTiendaById(tiendaId));
			
			request.setAttribute("tienda", tienda.getNombre());
		
			List<CompraWTO> compraWTOs = CompraWebserviceConversor.toCompraWTO(
					compraService.getComprasByTienda(tiendaId));
			
			request.setAttribute("compras", compraWTOs);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("SearchCompraResultados.jsp");
			
			requestDispatcher.forward(request, response);
		
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
			
		} catch (BadFormatRequestException e) {
			
			handleException(e);
			
		}
	}
}

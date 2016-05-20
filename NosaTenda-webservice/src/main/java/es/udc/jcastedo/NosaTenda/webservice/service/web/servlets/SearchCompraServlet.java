package es.udc.jcastedo.NosaTenda.webservice.service.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.webservice.service.TiendaWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.TiendaWebserviceConversor;

@SuppressWarnings("serial")
public class SearchCompraServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(SearchCompraServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de SearchCompraServlet");
		
		List<TiendaWTO> tiendaWTOs = TiendaWebserviceConversor.toTiendaWTO(tiendaService.getTiendas());
		
		request.setAttribute("tiendas", tiendaWTOs);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("SearchCompra.jsp");
		
		requestDispatcher.forward(request, response);
	}
}

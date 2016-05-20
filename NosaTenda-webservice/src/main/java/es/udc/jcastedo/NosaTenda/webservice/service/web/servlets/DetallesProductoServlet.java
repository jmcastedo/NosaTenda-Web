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
import es.udc.jcastedo.NosaTenda.webservice.service.ProductoVentasBlockWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.ProductoWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ProductoWebserviceConversor;

@SuppressWarnings("serial")
public class DetallesProductoServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(DetallesProductoServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de DetallesProductoServlet");
		
		try {
			
			Long productoId = Long.valueOf((String)request.getParameter("id"));
			
			ProductoWTO productoWTO = ProductoWebserviceConversor.toProductoWTO(
					productoService.findProductoById(productoId));
			
			request.setAttribute("producto", productoWTO);
			
			List<ProductoVentasBlockWTO> productoVentasBlockWTOs =
					ProductoWebserviceConversor.toProductoVentasBlockWTO(
							productoService.getHistoricoVentasByProducto(productoId));
			
			request.setAttribute("productoVentasBlockWTOs", productoVentasBlockWTOs);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("DetallesProducto.jsp");
			
			requestDispatcher.forward(request, response);
			
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
			
		}
		
	}
}

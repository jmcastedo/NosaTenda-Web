package es.udc.jcastedo.NosaTenda.webservice.service.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;

@SuppressWarnings("serial")
public class BorrarProductoServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(BorrarProductoServlet.class);
	
	@Override
	public void doPost(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doPost de BorrarProductoServlet");
		
		try {
			
			Long productoId = Long.valueOf((String)request.getParameter("productoId"));
		
			productoService.deleteProducto(productoId);
			
			response.sendRedirect("BorrarProductoExito.jsp?id="
					+ productoId);
			
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
			
		}
		
	}
}

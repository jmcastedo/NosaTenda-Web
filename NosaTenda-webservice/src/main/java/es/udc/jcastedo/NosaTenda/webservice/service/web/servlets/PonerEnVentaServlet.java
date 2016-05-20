package es.udc.jcastedo.NosaTenda.webservice.service.web.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class PonerEnVentaServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(PonerEnVentaServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de PonerEnVentaServlet");
		
		try {
			
			Long productoId = Long.valueOf((String)request.getParameter("id"));
			
			ProductoWTO productoWTO = ProductoWebserviceConversor.toProductoWTO(
					productoService.findProductoById(productoId));
			
			request.setAttribute("producto", productoWTO);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("PonerEnVenta.jsp");
			
			requestDispatcher.forward(request, response);
			
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
			
		}
		
	}
	
	@Override
	public void doPost(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doPost de PonerEnVentaServlet");
		
		try {
		
			Map<String, String> errors = new HashMap<String, String>();
			
			String id = request.getParameter("id");
			String precio = request.getParameter("precio");
			String stock = request.getParameter("stock");
			String fecha_retirada = request.getParameter("fecha_retirada");
			
			Long idAsLong = PropertyValidator.validateLong(errors,
					"id", id, true, 0, Long.MAX_VALUE);
			Double precioAsDouble = PropertyValidator.validateDouble(errors,
					"precio", precio, true, 0, Double.MAX_VALUE);
			Long stockAsLong = PropertyValidator.validateLong(errors,
					"stock", stock, true, 0, Long.MAX_VALUE);
		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			
			// fecha de retirada como máximo a dos meses vista
			Calendar upper_value = Calendar.getInstance();
			upper_value.add(Calendar.MONTH, 2);
			
			Calendar fecha_retiradaAsCalendar = PropertyValidator.validateCalendar(errors,
					"fecha_retirada", fecha_retirada, true, sdf, Calendar.getInstance(), upper_value);
			
			if (!errors.isEmpty()) {
				
				request.setAttribute("errors", errors);
				
				Long productoId = Long.valueOf((String)request.getParameter("id"));
				
				ProductoWTO productoWTO = ProductoWebserviceConversor.toProductoWTO(
						productoService.findProductoById(productoId));
				
				request.setAttribute("producto", productoWTO);
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("PonerEnVenta.jsp");
				
				requestDispatcher.forward(request, response);
			} else {
				
				ProductoWTO productoEnVenta = ProductoWebserviceConversor.toProductoWTO(
						productoService.ponerEnVenta(
													idAsLong,
													precioAsDouble,
													stockAsLong,
													Calendar.getInstance(),
													fecha_retiradaAsCalendar));
				
				response.sendRedirect("PonerEnVentaExito.jsp?id="
						+ productoEnVenta.getId());
				
			}
			
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
			
		}
	}
}

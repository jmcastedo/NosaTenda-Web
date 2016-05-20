package es.udc.jcastedo.NosaTenda.webservice.service.rest.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InsufficientStockException;
import es.udc.jcastedo.NosaTenda.webservice.service.ErrorResponseWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.StockResponseWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils;

@SuppressWarnings("serial")
public class GetStockJSONServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(GetStockJSONServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de GetStockJSONServlet");
		
		try {
			
			Long productoId = Long.valueOf((String)request.getParameter("productoId"));
			Long cantidad = new Long(0);
		
			StockResponseWTO stockResponse = new StockResponseWTO(productoService.actualizarStock(productoId, cantidad, "up"));
			
			ServletUtils.writeServiceResponse(stockResponse, response);
			
		} catch (InstanceNotFoundException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("InstanceNotFoundException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 500);
			
		} catch (BadFormatRequestException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("BadFormatRequestException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 400);
			
		} catch (InsufficientStockException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("InsufficientStockException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 500);
			
		} catch (NumberFormatException e) {
			
			ErrorResponseWTO error = new ErrorResponseWTO("NumberFormatException", e.getClass().toString(), e.getMessage());
			
			ServletUtils.writeServiceResponseError(error, response, 400);
			
		}
	}
}

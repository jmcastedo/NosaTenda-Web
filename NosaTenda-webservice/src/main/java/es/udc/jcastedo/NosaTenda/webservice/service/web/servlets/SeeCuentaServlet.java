package es.udc.jcastedo.NosaTenda.webservice.service.web.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.webservice.service.EmpleadoInfoWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.EmpleadoWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.EmpleadoWebserviceConversor;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.JSONHttpServlet;

@SuppressWarnings("serial")
public class SeeCuentaServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(SeeCuentaServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de SeeCuentaServlet");

		try {
			
			String correo = request.getUserPrincipal().getName();
			
			EmpleadoWTO empleadoWTO = EmpleadoWebserviceConversor.toEmpleadoWTO(
					userService.getEmpleado(correo));
			
			EmpleadoInfoWTO empleadoInfoWTO = EmpleadoWebserviceConversor.toEmpleadoInfoWTO(
					userService.getEmpleadoData(empleadoWTO.getId()));
			
			request.setAttribute("empleado", empleadoWTO);
			request.setAttribute("empleadoInfo", empleadoInfoWTO);
			request.setAttribute("edit", false);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("SeeCuenta.jsp");
			
			requestDispatcher.forward(request, response);
			
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
			
		} catch (BadFormatRequestException e) {
			
			handleException(e);
			
		}
	}
}

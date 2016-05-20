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
public class SeeCuentaEditServlet extends JSONHttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(SeeCuentaEditServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doGet de SeeCuentaEditServlet");
		
		try {
			
			String correo = request.getUserPrincipal().getName();
			
			EmpleadoWTO empleadoWTO = EmpleadoWebserviceConversor.toEmpleadoWTO(
					userService.getEmpleado(correo));
			
			EmpleadoInfoWTO empleadoInfoWTO = EmpleadoWebserviceConversor.toEmpleadoInfoWTO(
					userService.getEmpleadoData(empleadoWTO.getId()));
			
			request.setAttribute("empleado", empleadoWTO);
			request.setAttribute("empleadoInfo", empleadoInfoWTO);
			request.setAttribute("edit", true);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("SeeCuenta.jsp");
			
			requestDispatcher.forward(request, response);
			
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
			
		} catch (BadFormatRequestException e) {
			
			handleException(e);
			
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		logger.debug("doPost de SeeCuentaEditServlet");
			
		try {
			
			String correo = request.getParameter("correo");
			String nombre = request.getParameter("nombre");
			String nif = request.getParameter("nif");
			String apellidos = request.getParameter("apellidos");
			String direccion = request.getParameter("direccion");
			String localidad = request.getParameter("localidad");
			String cp = request.getParameter("cp");
			String provincia = request.getParameter("provincia");
			String phone1 = request.getParameter("phone1");
			String phone2 = request.getParameter("phone2");

			Boolean result = userService.editEmpleado(
					correo, nif, nombre, apellidos, direccion,
					localidad, provincia, cp, phone1, phone2);
			
			if (result) {
				response.sendRedirect("EditarCuentaExito.jsp");
			}
			
		} catch (InstanceNotFoundException e) {
			
			handleException(e);
			
		}

	}
}

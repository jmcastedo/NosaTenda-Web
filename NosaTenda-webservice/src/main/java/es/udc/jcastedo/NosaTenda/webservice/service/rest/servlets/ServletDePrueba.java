package es.udc.jcastedo.NosaTenda.webservice.service.rest.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.udc.jcastedo.NosaTenda.model.ReservaTO.ReservaStateTO;
import es.udc.jcastedo.NosaTenda.model.productoService.ProductoService;
import es.udc.jcastedo.NosaTenda.model.reservaService.ReservaService;
import es.udc.jcastedo.NosaTenda.model.userService.UserService;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.BadFormatRequestException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.DuplicateInstanceException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InsufficientStockException;

@SuppressWarnings("serial")
public class ServletDePrueba extends HttpServlet {

	static {
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {"classpath*:/nosaTenda-spring-config.xml", "classpath*:/nosaTenda-spring-config-test.xml"});
		
		productoService = (ProductoService) context.getBean("productoService");
		userService = (UserService) context.getBean("userService");
		reservaService = (ReservaService) context.getBean("reservaService");
	}


	private static ProductoService productoService;
	private static UserService userService;
	private static ReservaService reservaService;
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		Random rng = new Random();
		try {
			
			Long pruebaId = userService.newCliente("Cliente de prueba", "prueba@example.es"+rng.nextInt(), "pojo");
			
			Long pruebaId2 = new Long(0);
		
		
			pruebaId2 = reservaService.reserveProducto(new Long(1), ReservaStateTO.PENDIENTE, new Double(7), new Long(4), pruebaId);
			
			PrintWriter out = response.getWriter();
			out.println("Servlet de prueba");
			out.println("El id de prueba de cliente que devuelve es: " + pruebaId);
			out.println();
			out.println("El id de la prueba de reserva que devuelve es: " + pruebaId2);
			out.flush();
			out.close();
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		} catch (InsufficientStockException e) {
			e.printStackTrace();
		} catch (BadFormatRequestException e) {
			e.printStackTrace();
		} catch (DuplicateInstanceException e) {
			e.printStackTrace();
		}
		
		
	}
}

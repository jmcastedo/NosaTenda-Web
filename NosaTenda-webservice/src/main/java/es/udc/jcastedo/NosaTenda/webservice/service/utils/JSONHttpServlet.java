package es.udc.jcastedo.NosaTenda.webservice.service.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import es.udc.jcastedo.NosaTenda.model.compraService.CompraService;
import es.udc.jcastedo.NosaTenda.model.productoService.ProductoService;
import es.udc.jcastedo.NosaTenda.model.reservaService.ReservaService;
import es.udc.jcastedo.NosaTenda.model.tiendaService.TiendaService;
import es.udc.jcastedo.NosaTenda.model.userService.UserService;

@SuppressWarnings("serial")
public class JSONHttpServlet extends HttpServlet {

	static {
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {"classpath*:/nosaTenda-spring-config.xml", "classpath*:/nosaTenda-spring-config-test.xml"});
		
		productoService = (ProductoService) context.getBean("productoService");
		userService = (UserService) context.getBean("userService");
		tiendaService = (TiendaService) context.getBean("tiendaService");
		reservaService = (ReservaService) context.getBean("reservaService");
		compraService = (CompraService) context.getBean("compraService");
	}

	protected static ProductoService productoService;
	
	protected static UserService userService;
	
	protected static TiendaService tiendaService;
	
	protected static ReservaService reservaService;
	
	protected static CompraService compraService;
	
	// TODO javadoc
	protected void handleException(Exception e) throws ServletException {
		
		// development
		// e.printStackTrace();
		
		throw new ServletException(e);
			
	}
	
	/**
	 * @return true if the user has one of the specified roles.
	 */
	protected boolean hasRole(String[] roles) {
		
		boolean result = false;
		
		for (GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
			String userRole = authority.getAuthority();
			for (String role : roles) {
	            if (role.equals(userRole)) {
	                result = true;
	                break;
	            }
	        }

	        if (result) {
	            break;
	        }
		}
		
		return result;
	}
	
	/**
	 * @return true if the user has one of the specified roles.
	 */
	protected boolean hasRole(String role) {
		
		boolean result = false;
		
		for (GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
			String userRole = authority.getAuthority();
            if (role.equals(userRole)) {
                result = true;
            }

	        if (result) {
	            break;
	        }
		}
		
		return result;
	}
}

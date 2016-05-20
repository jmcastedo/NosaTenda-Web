package es.udc.jcastedo.NosaTenda.webservice.service.rest.servlets;

import java.io.IOException;
//import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;






import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.udc.jcastedo.NosaTenda.model.ProductoTO;
import es.udc.jcastedo.NosaTenda.model.productoService.ProductoService;
//import es.udc.jcastedo.NosaTenda.model.productoService.ProductoServiceFactory;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.MockService;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils;
import es.udc.jcastedo.NosaTenda.webservice.service.xml.FeedXMLConversor;

@SuppressWarnings("serial")
public class GetProductosServlet extends HttpServlet {
	
	//private static final Logger logger = LoggerFactory.getLogger(GetProductosServlet.class);
	
	static {
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {"classpath*:/nosaTenda-spring-config.xml", "classpath*:/nosaTenda-spring-config-test.xml"});
		
		productoService = (ProductoService) context.getBean("productoService");
	}
	
	private static ProductoService productoService;
	
	@Override
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)
		throws ServletException, IOException {
		
		List<ProductoTO> productoTOs = new ArrayList<ProductoTO>();
		
		//servicio real con beans
		productoTOs = productoService.getProductos();
		
		//servicio real con factoria
		//productoTOs = ProductoServiceFactory.getProductoService().getProductos();
		
		//mock
		//productoTOs = MockService.getProductos();
		
		/* Generate response */
		ServletUtils.writeServiceResponse(FeedXMLConversor.toXML(productoTOs), response);
		
		/*PrintWriter out = response.getWriter();
		out.println("GetProductos Servlet executed");
		if (productoTOs != null) {
			for(int i = 0; i < productoTOs.size(); i++) {
				ProductoTO temporal = productoTOs.get(i);
				
				out.println("Producto " + (i+1));
				out.println("**********");
				out.println("Id: " + temporal.getId());
				out.println("Nombre: " + temporal.getNombre());
				out.println("Descripción: " + temporal.getDescripcion());
				out.println("Precio: " + temporal.getPrecio());
				out.println("Imagen: " + temporal.getImagen());
				out.println("Tienda: " + temporal.getTienda());
				out.println("");
				out.println("");
			}
		}
		out.flush();
		out.close();*/
	}

}

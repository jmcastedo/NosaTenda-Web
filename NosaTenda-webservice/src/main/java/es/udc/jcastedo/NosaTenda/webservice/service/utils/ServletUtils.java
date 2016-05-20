package es.udc.jcastedo.NosaTenda.webservice.service.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletResponse;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ServletUtils {

	private ServletUtils() {}
	
	public static void writeServiceResponse(Element dataElement, HttpServletResponse response)
		throws IOException {
		
		OutputStream out = response.getOutputStream();
		
		response.setContentType("text/xml; charset=UTF-8");
		
		Document document = new Document(dataElement);
		XMLOutputter outputter;
		
		outputter = new XMLOutputter(Format.getPrettyFormat());
		
		outputter.output(document, out);
	}
	
	public static void writeServiceResponse(Object value, HttpServletResponse response)
		throws IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		OutputStream out = response.getOutputStream();
		response.setContentType("application/json; charset=UTF-8");
		mapper.writerWithDefaultPrettyPrinter().writeValue(out, value);

		out.flush();
		out.close();
	}
	
	public static void writeServiceResponseError(Object value, HttpServletResponse response, int sc_status)
			throws IOException {
			
			ObjectMapper mapper = new ObjectMapper();
			
			OutputStream out = response.getOutputStream();
			response.setStatus(sc_status);
			response.setContentType("application/json; charset=UTF-8");
			mapper.writerWithDefaultPrettyPrinter().writeValue(out, value);

			out.flush();
			out.close();
	}
	
	public static String fmt(Double d) {
		
		/*double dd = d.doubleValue();
		
		if (dd == (long) dd)
			return String.format("%d", (long) dd);
		else
			return String.format("%s", dd);*/
		
		DecimalFormat myFormatter = new DecimalFormat("###.##");
		
		return myFormatter.format(d);
	}
	
	public static String noNulls(String maybeNull) {
		
		if (maybeNull == null || maybeNull.equals("null")) {
			return "";
		} else {
			return maybeNull;
		}
	}
	
	public static String niceRoles(String role) {
		
		if (role.equals("ROLE_USUARIO")) {
			return "Empleado";
		}
		if (role.equals("ROLE_ADMIN_TIENDA")) {
			return "Administrador de tienda";
		}
		if (role.equals("ROLE_ADMIN")) {
			return "Administrador";
		}
		
		return "";
		
	}
}

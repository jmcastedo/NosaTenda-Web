package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import es.udc.jcastedo.NosaTenda.webservice.service.CompraWTO;
import java.text.SimpleDateFormat;

public final class Factura_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "HeadConfig.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("\t<title>Nosa Tenda - Factura</title>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");


	CompraWTO compra = (CompraWTO) request.getAttribute("compra");

	SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yyyy");
	
 
      out.write("\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("\t<h1 class=\"text-center\">FACTURA</h1>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("\n");
      out.write("\t<div class=\"row\">\n");
      out.write("\t\t<div class=\"col-sm-6\" style=\"border:solid 1px black;\">\n");
      out.write("\t\t\t<p>");
      out.print( compra.getProducto().getTienda().getNombre() );
      out.write("</p>\n");
      out.write("\t\t\t<p>NIF: ");
      out.print( compra.getProducto().getTienda().getNif() );
      out.write("</p>\n");
      out.write("\t\t\t<p>");
      out.print( compra.getProducto().getTienda().getDireccion() );
      out.write("</p>\n");
      out.write("\t\t\t<p>");
      out.print( compra.getProducto().getTienda().getLocalidad() );
      out.write("</p>\n");
      out.write("\t\t\t<p>");
      out.print( compra.getProducto().getTienda().getCp() );
      out.write(' ');
      out.print( compra.getProducto().getTienda().getProvincia() );
      out.write("</p>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-sm-6\" style=\"border:solid 1px black;\">\n");
      out.write("\t\t\t<p>Nombre: ");
      out.print( compra.getCliente().getApellidos() );
      out.write(',');
      out.write(' ');
      out.print( compra.getCliente().getNombre() );
      out.write("</p>\n");
      out.write("\t\t\t<p>Dirección: ");
      out.print( compra.getCliente().getDireccion() );
      out.write("</p>\n");
      out.write("\t\t\t<p>Población: ");
      out.print( compra.getCliente().getLocalidad() );
      out.write(" CP ");
      out.print( compra.getCliente().getCp() );
      out.write("</p>\n");
      out.write("\t\t\t<p>NIF/CIF: ");
      out.print( compra.getCliente().getNif() );
      out.write("</p>\n");
      out.write("\t\t\t<p>Teléfono: ");
      out.print( compra.getCliente().getPhone1() );
      out.write("</p>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\t<br>\n");
      out.write("\t<div class=\"row\">\n");
      out.write("\t\t<div class=\"col-sm-4\" style=\"border:solid 1px black;\">\n");
      out.write("\t\t\tNº Factura: ");
      out.print( compra.getNum_factura() );
      out.write("\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-sm-4\" style=\"border:solid 1px black;\">\n");
      out.write("\t\t\tFecha: ");
      out.print( date_format.format((compra.getFecha()).getTime()) );
      out.write("\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-sm-4\" style=\"border:solid 1px black;\">\n");
      out.write("\t\t\tForma de pago: ");
      out.print( compra.getFormaPago() );
      out.write("\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\t<br>\n");
      out.write("\t<div class=\"row\" style=\"background-color:lightgrey\">\n");
      out.write("\t\t<div class=\"col-sm-1\" style=\"border:solid 1px black;\">\n");
      out.write("\t\t\tCod\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-sm-7\" style=\"border:solid 1px black;\">\n");
      out.write("\t\t\tArtículo\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-sm-1\" style=\"border:solid 1px black;\">\n");
      out.write("\t\t\tPrecio\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-sm-1\" style=\"border:solid 1px black;\">\n");
      out.write("\t\t\tIVA\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-sm-1\" style=\"border:solid 1px black;\">\n");
      out.write("\t\t\tUnd\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-sm-1\" style=\"border:solid 1px black;\">\n");
      out.write("\t\t\tTotal\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\t<div class=\"row\">\n");
      out.write("\t\t<div class=\"col-sm-1\" style=\"border:solid 1px black;\">\n");
      out.write("\t\t\t");
      out.print( compra.getProducto().getId() );
      out.write("\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-sm-7\" style=\"border:solid 1px black;\">\n");
      out.write("\t\t\t");
      out.print( compra.getProducto().getNombre() );
      out.write("\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-sm-1\" style=\"border:solid 1px black;\">\n");
      out.write("\t\t\t");
      out.print( compra.getSubtotal() );
      out.write("\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-sm-1\" style=\"border:solid 1px black;\">\n");
      out.write("\t\t\t");
      out.print( compra.getTax() );
      out.write("%\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-sm-1\" style=\"border:solid 1px black;\">\n");
      out.write("\t\t\t");
      out.print( compra.getUnidades() );
      out.write("\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-sm-1\" style=\"border:solid 1px black;\">\n");
      out.write("\t\t\t");
      out.print( compra.getTotal() );
      out.write("\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\t\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

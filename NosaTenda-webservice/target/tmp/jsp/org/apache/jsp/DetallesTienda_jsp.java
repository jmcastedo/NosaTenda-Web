package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import es.udc.jcastedo.NosaTenda.webservice.service.TiendaWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils;

public final class DetallesTienda_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t<title>Nosa Tenda - Detalles producto</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
 
	TiendaWTO tienda = (TiendaWTO) request.getAttribute("tienda");

      out.write('\n');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "NavBar.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("menu", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("detailtienda", request.getCharacterEncoding()), out, false);
      out.write("\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("\t<h1 class=\"text-left text-primary\">");
      out.print( tienda.getNombre() );
      out.write("</h1>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("\t<div class=\"row\">\n");
      out.write("\t\t<!-- descripcion + imagen -->\n");
      out.write("\t\t<div class=\"col-sm-7 col-sm-offset-1\">\n");
      out.write("\t\t\t<!-- descripcion -->\n");
      out.write("\t\t\t<div class=\"row\"><h3>Dirección</h3></div>\n");
      out.write("\t\t\t<div class=\"row\"><p class=\"lead\">");
      out.print( tienda.getDireccion() + " " + tienda.getLocalidad() + " " + ServletUtils.noNulls(tienda.getProvincia()) + " " + ServletUtils.noNulls(tienda.getCp()) );
      out.write("</p></div>\n");
      out.write("\t\t\t<div class=\"row\"><p class=\"lead\">");
      out.print( "(" + tienda.getLat() + ", " + tienda.getLon() + ")" );
      out.write("</p></div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-sm-3 col-sm-offset-1\">\n");
      out.write("\t\t\t<!-- imagen -->\n");
      out.write("\t\t\t<img class=\"img-responsive center-block\" src=\"");
      out.print( tienda.getImagen() );
      out.write("\" alt=\"Imagen del producto\">\n");
      out.write("\t\t</div>\n");
      out.write("\t\t\n");
      out.write("\t</div>\n");
      out.write("\t\n");
      out.write("\t<div class=\"row\">\n");
      out.write("\t\t<!-- web + correo + nif -->\n");
      out.write("\t\t<div class=\"col-sm-4 col-sm-offset-1\">\n");
      out.write("\t\t\t<div class=\"row\"><h3>Web</h3></div>\n");
      out.write("\t\t\t<div class=\"row\"><p class=\"lead\">");
      out.print( ServletUtils.noNulls(tienda.getWeb()) );
      out.write("</p></div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-sm-3\">\n");
      out.write("\t\t\t<div class=\"row\"><h3>Correo electrónico</h3></div>\n");
      out.write("\t\t\t<div class=\"row\"><p class=\"lead\">");
      out.print( tienda.getCorreo() );
      out.write("</p></div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-sm-3 col-sm-offset-1\">\n");
      out.write("\t\t\t<div class=\"row\"><h3>NIF</h3></div>\n");
      out.write("\t\t\t<div class=\"row\"><p class=\"lead\">");
      out.print( tienda.getNif() );
      out.write("</p></div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\t\n");
      out.write("\t<div class=\"row\">\n");
      out.write("\t\t<!-- tel1 + tel2 + fax -->\n");
      out.write("\t\t<div class=\"col-sm-4 col-sm-offset-1\">\n");
      out.write("\t\t\t<div class=\"row\"><h3>Teléfono 1</h3></div>\n");
      out.write("\t\t\t<div class=\"row\"><p class=\"lead\">");
      out.print( tienda.getPhone1() );
      out.write("</p></div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-sm-3\">\n");
      out.write("\t\t\t<div class=\"row\"><h3>Teléfono 2</h3></div>\n");
      out.write("\t\t\t<div class=\"row\"><p class=\"lead\">");
      out.print( ServletUtils.noNulls(tienda.getPhone2()) );
      out.write("</p></div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-sm-3 col-sm-offset-1\">\n");
      out.write("\t\t\t<div class=\"row\"><h3>Fax</h3></div>\n");
      out.write("\t\t\t<div class=\"row\"><p class=\"lead\">");
      out.print( ServletUtils.noNulls(tienda.getFax()) );
      out.write("</p></div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\t\n");
      out.write("\t<div class=\"row\">\n");
      out.write("\t\t<!-- categorias -->\n");
      out.write("\t\t<div class=\"col-sm-11 col-sm-offset-1\">\n");
      out.write("\t\t\t<div class=\"row\"><h3>Categorías</h3></div>\n");
      out.write("\t\t\t<div class=\"row\"><p class=\"lead\">Categoría 1, categoría 2, categoría 3...");
      out.print(  );
      out.write("</p></div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
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

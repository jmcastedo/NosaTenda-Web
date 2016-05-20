package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import es.udc.jcastedo.NosaTenda.webservice.service.CompraWTO;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils;

public final class SeeCompras_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

 SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yyyy"); 
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
      out.write("\t<title>Nosa Tenda - Ver Compras</title>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");

	List<CompraWTO> compraWTOs = (List<CompraWTO>)request.getAttribute("compraWTOs");

      out.write('\n');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "NavBar.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("menu", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("compra", request.getCharacterEncoding()), out, false);
      out.write("\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("\t<h1 class=\"text-left text-primary\">Lista de Compras</h1>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("\n");
      out.write('\n');
      out.write('\n');

if (compraWTOs!=null && compraWTOs.size() > 0) {

      out.write("\n");
      out.write("\n");
      out.write("\t<table class=\"table table-hover table-condensed\">\n");
      out.write("\t<thead>\n");
      out.write("\t\t<tr>\n");
      out.write("\t\t\t<th></th>\n");
      out.write("\t\t\t<th>Id</th>\n");
      out.write("\t\t\t<th>Nombre del Producto</th>\n");
      out.write("\t\t\t<th>Tienda</th>\n");
      out.write("\t\t\t<th>Fecha</th>\n");
      out.write("\t\t\t<th>Cliente</th>\n");
      out.write("\t\t\t<th>Forma de Pago</th>\n");
      out.write("\t\t\t<th>Estado</th>\n");
      out.write("\t\t\t<th>Unidades</th>\n");
      out.write("\t\t\t<th>Subtotal</th>\n");
      out.write("\t\t\t<th>Total</th>\n");
      out.write("\t\t\t<th></th>\n");
      out.write("\t\t</tr>\n");
      out.write("\t</thead>\n");
      out.write("\t\n");
      out.write("\t");
 for (int i=0; i < compraWTOs.size(); i++) { 
      out.write("\n");
      out.write("\t\n");
      out.write("\t\t");

			String uriFactura = "GenerateFactura?id=" + compraWTOs.get(i).getId();
		
      out.write("\n");
      out.write("\t<tbody>\n");
      out.write("\t\t<tr>\n");
      out.write("\t\t\t<th>");
      out.print( i+1 );
      out.write("</th>\n");
      out.write("\t\t\t<td>");
      out.print( compraWTOs.get(i).getId() );
      out.write("</td>\n");
      out.write("\t\t\t<td>");
      out.print( compraWTOs.get(i).getProducto().getNombre() );
      out.write("</td>\n");
      out.write("\t\t\t<td>");
      out.print( compraWTOs.get(i).getProducto().getTienda().getNombre() );
      out.write("</td>\n");
      out.write("\t\t\t<td>");
      out.print( date_format.format((compraWTOs.get(i).getFecha()).getTime()) );
      out.write("</td>\n");
      out.write("\t\t\t<td>");
      out.print( compraWTOs.get(i).getCliente().getId() );
      out.write("</td>\n");
      out.write("\t\t\t<td>");
      out.print( compraWTOs.get(i).getFormaPago() );
      out.write("</td>\n");
      out.write("\t\t\t<td>");
      out.print( compraWTOs.get(i).getEstadoRecogida() );
      out.write("</td>\n");
      out.write("\t\t\t<td>");
      out.print( compraWTOs.get(i).getUnidades() );
      out.write("</td>\n");
      out.write("\t\t\t<td>");
      out.print( ServletUtils.fmt(compraWTOs.get(i).getSubtotal()) );
      out.write("</td>\n");
      out.write("\t\t\t<td>");
      out.print( ServletUtils.fmt(compraWTOs.get(i).getTotal()) );
      out.write("</td>\n");
      out.write("\t\t\t<td>\n");
      out.write("\t\t\t\t<a href=\"");
      out.print( uriFactura );
      out.write("\" class=\"btn btn-primary btn-xs\" role =\"button\" target=\"_blank\">Factura</a>\n");
      out.write("\t\t\t</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t</tbody>\n");
      out.write("\t");
 } 
      out.write("\n");
      out.write("\t\n");
      out.write("\t</table>\n");

} else {

      out.write("\n");
      out.write("\n");
      out.write("\t<h4>No se encontraron compras</h4>\n");
      out.write("\n");

}

      out.write("\n");
      out.write("\n");
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

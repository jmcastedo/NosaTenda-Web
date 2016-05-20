package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils;
import es.udc.jcastedo.NosaTenda.webservice.service.CompraWTO;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils;

public final class SearchCompraResultados_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<title>Nosa Tenda - Búsqueda</title>\n");
      out.write("</head>\n");
      out.write("<body text=\"#000000\" bgcolor=\"#ffffff\">\n");

	List<CompraWTO> compraWTOs = (List<CompraWTO>)request.getAttribute("compras");

	String tienda = (String)request.getAttribute("tienda");
	
	SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yyyy");
	
	Long totalUnidades = new Long(0);
	Double totalTotal = new Double(0);

      out.write("\n");
      out.write("\n");
      out.write("<div align=\"center\">\n");
      out.write("\t<p>\n");
      out.write("\t\t<font color=\"#000099\" face=\"Arial, Helvetica, san-serif\">\n");
      out.write("\t\t\t<b>Compras realizadas en la tienda \"");
      out.print( tienda );
      out.write("\"</b>\n");
      out.write("\t\t</font>\n");
      out.write("\t</p>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div align=\"center\">\n");
      out.write("\n");

if (compraWTOs!=null && compraWTOs.size() > 0) {

      out.write("\n");
      out.write("\n");
      out.write("\t<table border=\"1\" align=\"center\" width=\"55%\">\n");
      out.write("\t\n");
      out.write("\t<tr>\n");
      out.write("\t\t<th></th>\n");
      out.write("\t\t<td align=\"center\">Id</td>\n");
      out.write("\t\t<td align=\"center\">Producto</td>\n");
      out.write("\t\t<td align=\"center\">Cliente</td>\n");
      out.write("\t\t<td align=\"center\">Fecha</td>\n");
      out.write("\t\t<td align=\"center\">Estado</td>\n");
      out.write("\t\t<td align=\"center\">Forma de pago</td>\n");
      out.write("\t\t<td align=\"center\">Unidades</td>\n");
      out.write("\t\t<td align=\"center\">Subtotal</td>\n");
      out.write("\t\t<td align=\"center\">Total</td>\n");
      out.write("\t\t<td></td>\n");
      out.write("\t\t\n");
      out.write("\t\n");
      out.write("\t</tr>\n");
      out.write("\t\n");
      out.write("\t");
 for (int i=0; i < compraWTOs.size(); i++) { 
      out.write("\n");
      out.write("\t\n");
      out.write("\t\t");

			String uriFactura = "GenerateFactura?id=" + compraWTOs.get(i).getId();
		
      out.write("\n");
      out.write("\t\n");
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
      out.print( compraWTOs.get(i).getCliente().getClienteId() );
      out.write("</td>\n");
      out.write("\t\t\t<td>");
      out.print( date_format.format((compraWTOs.get(i).getFecha()).getTime()) );
      out.write("</td>\n");
      out.write("\t\t\t<td>");
      out.print( compraWTOs.get(i).getEstadoRecogida() );
      out.write("</td>\n");
      out.write("\t\t\t<td>");
      out.print( compraWTOs.get(i).getFormaPago() );
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
      out.write("\" target=\"_blank\">Factura</a>\n");
      out.write("\t\t\t</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t\n");
      out.write("\t\t");

			totalUnidades = totalUnidades + compraWTOs.get(i).getUnidades();
			totalTotal = totalTotal + compraWTOs.get(i).getTotal();
		
      out.write("\n");
      out.write("\t\t\n");
      out.write("\t");
 } 
      out.write("\n");
      out.write("\t\n");
      out.write("\t<tr>\n");
      out.write("\t\t<th></th>\n");
      out.write("\t\t<td></td>\n");
      out.write("\t\t<td></td>\n");
      out.write("\t\t<td></td>\n");
      out.write("\t\t<td></td>\n");
      out.write("\t\t<td></td>\n");
      out.write("\t\t<td></td>\n");
      out.write("\t\t<td><b>");
      out.print( totalUnidades );
      out.write("</b></td>\n");
      out.write("\t\t<td></td>\n");
      out.write("\t\t<td><b>");
      out.print( ServletUtils.fmt(totalTotal) );
      out.write("</b></td>\n");
      out.write("\t\t<td></td>\n");
      out.write("\t\t\n");
      out.write("\t\n");
      out.write("\t</tr>\n");
      out.write("\t\n");
      out.write("\t\n");
      out.write("\t</table>\n");

} else {

      out.write("\n");
      out.write("\n");
      out.write("\t<font color=\"#000099\" face=\"Arial, Helvetica, sans-serif\">\n");
      out.write("\t\t<b>No se encontraron compras</b>\n");
      out.write("\t</font>\n");
      out.write("\n");

}

      out.write("\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div align=\"center\">\n");
      out.write("\t<br><br>\n");
      out.write("\t<form method=\"GET\" action=\"SearchCompra\">\n");
      out.write("\t\t<input type=\"submit\" value=\"Realizar otra búsqueda\">\n");
      out.write("\t</form>\n");
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

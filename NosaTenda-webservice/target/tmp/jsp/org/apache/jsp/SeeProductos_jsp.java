package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import es.udc.jcastedo.NosaTenda.webservice.service.ProductoWTO;
import java.util.List;
import java.util.ArrayList;

public final class SeeProductos_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "HeadConfig.jsp", out, false);
      out.write("\n");
      out.write("    \n");
      out.write("\t<title>Nosa Tenda - Ver Productos</title>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");

	List<ProductoWTO> productoWTOs = (List<ProductoWTO>)request.getAttribute("productoWTOs");

      out.write('\n');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "NavBar.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("menu", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("producto", request.getCharacterEncoding()), out, false);
      out.write("\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("\t<h1 class=\"text-left text-primary\">Lista de Productos</h1>\n");
      out.write("</div>\n");
      out.write("\n");

if (productoWTOs!=null && productoWTOs.size() > 0) {

      out.write("\n");
      out.write("\n");
      out.write("\t<div class=\"container\">\n");
      out.write("\n");
      out.write("\t\t<table class=\"table table-hover table-condensed\">\n");
      out.write("\t\t<thead>\n");
      out.write("\t\t\t<tr>\n");
      out.write("\t\t\t\t<th></th>\n");
      out.write("\t\t\t\t<th>Id</th>\n");
      out.write("\t\t\t\t<th>Nombre</th>\n");
      out.write("\t\t\t\t<th>Descripción</th>\n");
      out.write("\t\t\t\t<th>URL imagen</th>\n");
      out.write("\t\t\t\t<th>Precio</th>\n");
      out.write("\t\t\t\t<th>Stock</th>\n");
      out.write("\t\t\t\t<th>Tienda</th>\n");
      out.write("\t\t\t\t<th></th>\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t</tr>\n");
      out.write("\t\t</thead>\n");
      out.write("\t\t\n");
      out.write("\t\t");
 for (int i=0; i < productoWTOs.size(); i++) { 
      out.write("\n");
      out.write("\t\t\n");
      out.write("\t\t\t");

				String uriBorrar = "BorrarProducto.jsp?id=" + productoWTOs.get(i).getId();
				String uriEditar = "EditarProducto?id=" + productoWTOs.get(i).getId();
			
      out.write("\n");
      out.write("\t\t<tbody>\n");
      out.write("\t\t");
 if (productoWTOs.get(i).getStock() <= 5) { 
      out.write("\n");
      out.write("\t\t\t<tr class=\"danger\">\n");
      out.write("\t\t\t");
 } else { 
      out.write("\n");
      out.write("\t\t\t<tr>\n");
      out.write("\t\t\t");
 } 
      out.write("\n");
      out.write("\t\t\t\t<th>");
      out.print( i+1 );
      out.write("</th>\n");
      out.write("\t\t\t\t<td>");
      out.print( productoWTOs.get(i).getId() );
      out.write("</td>\n");
      out.write("\t\t\t\t<td>");
      out.print( productoWTOs.get(i).getNombre() );
      out.write("</td>\n");
      out.write("\t\t\t\t<td>");
      out.print( productoWTOs.get(i).getDescripcion() );
      out.write("</td>\n");
      out.write("\t\t\t\t<td>");
      out.print( productoWTOs.get(i).getImagen() );
      out.write("</td>\n");
      out.write("\t\t\t\t<td>");
      out.print( productoWTOs.get(i).getPrecio() );
      out.write("</td>\n");
      out.write("\t\t\t\t<td>");
      out.print( productoWTOs.get(i).getStock() );
      out.write("</td>\n");
      out.write("\t\t\t\t<td>");
      out.print( productoWTOs.get(i).getTienda().getNombre() );
      out.write("</td>\n");
      out.write("\t\t\t\t<td>\n");
      out.write("\t\t\t\t\t<div class=\"btn-group-vertical\">\n");
      out.write("\t\t\t\t\t\t<a href=\"");
      out.print( uriEditar );
      out.write("\" class=\"btn btn-primary btn-xs\" role =\"button\">Editar</a>\n");
      out.write("\t\t\t\t\t\t<a href=\"");
      out.print( uriBorrar );
      out.write("\" class=\"btn btn-primary btn-xs\" role =\"button\">Borrar</a>\n");
      out.write("\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-primary btn-xs\" role =\"button\" data-toggle=\"modal\" data-target=\"#myModal\">Borrar</button>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t\t<div class=\"modal fade\" id=\"myModal\" role=\"dialog\">\n");
      out.write("\t\t\t\t\t    <div class=\"modal-dialog\">\n");
      out.write("\t\t\t\t\t    \n");
      out.write("\t\t\t\t\t      <!-- Modal content-->\n");
      out.write("\t\t\t\t\t      <div class=\"modal-content\">\n");
      out.write("\t\t\t\t\t        <div class=\"modal-header\">\n");
      out.write("\t\t\t\t\t          <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\n");
      out.write("\t\t\t\t\t          <h4 class=\"modal-title\"><span class=\"glyphicon glyphicon-exclamation-sign\"> ");
      out.print( header );
      out.write(" <span class=\"glyphicon glyphicon-exclamation-sign\"></span></h4>\n");
      out.write("\t\t\t\t\t        </div>\n");
      out.write("\t\t\t\t\t        <div class=\"modal-body\">\n");
      out.write("\t\t\t\t\t          <p>");
      out.print( body );
      out.write("</p>\n");
      out.write("\t\t\t\t\t        </div>\n");
      out.write("\t\t\t\t\t        <div class=\"modal-footer\">\n");
      out.write("\t\t\t\t\t          <button type=\"button\" class=\"btn btn-default\"\">Confirmar</button>\n");
      out.write("\t\t\t\t\t          <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Cancelar</button>\n");
      out.write("\t\t\t\t\t        </div>\n");
      out.write("\t\t\t\t\t      </div>\n");
      out.write("\t\t\t\t\t      \n");
      out.write("\t\t\t\t\t    </div>\n");
      out.write("\t\t\t\t\t </div>\n");
      out.write("\t\t\t\t</td>\n");
      out.write("\t\t\t</tr>\n");
      out.write("\t\t</tbody>\n");
      out.write("\t\t");
 } 
      out.write("\n");
      out.write("\t\t\n");
      out.write("\t\t</table>\n");
      out.write("\t\n");
      out.write("\t</div>\n");

} else {

      out.write("\n");
      out.write("\n");
      out.write("\t<div class=\"container text-center\">\n");
      out.write("\t\t<br>\n");
      out.write("\t\t<h4>No se encontraron productos</h4>\n");
      out.write("\t</div>\n");
      out.write("\n");

}

      out.write("\n");
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

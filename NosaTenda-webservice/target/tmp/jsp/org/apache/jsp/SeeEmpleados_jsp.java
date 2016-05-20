package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import es.udc.jcastedo.NosaTenda.webservice.service.EmpleadoWTO;
import java.util.List;
import java.util.ArrayList;

public final class SeeEmpleados_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("\t\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "HeadConfig.jsp", out, false);
      out.write("\n");
      out.write("    \n");
      out.write("\t<title>Nosa Tenda - Ver Empleados</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");

	List<EmpleadoWTO> empleadoWTOs = (List<EmpleadoWTO>)request.getAttribute("empleadoWTOs");

      out.write('\n');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "NavBar.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("menu", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("empleado", request.getCharacterEncoding()), out, false);
      out.write("\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("\t<h1 class=\"text-left text-primary\">Lista de Empleados</h1>\n");
      out.write("</div>\n");
      out.write("\n");

if (empleadoWTOs!=null && empleadoWTOs.size() > 0) {

      out.write("\n");
      out.write("\n");
      out.write("\t<div class=\"container\">\n");
      out.write("\t\n");
      out.write("\t\t<form method=\"POST\" action=\"EditarEmpleadosPermisos\" class=\"form-horizontal\" role=\"form\">\n");
      out.write("\n");
      out.write("\t\t\t<table class=\"table table-hover table-condensed\">\n");
      out.write("\t\t\t<thead>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<th></th>\n");
      out.write("\t\t\t\t\t<th>Id</th>\n");
      out.write("\t\t\t\t\t<th>Correo</th>\n");
      out.write("\t\t\t\t\t<th>NIF</th>\n");
      out.write("\t\t\t\t\t<th>Permisos</th>\n");
      out.write("\t\t\t\t\t<th></th>\n");
      out.write("\t\t\t\t\t<th>Activado</th>\n");
      out.write("\t\t\t\t\t<th></th>\n");
      out.write("\t\t\t\t\t<th></th>\n");
      out.write("\t\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t</thead>\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t");
 for (int i=0; i < empleadoWTOs.size(); i++) { 
      out.write("\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t\t");

					//String uriBorrar = "BorrarProducto.jsp?id=" + empleadoWTOs.get(i).getId();
					String uriEditar = "EditarProducto?id=" + empleadoWTOs.get(i).getId();
				
      out.write("\n");
      out.write("\t\t\t<tbody>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<th>");
      out.print( i+1 );
      out.write("</th>\n");
      out.write("\t\t\t\t\t<td>");
      out.print( empleadoWTOs.get(i).getId() );
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print( empleadoWTOs.get(i).getCorreo() );
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print( empleadoWTOs.get(i).getNif() );
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print( empleadoWTOs.get(i).getRole() );
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td><a href=\"#\" class=\"btn btn-primary\" role=\"button\">Editar</a></td>\n");
      out.write("\t\t\t\t\t<td>");
      out.print( empleadoWTOs.get(i).getActivado() );
      out.write("</td>\n");
      out.write("\t\t\t\t\t<td>\n");
      out.write("\t\t\t\t\t\t");
 if (empleadoWTOs.get(i).getActivado().equals("false")) 
      out.write("\n");
      out.write("\t\t\t\t\t\t\t<a href=\"#\" class=\"btn btn-primary\" role=\"button\">Activar</a>\n");
      out.write("\t\t\t\t\t\t");
 } 
      out.write("\n");
      out.write("\t\t\t\t\t</td>\n");
      out.write("\t\t\t\t\t<td><a href=\"#\" class=\"btn btn-primary\" role=\"button\">Borrar</a></td>\n");
      out.write("\t\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t</tbody>\n");
      out.write("\t\t\t");
 } 
      out.write("\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t</table>\n");
      out.write("\t\t\t\n");
      out.write("\t\t</form>\n");
      out.write("\t\n");
      out.write("\t</div>\n");

} else {

      out.write("\n");
      out.write("\n");
      out.write("\t<div class=\"container text-center\">\n");
      out.write("\t\t<br>\n");
      out.write("\t\t<h4>No se encontraron empleados</h4>\n");
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

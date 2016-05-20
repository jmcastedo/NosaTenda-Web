package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import es.udc.jcastedo.NosaTenda.webservice.service.ProductoWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.ProductoVentasBlockWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils;

public final class PonerEnVenta_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_sec_csrfInput_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_sec_csrfInput_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_sec_csrfInput_nobody.release();
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
      out.write("\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "HeadConfig.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("\t<title>Nosa Tenda - Poner en venta</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
 
	ProductoWTO producto = (ProductoWTO) request.getAttribute("producto");
	

      out.write('\n');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "NavBar.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("menu", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("ventaproducto", request.getCharacterEncoding()), out, false);
      out.write("\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("\t<h1 class=\"text-left text-primary\">Poner en venta</h1>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("\t<div class=\"row\">\n");
      out.write("\t\t<!-- descripcion + imagen -->\n");
      out.write("\t\t<div class=\"col-sm-7 col-sm-offset-1\">\n");
      out.write("\t\t\t<!-- descripcion -->\n");
      out.write("\t\t\t<div class=\"row\"><h3>");
      out.print( producto.getNombre() );
      out.write("</h3></div>\n");
      out.write("\t\t\t<div class=\"row\"><p class=\"lead\">");
      out.print( producto.getTienda().getNombre() );
      out.write("</p></div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-sm-3 col-sm-offset-1\">\n");
      out.write("\t\t\t<!-- imagen -->\n");
      out.write("\t\t\t<img class=\"img-responsive center-block\" src=\"");
      out.print( producto.getImagen() );
      out.write("\" alt=\"Imagen del producto\">\n");
      out.write("\t\t</div>\n");
      out.write("\t\t\n");
      out.write("\t</div>\n");
      out.write("\t\n");
      out.write("\t<form method=\"POST\" action=\"PonerEnVenta\" class=\"form-horizontal\" role=\"form\">\n");
      out.write("\t\n");
      out.write("\t\t<input type=\"hidden\" name=\"id\" value=\"");
      out.print( producto.getId() );
      out.write("\">\n");
      out.write("\t\t\n");
      out.write("\t\t");
      if (_jspx_meth_sec_csrfInput_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t");
      out.write("\n");
      out.write("\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t<label class=\"control-label col-sm-2\" for=\"precio\"><h3>Fijar precio</h3></label>\n");
      out.write("\t\t\t<div class=\"col-sm-8\">\n");
      out.write("\t\t\t\t<input type=\"number\" class=\"form-control\" id=\"precio\" name=\"precio\" placeholder=\"Introduzca el precio\" value=\"");
      out.print( precio );
      out.write("\" maxlength=\"8\" min=\"0\" step=\"0.01\">\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<label class=\"control-label col-sm-2\" for=\"stock\"><h3>Fijar stock</h3></label>\n");
      out.write("\t\t\t<div class=\"col-sm-8\">\n");
      out.write("\t\t\t\t<input type=\"number\" class=\"form-control\" id=\"stock\" name=\"stock\" placeholder=\"Introduzca el stock\" value=\"");
      out.print(stock );
      out.write("\" maxlength=\"8\" min=\"0\">\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t\n");
      out.write("\t\t");
      out.write("\n");
      out.write("\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t<label class=\"control-label col-sm-2\" for=\"fecha_venta\"><h3>Fijar fecha de venta</h3></label>\n");
      out.write("\t\t\t<div class=\"col-sm-8\">\n");
      out.write("\t\t\t\t<input type=\"datetime-local\" class=\"form-control\" id=\"fecha_venta\" name=\"fecha_venta\" placeholder=\"Introduzca la fecha de puesta en venta\" value=\"");
      out.print( fecha_venta );
      out.write("\">\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<label class=\"control-label col-sm-2\" for=\"fecha_retirada\"><h3>Fijar fecha de retirada</h3></label>\n");
      out.write("\t\t\t<div class=\"col-sm-8\">\n");
      out.write("\t\t\t\t<input type=\"datetime-local\" class=\"form-control\" id=\"fecha_retirada\" name=\"fecha_retirada\" placeholder=\"Introduzca la fecha de retirada\" value=\"");
      out.print( fecha_retirada );
      out.write("\">\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t\n");
      out.write("\t\t");
      out.write("\n");
      out.write("\t\t<div class=\"form-group\">        \n");
      out.write("\t      <div class=\"col-sm-offset-2 col-sm-10\">\n");
      out.write("\t        <input type=\"submit\" class=\"btn btn-primary\" value=\"Poner en venta\">\n");
      out.write("\t      </div>\n");
      out.write("\t    </div>\n");
      out.write("\t\t\n");
      out.write("\t\t<div class=\"row\">\n");
      out.write("\t\t\t<!-- fijar precio - fijar fecha puesta venta -->\n");
      out.write("\t\t\t<div class=\"col-sm-2 col-sm-offset-1\">\n");
      out.write("\t\t\t\t<div class=\"row\"><h3>Fijar precio</h3></div>\n");
      out.write("\t\t\t\t<div class=\"row\"><h3>Fecha de puesta en venta</h3></div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div class=\"col-sm-2 col-sm-offset-1\">\n");
      out.write("\t\t\t\t<div class=\"row\"><p class=\"lead\">Formulario fijar precio</p></div>\n");
      out.write("\t\t\t\t<div class=\"row\"><p class=\"lead\">Formulario fecha puesta en venta</p></div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<!-- fijar stock - fijar fecha retirada -->\n");
      out.write("\t\t\t<div class=\"col-sm-2 col-sm-offset-1\">\n");
      out.write("\t\t\t\t<div class=\"row\"><h3>Fijar stock</h3></div>\n");
      out.write("\t\t\t\t<div class=\"row\"><p class=\"lead\">Fecha de retirada</p></div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div class=\"col-sm-2 col-sm-offset-1\">\n");
      out.write("\t\t\t\t<div class=\"row\"><h3>Formulario fijar stock</h3></div>\n");
      out.write("\t\t\t\t<div class=\"row\"><p class=\"lead\">Formulario fecha retirada</p></div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t\n");
      out.write("\t\t");
      out.write("\n");
      out.write("\t\t<div class=\"form-group\">        \n");
      out.write("\t      <div class=\"col-sm-offset-2 col-sm-10\">\n");
      out.write("\t        <input type=\"submit\" class=\"btn btn-primary\" value=\"Poner en venta\">\n");
      out.write("\t        <a href=\"DetallesProducto?id=");
      out.print( producto.getId() );
      out.write("\" class=\"btn btn-primary\" role=\"button\">Cancelar</a>\n");
      out.write("\t      </div>\n");
      out.write("\t    </div>\n");
      out.write("\t</form>\n");
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

  private boolean _jspx_meth_sec_csrfInput_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sec:csrfInput
    org.springframework.security.taglibs.csrf.CsrfInputTag _jspx_th_sec_csrfInput_0 = (org.springframework.security.taglibs.csrf.CsrfInputTag) _jspx_tagPool_sec_csrfInput_nobody.get(org.springframework.security.taglibs.csrf.CsrfInputTag.class);
    _jspx_th_sec_csrfInput_0.setPageContext(_jspx_page_context);
    _jspx_th_sec_csrfInput_0.setParent(null);
    int _jspx_eval_sec_csrfInput_0 = _jspx_th_sec_csrfInput_0.doStartTag();
    if (_jspx_th_sec_csrfInput_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_sec_csrfInput_nobody.reuse(_jspx_th_sec_csrfInput_0);
      return true;
    }
    _jspx_tagPool_sec_csrfInput_nobody.reuse(_jspx_th_sec_csrfInput_0);
    return false;
  }
}

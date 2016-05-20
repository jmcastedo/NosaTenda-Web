package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import es.udc.jcastedo.NosaTenda.webservice.service.ReservaInfoWTO;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public final class SeeReservas_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

 SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yyyy"); 
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
      out.write("\t<title>Nosa Tenda - Ver Reservas</title>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");

	List<ReservaInfoWTO> reservaWTOs = (List<ReservaInfoWTO>)request.getAttribute("reservaWTOs");

      out.write('\n');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "NavBar.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("menu", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("reserva", request.getCharacterEncoding()), out, false);
      out.write("\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("\t<h1 class=\"text-left text-primary\">Lista de Reservas</h1>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("\n");
      out.write('\n');
      out.write('\n');

if (reservaWTOs!=null && reservaWTOs.size() > 0) {

      out.write("\n");
      out.write("\n");
      out.write("\t<table class=\"table table-hover table-condensed\">\n");
      out.write("\t<thead>\n");
      out.write("\t\t<tr>\n");
      out.write("\t\t\t\t<th>Id</th>\n");
      out.write("\t\t\t\t<th>Producto</th>\n");
      out.write("\t\t\t\t<th>Tienda</th>\n");
      out.write("\t\t\t\t<th>Fecha de la reserva</th>\n");
      out.write("\t\t\t\t<th>Unidades</th>\n");
      out.write("\t\t\t\t<th>Subtotal</th>\n");
      out.write("\t\t\t\t<th>Total</th>\n");
      out.write("\t\t\t\t<th>Estado</th>\n");
      out.write("\t\t\t\t<th></th>\n");
      out.write("\t\t</tr>\n");
      out.write("\t</thead>\n");
      out.write("\t\n");
      out.write("\t");
 for (int i=0; i < reservaWTOs.size(); i++) { 
      out.write("\n");
      out.write("\t\n");
      out.write("\t\t<tbody>\n");
      out.write("\t\t\t<tr>\n");
      out.write("\t\t\t\t<th>");
      out.print( reservaWTOs.get(i).getId() );
      out.write("</th>\n");
      out.write("\t\t\t\t<td>");
      out.print( reservaWTOs.get(i).getProducto().getNombre() );
      out.write("</td>\n");
      out.write("\t\t\t\t<td>");
      out.print( reservaWTOs.get(i).getProducto().getTienda().getNombre() );
      out.write("</td>\n");
      out.write("\t\t\t\t<td>");
      out.print( date_format.format((reservaWTOs.get(i).getFecha()).getTime()) );
      out.write("</td>\n");
      out.write("\t\t\t\t<td>");
      out.print( reservaWTOs.get(i).getUnidades() );
      out.write("</td>\n");
      out.write("\t\t\t\t<td>");
      out.print( reservaWTOs.get(i).getSubtotal() );
      out.write("</td>\n");
      out.write("\t\t\t\t<td>");
      out.print( reservaWTOs.get(i).getTotal() );
      out.write("</td>\n");
      out.write("\t\t\t\t<td>");
      out.print( reservaWTOs.get(i).getEstado() );
      out.write("</td>\n");
      out.write("\t\t\t\t<td>\n");
      out.write("\t\t\t\t\t<div class=\"btn-group-vertical\">\n");
      out.write("\t\t\t\t\t\t");
 if (reservaWTOs.get(i).getEstado() == ReservaInfoWTO.ReservaStateWTO.PENDIENTE) { 
      out.write("\n");
      out.write("\t\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-primary btn-xs\" role =\"button\" data-toggle=\"modal\" data-target=\"#myModalE");
      out.print( i+1 );
      out.write("\">Entregar</button>\n");
      out.write("\t\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-primary btn-xs\" role =\"button\" data-toggle=\"modal\" data-target=\"#myModalC");
      out.print( i+1 );
      out.write("\">Cancelar</button>\n");
      out.write("\t\t\t\t\t\t");
 } else { 
      out.write("\n");
      out.write("\t\t\t\t\t\t\t<a href=\"#\" class=\"btn btn-primary btn-xs disabled\" role =\"button\">Entregar</a>\n");
      out.write("\t\t\t\t\t\t\t<a href=\"#\" class=\"btn btn-primary btn-xs disabled\" role =\"button\">Cancelar</a>\n");
      out.write("\t\t\t\t\t\t");
 } 
      out.write("\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t\t<div class=\"modal fade\" id=\"myModalE");
      out.print( i+1 );
      out.write("\" role=\"dialog\">\n");
      out.write("\t\t\t\t\t    <div class=\"modal-dialog\">\n");
      out.write("\t\t\t\t\t    \n");
      out.write("\t\t\t\t\t      <!-- Modal content-->\n");
      out.write("\t\t\t\t\t      <div class=\"modal-content\">\n");
      out.write("\t\t\t\t\t        <div class=\"modal-header\">\n");
      out.write("\t\t\t\t\t          <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\n");
      out.write("\t\t\t\t\t          <h4 class=\"modal-title\">Está a punto de marcar una reserva como ENTREGADA <span class=\"glyphicon glyphicon-exclamation-sign text-danger\"></h4>\n");
      out.write("\t\t\t\t\t        </div>\n");
      out.write("\t\t\t\t\t        <div class=\"modal-body\">\n");
      out.write("\t\t\t\t\t          <p>¿Está seguro?</p>\n");
      out.write("\t\t\t\t\t        </div>\n");
      out.write("\t\t\t\t\t        <div class=\"modal-footer\">\n");
      out.write("\t\t\t\t\t          <form method=\"POST\" action=\"EntregarReserva?reservaId=");
      out.print( reservaWTOs.get(i).getId() );
      out.write("\">\n");
      out.write("\t\t\t\t\t            ");
      if (_jspx_meth_sec_csrfInput_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"submit\" class=\"btn btn-primary\" value=\"Confirmar\">\n");
      out.write("\t\t\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\">Cancelar</button>\n");
      out.write("\t\t\t\t\t\t\t  </form>\t\n");
      out.write("\t\t\t\t\t          \n");
      out.write("\t\t\t\t\t        </div>\n");
      out.write("\t\t\t\t\t      </div>\n");
      out.write("\t\t\t\t\t      \n");
      out.write("\t\t\t\t\t    </div>\n");
      out.write("\t\t\t\t\t </div>\n");
      out.write("\t\t\t\t\t \n");
      out.write("\t\t\t\t\t <div class=\"modal fade\" id=\"myModalC");
      out.print( i+1 );
      out.write("\" role=\"dialog\">\n");
      out.write("\t\t\t\t\t    <div class=\"modal-dialog\">\n");
      out.write("\t\t\t\t\t    \n");
      out.write("\t\t\t\t\t      <!-- Modal content-->\n");
      out.write("\t\t\t\t\t      <div class=\"modal-content\">\n");
      out.write("\t\t\t\t\t        <div class=\"modal-header\">\n");
      out.write("\t\t\t\t\t          <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\n");
      out.write("\t\t\t\t\t          <h4 class=\"modal-title\">Está a punto de marcar una reserva como CANCELADA <span class=\"glyphicon glyphicon-exclamation-sign text-danger\"></h4>\n");
      out.write("\t\t\t\t\t        </div>\n");
      out.write("\t\t\t\t\t        <div class=\"modal-body\">\n");
      out.write("\t\t\t\t\t          <p>¿Está seguro?</p>\n");
      out.write("\t\t\t\t\t        </div>\n");
      out.write("\t\t\t\t\t        <div class=\"modal-footer\">\n");
      out.write("\t\t\t\t\t          <form method=\"POST\" action=\"CancelarReserva?reservaId=");
      out.print( reservaWTOs.get(i).getId() );
      out.write("\">\n");
      out.write("\t\t\t\t\t            ");
      if (_jspx_meth_sec_csrfInput_1(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"submit\" class=\"btn btn-primary\" value=\"Confirmar\">\n");
      out.write("\t\t\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\">Cancelar</button>\n");
      out.write("\t\t\t\t\t\t\t  </form>\t\n");
      out.write("\t\t\t\t\t          \n");
      out.write("\t\t\t\t\t        </div>\n");
      out.write("\t\t\t\t\t      </div>\n");
      out.write("\t\t\t\t\t      \n");
      out.write("\t\t\t\t\t    </div>\n");
      out.write("\t\t\t\t\t </div>\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t</td>\n");
      out.write("\t\t\t</tr>\n");
      out.write("\t\t</tbody>\n");
      out.write("\t");
 } 
      out.write("\n");
      out.write("\t\n");
      out.write("\t</table>\n");

} else {

      out.write("\n");
      out.write("\n");
      out.write("\t<h4>No se encontraron reservas</h4>\n");
      out.write("\n");

}

      out.write("\n");
      out.write("\n");
      out.write("</div>\n");
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

  private boolean _jspx_meth_sec_csrfInput_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sec:csrfInput
    org.springframework.security.taglibs.csrf.CsrfInputTag _jspx_th_sec_csrfInput_1 = (org.springframework.security.taglibs.csrf.CsrfInputTag) _jspx_tagPool_sec_csrfInput_nobody.get(org.springframework.security.taglibs.csrf.CsrfInputTag.class);
    _jspx_th_sec_csrfInput_1.setPageContext(_jspx_page_context);
    _jspx_th_sec_csrfInput_1.setParent(null);
    int _jspx_eval_sec_csrfInput_1 = _jspx_th_sec_csrfInput_1.doStartTag();
    if (_jspx_th_sec_csrfInput_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_sec_csrfInput_nobody.reuse(_jspx_th_sec_csrfInput_1);
      return true;
    }
    _jspx_tagPool_sec_csrfInput_nobody.reuse(_jspx_th_sec_csrfInput_1);
    return false;
  }
}

package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import es.udc.jcastedo.NosaTenda.webservice.service.ProductoWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.ProductoVentasBlockWTO;
import es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils;

public final class DetallesProducto_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t<title>Nosa Tenda - Editar producto</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
 
	ProductoWTO producto = (ProductoWTO) request.getAttribute("producto");

	List<ProductoVentasBlockWTO> productoVentasBlockWTOs = (List<ProductoVentasBlockWTO>) request.getAttribute("productoVentasBlockWTOs");

      out.write('\n');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "NavBar.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("menu", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("detailproducto", request.getCharacterEncoding()), out, false);
      out.write("\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("\t<h1 class=\"text-left text-primary\">");
      out.print( producto.getNombre() );
      out.write("</h1>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("\t<div class=\"row\">\n");
      out.write("\t\t<!-- descripcion + imagen -->\n");
      out.write("\t\t<div class=\"col-sm-7 col-sm-offset-1\">\n");
      out.write("\t\t\t<!-- descripcion -->\n");
      out.write("\t\t\t<div class=\"row\"><h3>Descripción</h3></div>\n");
      out.write("\t\t\t<div class=\"row\"><p class=\"lead\">");
      out.print( producto.getDescripcion() );
      out.write("</p></div>\n");
      out.write("\t\t\t<div class=\"row\"><a href=\"");
      out.print( "EditarProducto?id=" + producto.getId() );
      out.write("\" class=\"btn btn-primary\" role =\"button\">Editar</a></div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-sm-3 col-sm-offset-1\">\n");
      out.write("\t\t\t<!-- imagen -->\n");
      out.write("\t\t\t<img class=\"img-responsive center-block\" src=\"");
      out.print( producto.getImagen() );
      out.write("\" alt=\"Imagen del producto\">\n");
      out.write("\t\t</div>\n");
      out.write("\t\t\n");
      out.write("\t</div>\n");
      out.write("\t<div class=\"row\">\n");
      out.write("\t\t<!-- precio + stock + tienda -->\n");
      out.write("\t\t<div class=\"col-sm-3 col-sm-offset-1\">\n");
      out.write("\t\t\t<div class=\"row\"><h3>Precio actual</h3></div>\n");
      out.write("\t\t\t<div class=\"row\"><p class=\"lead\">");
      out.print( ServletUtils.fmt(producto.getPrecio()) );
      out.write("</p></div>\n");
      out.write("\t\t\t<div class=\"row\"><a href=\"");
      out.print( "ModificarPrecio?id=" + producto.getId() );
      out.write("\" class=\"btn btn-primary\" role =\"button\">Modificar precio</a></div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-sm-3 col-sm-offset-1\">\n");
      out.write("\t\t\t<div class=\"row\"><h3>Stock actual</h3></div>\n");
      out.write("\t\t\t<div class=\"row\"><p class=\"lead\">");
      out.print( producto.getStock() );
      out.write("</p></div>\n");
      out.write("\t\t\t<div class=\"row\"><a href=\"");
      out.print( "ModificarStock?id=" + producto.getId() );
      out.write("\" class=\"btn btn-primary\" role =\"button\">Modificar stock</a></div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-sm-3 col-sm-offset-1\">\n");
      out.write("\t\t\t<div class=\"row\"><h3>Tienda</h3></div>\n");
      out.write("\t\t\t<div class=\"row\"><p class=\"lead\">");
      out.print( producto.getTienda().getNombre() );
      out.write("</p></div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\t<div class=\"row\">\n");
      out.write("\t\t<!-- gráficas -->\n");
      out.write("\t\t<div class=\"col-sm-6\" id=\"chart_div\" style=\"width:500; height:300\"></div>\n");
      out.write("\t\t<div class=\"col-sm-6\" id=\"chart_div2\" style=\"width:500; height:300\"></div>\n");
      out.write("\t</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\t<!--Load the AJAX API-->\n");
      out.write("    <script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script>\n");
      out.write("    <script type=\"text/javascript\">\n");
      out.write("\n");
      out.write("      // Load the Visualization API and the piechart package.\n");
      out.write("      google.load('visualization', '1.0', {'packages':['corechart']});\n");
      out.write("\n");
      out.write("      // Set a callback to run when the Google Visualization API is loaded.\n");
      out.write("      google.setOnLoadCallback(drawChart);\n");
      out.write("\n");
      out.write("      // Callback that creates and populates a data table,\n");
      out.write("      // instantiates the bar chart, passes in the data and\n");
      out.write("      // draws it.\n");
      out.write("      function drawChart() {\n");
      out.write("\n");
      out.write("        // Create the data table.\n");
      out.write("        var data = new google.visualization.DataTable();\n");
      out.write("        data.addColumn('string', 'Fechas');\n");
      out.write("        data.addColumn('number', 'Unidades');\n");
      out.write("        \n");
      out.write("        var size = ");
      out.print( productoVentasBlockWTOs.size() );
      out.write(";\n");
      out.write("        var population;\n");
      out.write("        if (size >= 5) {\n");
      out.write("        \tpopulation = 5;\n");
      out.write("        } else {\n");
      out.write("        \tpopulation = size;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        var i;\n");
      out.write("        for (i=size-population; i<size; i++) {\n");
      out.write("        \t\n");
      out.write("        \t// data.addRow(['field' + i,  ");
      out.print( productoVentasBlockWTOs.get(i).getVentas() );
      out.write("]);\n");
      out.write("        \tdata.addRow(['prueba', 1]);\n");
      out.write("        \t\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        // Set chart options\n");
      out.write("        var options = {'title': 'Unidades vendidas y stock por fecha',\n");
      out.write("                       'width':500,\n");
      out.write("                       'height':300};\n");
      out.write("        \n");
      out.write("        // Instantiate and draw our chart, passing in some options.\n");
      out.write("        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));\n");
      out.write("        chart.draw(data, options);\n");
      out.write("        \n");
      out.write("        // chart 2\n");
      out.write("        var data2 = new google.visualization.DataTable();\n");
      out.write("        data2.addColumn('string', 'Fechas');\n");
      out.write("        data2.addColumn('number', 'Unidades');\n");
      out.write("        \n");
      out.write("        var options2 = {'title': 'Unidades vendidas por precio',\n");
      out.write("                'width':500,\n");
      out.write("                'height':300};\n");
      out.write("        \n");
      out.write("        // usar add row individual\n");
      out.write("        data2.addRows([\n");
      out.write("\t\t\t['11-06 to 18-06', 10]\n");
      out.write("        ]);\n");
      out.write("        \n");
      out.write("     \t// Instantiate and draw our chart, passing in some options.\n");
      out.write("        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div2'));\n");
      out.write("        chart.draw(data2, options2);\n");
      out.write("      }\n");
      out.write("    </script>\n");
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

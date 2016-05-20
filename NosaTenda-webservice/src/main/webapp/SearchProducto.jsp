<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Buscar Productos</title>
</head>

<body>

<%-- Get errors. --%>

<%
	String stockErrorMessage = "";

	Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
	
	if (errors != null) {
		
		String errorHeader = "<font color=\"red\"><b>";
		String errorFooter = "</b></font>";
		
		if (errors.containsKey("stock")) {
			stockErrorMessage = errorHeader + errors.get("stock") + errorFooter;
		}
	}
	
	String nombre = request.getParameter("nombre");
	if (nombre == null) {
		nombre = "";
	}
%>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="searchproducto" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Buscar producto</h1>
</div>

<div class="container">

	<form method="GET" action="SearchProducto" class="form-horizontal" role="form">
	
		<%-- Nombre --%>
		<div class="form-group">
			<label class="control-label col-sm-2" for="nombre">Nombre:</label>
			<div class="col-sm-8">
		        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Introduzca un nombre" value="<%= nombre %>" maxlength="100">
		    </div>
		    <div class="col-sm-2">
		    </div>
		</div>	
		<%-- Stock --%>
		<div class="form-group <% if (!stockErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
			<label class="control-label col-sm-2" for="stock">Stock:</label>
			<div class="col-sm-8">
		        <input type="number" class="form-control" id="stock" name="stock" placeholder="Introduzca el stock" maxlength="8" min="0">
		        <% if (!stockErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
		        <span class="help-block">Se buscarán los productos cuyo stock sea menor o igual.</span>
		    </div>
		    <div class="col-sm-2">
		    	<%= stockErrorMessage %>
		    </div>
		</div>
		<%-- Create button --%>
		<div class="form-group">        
	      <div class="col-sm-offset-2 col-sm-10">
	        <input type="submit" class="btn btn-primary" value="Buscar">
	      </div>
	    </div>
	
	</form>
	
</div>


</body>
</html>
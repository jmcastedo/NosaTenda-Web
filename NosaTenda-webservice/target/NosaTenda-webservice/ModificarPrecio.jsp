<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.Map,
		java.util.List,
		es.udc.jcastedo.NosaTenda.webservice.service.ProductoWTO" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Modificar precio</title>
</head>
<body>

<%-- Get errors. --%>

<%
	String precioErrorMessage = "";

	Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
	
	if (errors != null) {
		
		String errorHeader = "<font color=\"red\"><b>";
		String errorFooter = "</b></font>";
		
		if (errors.containsKey("precio")) {
			precioErrorMessage = errorHeader + errors.get("precio") + 
				errorFooter;
		}
		
	}

	ProductoWTO producto = (ProductoWTO) request.getAttribute("producto");

	String precio = request.getParameter("precio");
	if (precio==null) {
		if (producto != null) {
			precio = Double.toString(producto.getPrecio());
		} else {
			precio="";
		}
	}
	
	String nombre = request.getParameter("nombre");
	if (nombre==null) {
		if (producto != null) {
			nombre = producto.getNombre();
		} else {
			nombre="";
		}
	}
	
	String id = request.getParameter("id");
	if (id==null) {
		if (producto != null) {
			id = Long.toString(producto.getId());
		} else {
			id="";
		}
	}
	
%>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="editprecioproducto" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Editar precio de <%= nombre %></h1>
</div>

<div class="container">

	<form method="POST" action="ModificarPrecio" class="form-horizontal" role="form">
	
			<input type="hidden" name="id" value="<%= id %>">
			<input type="hidden" name="nombre" value="<%= nombre %>">
		
			<sec:csrfInput />
			<%-- Precio --%>
			<div class="form-group <% if (!precioErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
				<label class="control-label col-sm-2" for="precio">Precio:</label>
				<div class="col-sm-8">
			        <input type="number" class="form-control" id="precio" name="precio" placeholder="Introduzca el precio" value="<%= precio %>" maxlength="8" min="0" step="0.01">
			        <% if (!precioErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
			    </div>
			    <div class="col-sm-2">
			    	<%= precioErrorMessage %>
			    </div>
			</div>
			<%-- Edit button --%>
			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <input type="submit" class="btn btn-primary" value="Modificar precio">
		        <a href="SeeAllProductos" class="btn btn-primary" role="button">Cancelar</a>
		      </div>
		    </div>
	</form>
	
</div>

</body>
</html>
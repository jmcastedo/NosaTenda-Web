<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.Map,
		java.util.List,
		es.udc.jcastedo.NosaTenda.webservice.service.TiendaWTO,
		es.udc.jcastedo.NosaTenda.webservice.service.ProductoWTO" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Editar producto</title>
</head>
<body>

<%-- Get errors. --%>
	
<%
	String nombreErrorMessage = "";
	String imagenErrorMessage = "";
	String precioErrorMessage = "";
	String stockErrorMessage = "";
	String tiendaErrorMessage = "";
	
	String selected = "selected";
	
	Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
	
	
	
	if (errors != null) {
	
		String errorHeader = "<font color=\"red\"><b>";
		String errorFooter = "</b></font>";
	
		if (errors.containsKey("nombre")) {
			nombreErrorMessage = errorHeader + errors.get("nombre") + 
				errorFooter;
		}
		
		if (errors.containsKey("imagen")) {
			imagenErrorMessage = errorHeader + errors.get("imagen") + 
				errorFooter;
		}
		
		if (errors.containsKey("precio")) {
			precioErrorMessage = errorHeader + errors.get("precio") + 
				errorFooter;
		}
		
		if (errors.containsKey("stock")) {
			stockErrorMessage = errorHeader + errors.get("stock") + 
				errorFooter;
		}
		
		if (errors.containsKey("tienda")) {
			tiendaErrorMessage = errorHeader + errors.get("tienda") + 
				errorFooter;
		}
	
	}
	
	ProductoWTO producto = (ProductoWTO) request.getAttribute("producto");
	
	String id = request.getParameter("id");
	if (id==null) {
		if (producto != null) {
			id = Long.toString(producto.getId());
		} else {
			id="";
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
	String descripcion = request.getParameter("descripcion");
	if (descripcion==null) {
		if (producto != null) {
			descripcion = producto.getDescripcion();
		} else {
			descripcion="";
		}
	}
	String imagen = request.getParameter("imagen");
	if (imagen==null) {
		if (producto != null) {
			imagen = producto.getImagen();
		} else {
			imagen="";
		}
	}
	String precio = request.getParameter("precio");
	if (precio==null) {
		if (producto != null) {
			precio = Double.toString(producto.getPrecio());
		} else {
			precio="";
		}
	}
	String stock = request.getParameter("stock");
	if (stock==null) {
		if (producto != null) {
			stock = Long.toString(producto.getStock());
		} else {
			stock="";
		}
	}
	
	String tiendaId = request.getParameter("tiendaId");
	if (tiendaId==null) {
		if (producto != null) {
			tiendaId = Long.toString(producto.getTienda().getId());
		} else {
			tiendaId="-1";
		}
	}
	
	List<TiendaWTO> tiendas = (List<TiendaWTO>) request.getAttribute("tiendas");

%>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="editproducto" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Editar producto</h1>
</div>

<div class="container">

	<form method="POST" action="EditarProducto" class="form-horizontal" role="form">
	
		<input type="hidden" name="id" value="<%= id %>">
		
		<sec:csrfInput />
		<%-- Nombre --%>
		<div class="form-group <% if (!nombreErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
			<label class="control-label col-sm-2" for="nombre">Nombre:</label>
			<div class="col-sm-8">
		        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Introduzca un nombre" value="<%= nombre %>" maxlength="100">
		        <% if (!nombreErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
		    </div>
		    <div class="col-sm-2">
		    	<%= nombreErrorMessage %>
		    </div>
		</div>
		<%-- Descripcion --%>
		<div class="form-group">
			<label class="control-label col-sm-2" for="descripcion">Descripción:</label>
			<div class="col-sm-8">
		        <textarea class="form-control" rows="5" id="descripcion" name="descripcion" placeholder="Introduzca una descripción" maxlength="450"><%= descripcion %></textarea>
		    </div>
		</div>
		<%-- Imagen --%>
		<div class="form-group <% if (!imagenErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
			<label class="control-label col-sm-2" for="imagen">URL de la imagen:</label>
			<div class="col-sm-8">
		        <input type="url" class="form-control" id="imagen" name="imagen" placeholder="Introduzca una URL con la imagen del producto" value="<%= imagen %>" maxlength="450" pattern="https?://.+">
		        <% if (!imagenErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
		        <span class="help-block">La URL debe empezar por "http://" o "https://"</span>
		    </div>
		    <div class="col-sm-2">
		    	<%= imagenErrorMessage %>
		    </div>
		</div>
		<%-- Precio --%>
		<!-- <div class="form-group <% if (!precioErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
			<label class="control-label col-sm-2" for="precio">Precio:</label>
			<div class="col-sm-8">
		        <input type="number" class="form-control" id="precio" name="precio" placeholder="Introduzca el precio" value="<%= precio %>" maxlength="8" min="0" step="0.01">
		        <% if (!precioErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
		    </div>
		    <div class="col-sm-2">
		    	<%= precioErrorMessage %>
		    </div>
		</div> -->
		<%-- Stock --%>
		<!-- <div class="form-group <% if (!stockErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
			<label class="control-label col-sm-2" for="stock">Stock:</label>
			<div class="col-sm-8">
		        <input type="number" class="form-control" id="stock" name="stock" placeholder="Introduzca el stock" value="<%= stock %>" maxlength="8" min="0">
		        <% if (!stockErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
		    </div>
		    <div class="col-sm-2">
		    	<%= stockErrorMessage %>
		    </div>
		</div> -->
		<%-- Tienda --%>
		<!-- <div class="form-group">
			<label class="control-label col-sm-2" for="tiendaId">Tienda:</label>
			<div class="col-sm-8">
	      		<select class="form-control" id="tiendaId" name="tiendaId">
	      			<% for (int i=0; i < tiendas.size(); i++) { %>
						<% if (producto != null) { %>
							<% if (producto.getTienda().getId() == tiendas.get(i).getId()) { %>
								<option value="<%= tiendas.get(i).getId() %>" selected>
									<%= tiendas.get(i).getNombre() %></option>
							<% } else { %>
								<option value="<%= tiendas.get(i).getId() %>">
									<%= tiendas.get(i).getNombre() %></option>
							<% } %>
						<% } else { %>
							<% if (Long.valueOf(tiendaId) == tiendas.get(i).getId()) { %>
								<option value="<%= tiendas.get(i).getId() %>" selected>
									<%= tiendas.get(i).getNombre() %></option>
							<% } else { %>
								<option value="<%= tiendas.get(i).getId() %>">
									<%= tiendas.get(i).getNombre() %></option>
							<% } %>
						<% } %>
					<% } %>
			    </select>
			</div>
			<div class="col-sm-2">
		    	<%= tiendaErrorMessage %>
		    </div>
		</div>	 -->
		<%-- Edit button --%>
		<div class="form-group">        
	      <div class="col-sm-offset-2 col-sm-10">
	        <input type="submit" class="btn btn-primary" value="Editar producto">
	        <a href="DetallesProducto?id=<%= id %>" class="btn btn-primary" role="button">Cancelar</a>
	      </div>
	    </div>
	
	</form>
	
</div>


</body>
</html>
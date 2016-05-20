<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="java.util.Map,
		java.util.List,
		es.udc.jcastedo.NosaTenda.webservice.service.TiendaWTO,
		es.udc.jcastedo.NosaTenda.webservice.service.LocalidadWTO,
		es.udc.jcastedo.NosaTenda.webservice.service.CategoriaWTO" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Crear Tienda</title>
</head>

<body>

<%-- Get errors. --%>
	
<%
	String nombreErrorMessage = "";
	String imagenErrorMessage = "";
	String nifErrorMessage = "";
	String direccionErrorMessage = "";
	String correoErrorMessage = "";
	String latErrorMessage = "";
	String lonErrorMessage = "";
	String phone1ErrorMessage = "";
	
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
		
		if (errors.containsKey("nif")) {
			nifErrorMessage = errorHeader + errors.get("nif") + 
				errorFooter;
		}
		
		if (errors.containsKey("direccion")) {
			direccionErrorMessage = errorHeader + errors.get("direccion") + 
				errorFooter;
		}
		
		if (errors.containsKey("correo")) {
			correoErrorMessage = errorHeader + errors.get("correo") + 
				errorFooter;
		}
		
		if (errors.containsKey("lat")) {
			latErrorMessage = errorHeader + errors.get("lat") + 
				errorFooter;
		}
		
		if (errors.containsKey("lon")) {
			lonErrorMessage = errorHeader + errors.get("lon") + 
				errorFooter;
		}
		
		if (errors.containsKey("phone1")) {
			phone1ErrorMessage = errorHeader + errors.get("phone1") + 
				errorFooter;
		}
	
	}
	
	String nombre = request.getParameter("nombre");
	if (nombre==null) {
		nombre="";
	}
	String nif = request.getParameter("nif");
	if (nif==null) {
		nif="";
	}
	String correo = request.getParameter("correo");
	if (correo==null) {
		correo="";
	}
	String web = request.getParameter("web");
	if (web==null) {
		web="";
	}
	String imagen = request.getParameter("imagen");
	if (imagen==null) {
		imagen="";
	}
	String direccion = request.getParameter("direccion");
	if (direccion==null) {
		direccion="";
	}
	String localidadId = request.getParameter("localidadId");
	if (localidadId==null) {
		localidadId="-1";
	}
	String cp = request.getParameter("cp");
	if (cp==null) {
		cp="";
	}
	String provincia = request.getParameter("provincia");
	if (provincia==null) {
		provincia="";
	}
	String lat = request.getParameter("lat");
	if (lat==null) {
		lat="";
	}
	String lon = request.getParameter("lon");
	if (lon==null) {
		lon="";
	}
	String phone1 = request.getParameter("phone1");
	if (phone1==null) {
		phone1="";
	}
	String phone2 = request.getParameter("phone2");
	if (phone2==null) {
		phone2="";
	}
	String fax = request.getParameter("fax");
	if (fax==null) {
		fax="";
	}
	
	List<LocalidadWTO> localidades = (List<LocalidadWTO>) request.getAttribute("localidades");
	
	List<CategoriaWTO> categorias = (List<CategoriaWTO>) request.getAttribute("categorias");

%>	

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="addtienda" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Añadir Tienda</h1>
</div>

<div class="container">

	<form method="POST" action="AddTienda" class="form-horizontal" role="form">
	
		<sec:csrfInput />
		<%-- Nombre & NIF --%>
		<div class="form-group <% if (!nombreErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
			<label class="control-label col-sm-2" for="nombre">Nombre:</label>
			<div class="col-sm-3">
		        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Introduzca un nombre" value="<%= nombre %>" maxlength="100">
		        <% if (!nombreErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
		    </div>
		    <label class="control-label col-sm-2" for="nif">NIF:</label>
			<div class="col-sm-3 <% if (!nifErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
		        <input type="text" class="form-control" id="nif" name="nif" placeholder="Introduzca un NIF" value="<%= nif %>" maxlength="16">
		        <% if (!nifErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
		    </div>
		</div>
		<%-- Correo --%>
		<div class="form-group">
			<label class="control-label col-sm-2" for="correo">Correo:</label>
			<div class="col-sm-8 <% if (!correoErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
		        <input type="email" class="form-control" id="correo" name="correo" placeholder="Introduzca un correo" value="<%= correo %>" maxlength="100">
		        <% if (!correoErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
		    </div>
		</div>
		<%-- Web --%>
		<div class="form-group">
		    <label class="control-label col-sm-2" for="web">Web:</label>
			<div class="col-sm-8">
		        <input type="url" class="form-control" id="web" name="web" placeholder="Introduzca una URL" value="<%= web %>" maxlength="200" pattern="https?://.+">
		    </div>
		</div>
		<%-- Imagen --%>
		<div class="form-group">
			<label class="control-label col-sm-2" for="imagen">URL de la imagen:</label>
			<div class="col-sm-8 <% if (!imagenErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
		        <input type="url" class="form-control" id="imagen" name="imagen" placeholder="Introduzca una URL con la imagen de la tienda" value="<%= imagen %>" maxlength="450" pattern="https?://.+">
		        <% if (!imagenErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
		    </div>
		</div>	
		<%-- Direccion & Localidad --%>
		<div class="form-group">
			<label class="control-label col-sm-2" for="direccion">Dirección:</label>
			<div class="col-sm-3 <% if (!direccionErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
		        <input type="text" class="form-control" id="direccion" name="direccion" placeholder="Introduzca una direccion" value="<%= direccion %>" maxlength="200">
		        <% if (!direccionErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
		    </div>
		    <label class="control-label col-sm-2" for="localidadId">Localidad:</label>
			<div class="col-sm-3">
				<select class="form-control" id="localidadId" name="localidadId">
					<% for (int i=0; i < localidades.size(); i++) { %>
						<% if (Long.valueOf(localidadId) == localidades.get(i).getId()) { %>
							<option value="<%= localidades.get(i).getId() %>" selected>
								<%= localidades.get(i).getNombre() %></option>
						<% } else { %>
							<option value="<%= localidades.get(i).getId() %>">
								<%= localidades.get(i).getNombre() %></option>
						<% } %>
					<% } %> 
				</select>
		    </div>
		</div>	
		<%-- CP & Provincia --%>
		<div class="form-group">
			<label class="control-label col-sm-2" for="cp">Código Postal:</label>
			<div class="col-sm-3">
		        <input type="text" class="form-control" id="cp" name="cp" placeholder="Introduzca un código postal" value="<%= cp %>" maxlength="16">
		    </div>
		    <label class="control-label col-sm-2" for="provincia">Provincia:</label>
			<div class="col-sm-3">
		        <input type="text" class="form-control" id="provincia" name="provincia" placeholder="Introduzca una provincia" value="<%= provincia %>" maxlength="45">
		    </div>
		</div>
		<%-- Lat & Lon --%>
		<div class="form-group">
			<label class="control-label col-sm-2" for="lat">Latitud:</label>
			<div class="col-sm-3 <% if (!latErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
		        <input type="number" class="form-control" id="lat" name="lat" placeholder="Introduzca una latitud" value="<%= lat %>" step="0.000001">
		        <% if (!latErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
		    </div>
		    <label class="control-label col-sm-2" for="lon">Longitud:</label>
			<div class="col-sm-3 <% if (!lonErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
		        <input type="number" class="form-control" id="lon" name="lon" placeholder="Introduzca una longitud" value="<%= lon %>" step="0.000001">
		        <% if (!lonErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
		    </div>
		</div>
		<%-- Phone1 & Phone2 --%>
		<div class="form-group">
			<label class="control-label col-sm-2" for="phone1">Teléfono 1:</label>
			<div class="col-sm-3 <% if (!phone1ErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
		        <input type="text" class="form-control" id="phone1" name="phone1" placeholder="Introduzca un teléfono" value="<%= phone1 %>" maxlength="32">
		        <% if (!phone1ErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
		    </div>
		    <label class="control-label col-sm-2" for="phone2">Teléfono 2:</label>
			<div class="col-sm-3">
		        <input type="text" class="form-control" id="phone2" name="phone2" placeholder="Introduzca otro teléfono" value="<%= phone2 %>" maxlength="32">
		    </div>
		</div>
		<%-- Fax --%>
		<div class="form-group">
			<label class="control-label col-sm-2" for="fax">Fax:</label>
			<div class="col-sm-3">
		        <input type="text" class="form-control" id="fax" name="fax" placeholder="Introduzca un fax" value="<%= fax %>" maxlength="32">
		    </div>
		</div>
		<%-- Categorias --%>
		<div class="form-group">
		    <label class="control-label col-sm-2" for="categorias">Categorías:</label>
		    <div class="col-sm-3">
				<select multiple class="form-control" id="categorias" name="categorias_group">
					<% for (int i=0; i < categorias.size(); i++) { %>
						<option value="<%= categorias.get(i).getId() %>">
						<%= categorias.get(i).getNombre() %></option>
					<% } %> 
				</select>
			</div>
		</div>
		<br>
		<%-- Create button --%>
		<div class="form-group">        
	      <div class="col-sm-offset-2 col-sm-10">
	        <input type="submit" class="btn btn-primary" value="Crear tienda">
	      </div>
	    </div>
	
	</form>
	<br>
</div>


</body>

</html>

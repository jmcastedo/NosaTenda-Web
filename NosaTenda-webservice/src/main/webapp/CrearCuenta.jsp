<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.Map" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Crear Cuenta</title>
</head>
<body>

<%
	
	Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
	
	String correoErrorMessage = "";
	String passwordErrorMessage = "";
	
	if (errors != null) {
		
		String errorHeader = "<font color=\"red\"><b>";
		String errorFooter = "</b></font>";
		
		if (errors.containsKey("correo")) {
			correoErrorMessage = errorHeader + errors.get("correo") + 
				errorFooter;
		}
		
		if (errors.containsKey("password")) {
			passwordErrorMessage = errorHeader + errors.get("password") + 
				errorFooter;
		}
		
	}
	
	String correo = request.getParameter("correo");
	if (correo==null) {
		correo="";
	}
	String nombre = request.getParameter("nombre");
	if (nombre==null) {
		nombre="";
	}
	String nif = request.getParameter("nif");
	if (nif==null) {
		nif="";
	}
	String apellidos = request.getParameter("apellidos");
	if (apellidos==null) {
		apellidos="";
	}
	String direccion = request.getParameter("direccion");
	if (direccion==null) {
		direccion="";
	}
	String localidad = request.getParameter("localidad");
	if (localidad==null) {
		localidad="";
	}
	String cp = request.getParameter("cp");
	if (cp==null) {
		cp="";
	}
	String provincia = request.getParameter("provincia");
	if (provincia==null) {
		provincia="";
	}
	String phone1 = request.getParameter("phone1");
	if (phone1==null) {
		phone1="";
	}
	String phone2 = request.getParameter("phone2");
	if (phone2==null) {
		phone2="";
	}
	
	
%>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="newcuenta" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Crear Cuenta</h1>
</div>

<div class="container">

	<form method="POST" action="CrearCuenta" class="form-horizontal" role="form">
	
		<sec:csrfInput />
		
		<%-- Correo --%>
		<div class="form-group <% if (!correoErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
			<label class="control-label col-sm-2" for="correo">Correo:</label>
			<div class="col-sm-8">
		        <input type="email" class="form-control" id="correo" name="correo" placeholder="Introduzca un correo" value="<%= correo %>" maxlength="100">
		        <% if (!correoErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
		    </div>
		    <div class="col-sm-2">
		    	<%= correoErrorMessage %>
		    </div>
		</div>
		<%-- Password --%>
		<div class="form-group <% if (!passwordErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
			<label class="control-label col-sm-2" for="password">Contraseña:</label>
			<div class="col-sm-8">
		        <input type="password" class="form-control" id="password" name="password" placeholder="Introduzca una contraseña" maxlength="12">
		        <% if (!passwordErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
		    </div>
		    <div class="col-sm-2">
		    	<%= passwordErrorMessage %>
		    </div>
		</div>
		<%-- Confirm Password --%>
		<div class="form-group <% if (!passwordErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
			<label class="control-label col-sm-2" for="cpassword">Confirmar contraseña:</label>
			<div class="col-sm-8">
		        <input type="password" class="form-control" id="cpassword" name="cpassword" placeholder="Vuelva a escribir la contraseña" maxlength="12">
		        <% if (!passwordErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
		    </div>
		    <div class="col-sm-2">
		    	<%= passwordErrorMessage %>
		    </div>
		</div>
		<%-- Nombre & Nif --%>
		<div class="form-group">
			<label class="control-label col-sm-2" for="nombre">Nombre:</label>
			<div class="col-sm-3">
		        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Introduzca un nombre" value="<%= nombre %>" maxlength="100">
		    </div>
		    <label class="control-label col-sm-2" for="nif">NIF:</label>
			<div class="col-sm-3">
		        <input type="text" class="form-control" id="nif" name="nif" placeholder="Introduzca un NIF" value="<%= nif %>" maxlength="16">
		    </div>
		</div>
		<%-- Apellidos --%>
		<div class="form-group">
			<label class="control-label col-sm-2" for="apellidos">Apellidos:</label>
			<div class="col-sm-8">
		        <input type="text" class="form-control" id="apellidos" name="apellidos" placeholder="Introduzca los apellidos" value="<%= apellidos %>" maxlength="200">
		    </div>
		</div>
		<%-- Direccion & Localidad --%>
		<div class="form-group">
			<label class="control-label col-sm-2" for="direccion">Direccion:</label>
			<div class="col-sm-3">
		        <input type="text" class="form-control" id="direccion" name="direccion" placeholder="Introduzca una direccion" value="<%= direccion %>" maxlength="200">
		    </div>
		    <label class="control-label col-sm-2" for="localidad">Localidad:</label>
			<div class="col-sm-3">
		        <input type="text" class="form-control" id="localidad" name="localidad" placeholder="Introduzca una localidad" value="<%= localidad %>" maxlength="45">
		    </div>
		</div>
		<%-- CP & Provincia --%>
		<div class="form-group">
			<label class="control-label col-sm-2" for="cp">CP:</label>
			<div class="col-sm-3">
		        <input type="text" class="form-control" id="cp" name="cp" placeholder="Introduzca un CP" value="<%= cp %>" maxlength="16">
		    </div>
		    <label class="control-label col-sm-2" for="provincia">Provincia:</label>
			<div class="col-sm-3">
		        <input type="text" class="form-control" id="provincia" name="provincia" placeholder="Introduzca una provincia" value="<%= provincia %>" maxlength="45">
		    </div>
		</div>
		<%-- Phone1 & Phone2 --%>
		<div class="form-group">
			<label class="control-label col-sm-2" for="phone1">Phone1:</label>
			<div class="col-sm-3">
		        <input type="text" class="form-control" id="phone1" name="phone1" placeholder="Introduzca un teléfono" value="<%= phone1 %>" maxlength="32">
		    </div>
		    <label class="control-label col-sm-2" for="phone2">Phone2:</label>
			<div class="col-sm-3">
		        <input type="text" class="form-control" id="phone2" name="phone2" placeholder="Introduzca un teléfono" value="<%= phone2 %>" maxlength="32">
		    </div>
		</div>
		<%-- Create button --%>
		<div class="form-group">        
	      <div class="col-sm-offset-2 col-sm-10">
		  	<input type="submit" class="btn btn-primary" value="Crear cuenta">
	      </div>
	    </div>
		
			
	
	</form>
	
</div>

</body>
</html>
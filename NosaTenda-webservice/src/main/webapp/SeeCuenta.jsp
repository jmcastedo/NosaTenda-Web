<%@page import="es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.Map,
    	es.udc.jcastedo.NosaTenda.webservice.service.EmpleadoWTO,
    	es.udc.jcastedo.NosaTenda.webservice.service.EmpleadoInfoWTO,
    	es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Cuenta</title>
</head>
<body>

<%
	EmpleadoWTO empleado = (EmpleadoWTO) request.getAttribute("empleado");
	EmpleadoInfoWTO empleadoInfo = (EmpleadoInfoWTO) request.getAttribute("empleadoInfo");

	Boolean edit = (Boolean) request.getAttribute("edit");
	
	String nombre = "";
	String apellidos = "";
	String direccion = "";
	String localidad = "";
	String cp = "";
	String provincia = "";
	String phone1 = "";
	String phone2 = "";
	
	if (empleadoInfo != null) {
		nombre = empleadoInfo.getNombre();
		apellidos = empleadoInfo.getApellidos();
		direccion = empleadoInfo.getDireccion();
		localidad = empleadoInfo.getLocalidad();
		cp = empleadoInfo.getCp();
		provincia = empleadoInfo.getProvincia();
		phone1 = empleadoInfo.getPhone1();
		phone2 = empleadoInfo.getPhone2();
	}
	
	
%>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="seecuenta" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Cuenta</h1>
</div>

<div class="container">

	<div class="col-sm-2">
		<jsp:include page="SideBar.jsp" >
		  <jsp:param name="sidemenu" value="perfil" />
		</jsp:include>
	</div>
	
	<div class="col-sm-10">

	<form method="POST" action="SeeCuentaEdit" class="form-horizontal" role="form">
	
		<sec:csrfInput />
		
		<% if (!edit) { %><fieldset disabled><% } %>
			<%-- Correo --%>
			<div class="form-group">
				<label class="control-label col-sm-2" for="correo">Correo:</label>
				<div class="col-sm-8">
			        <input type="text" class="form-control" id="correo" name="correo" placeholder="Introduzca un correo" value="<%= empleado.getCorreo() %>" maxlength="100" readonly>
			    </div>
			</div>
			<%-- Permisos & Activado --%>
			<div class="form-group">
				<label class="control-label col-sm-2" for="permisos">Permisos:</label>
				<div class="col-sm-3">
			        <input type="text" class="form-control" id="permisos" name="permisos" value="<%= ServletUtils.niceRoles(empleado.getRole()) %>" maxlength="100" disabled>
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
			        <input type="text" class="form-control" id="nif" name="nif" placeholder="Introduzca un NIF" value="<%= ServletUtils.noNulls(empleado.getNif()) %>" maxlength="16">
			    </div>
			</div>
			<%-- Apellidos --%>
			<div class="form-group">
				<label class="control-label col-sm-2" for="apellidos">Apellidos:</label>
				<div class="col-sm-8">
			        <input type="text" class="form-control" id="apellidos" name="apellidos" placeholder="Introduzca los apellidos" value="<%= ServletUtils.noNulls(apellidos) %>" maxlength="200">
			    </div>
			</div>
			<%-- Direccion & Localidad --%>
			<div class="form-group">
				<label class="control-label col-sm-2" for="direccion">Direccion:</label>
				<div class="col-sm-3">
			        <input type="text" class="form-control" id="direccion" name="direccion" placeholder="Introduzca una direccion" value="<%= ServletUtils.noNulls(direccion) %>" maxlength="200">
			    </div>
			    <label class="control-label col-sm-2" for="localidad">Localidad:</label>
				<div class="col-sm-3">
			        <input type="text" class="form-control" id="localidad" name="localidad" placeholder="Introduzca una localidad" value="<%= ServletUtils.noNulls(localidad) %>" maxlength="45">
			    </div>
			</div>
			<%-- CP & Provincia --%>
			<div class="form-group">
				<label class="control-label col-sm-2" for="cp">CP:</label>
				<div class="col-sm-3">
			        <input type="text" class="form-control" id="cp" name="cp" placeholder="Introduzca un CP" value="<%= ServletUtils.noNulls(cp) %>" maxlength="16">
			    </div>
			    <label class="control-label col-sm-2" for="provincia">Provincia:</label>
				<div class="col-sm-3">
			        <input type="text" class="form-control" id="provincia" name="provincia" placeholder="Introduzca una provincia" value="<%= ServletUtils.noNulls(provincia) %>" maxlength="45">
			    </div>
			</div>
			<%-- Phone1 & Phone2 --%>
			<div class="form-group">
				<label class="control-label col-sm-2" for="phone1">Phone1:</label>
				<div class="col-sm-3">
			        <input type="text" class="form-control" id="phone1" name="phone1" placeholder="Introduzca un teléfono" value="<%= ServletUtils.noNulls(phone1) %>" maxlength="32">
			    </div>
			    <label class="control-label col-sm-2" for="phone2">Phone2:</label>
				<div class="col-sm-3">
			        <input type="text" class="form-control" id="phone2" name="phone2" placeholder="Introduzca un teléfono" value="<%= ServletUtils.noNulls(phone2) %>" maxlength="32">
			    </div>
			</div>
		<% if (!edit) { %></fieldset><% } %>
			<%-- Edit button --%>
			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		      	<% if (!edit) { %>
		      		<a href="SeeCuentaEdit" class="btn btn-primary" role ="button">Editar Perfil</a>
		      	<% } else { %>
			        <input type="submit" class="btn btn-primary" value="Confirmar cambios">
			        <a href="SeeCuenta" class="btn btn-primary" role="button">Cancelar</a>
		        <% } %>
		      </div>
		    </div>
		
			
	
	</form>
	
	</div>
	
</div>

</body>
</html>
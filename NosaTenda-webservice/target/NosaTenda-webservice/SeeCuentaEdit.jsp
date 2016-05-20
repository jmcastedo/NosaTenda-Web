<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import=
    	"es.udc.jcastedo.NosaTenda.webservice.service.EmpleadoWTO,
    	es.udc.jcastedo.NosaTenda.webservice.service.EmpleadoInfoWTO" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Ver Cuenta</title>
</head>
<body>

<%
	EmpleadoWTO empleado = (EmpleadoWTO) request.getAttribute("empleado");
	EmpleadoInfoWTO empleadoInfo = (EmpleadoInfoWTO) request.getAttribute("empleadoInfo");

%>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="seecuenta" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Ver Cuenta</h1>
</div>

<div class="container">

	<form method="POST" action="SeeCuenta" class="form-horizontal" role="form">
	
		<sec:csrfInput />
		
		<input type="hidden" name="correoOld" value="empleado1@correo.es">
		
		<fieldset disabled>
			<%-- Correo --%>
			<div class="form-group">
				<label class="control-label col-sm-2" for="correo">Correo:</label>
				<div class="col-sm-8">
			        <input type="text" class="form-control" id="correo" name="correo" placeholder="Introduzca un correo" value="<%= empleado.getCorreo() %>" maxlength="100">
			    </div>
			</div>
			<%-- Password --%>
			<div class="form-group">
				<label class="control-label col-sm-2" for="password">Password:</label>
				<div class="col-sm-8">
			        <input type="password" class="form-control" id="password" name="password" placeholder="Introduzca un password" value="********" maxlength="12">
			    </div>
			</div>
			<%-- Permisos & Activado --%>
			<div class="form-group">
				<label class="control-label col-sm-2" for="permisos">Permisos:</label>
				<div class="col-sm-3">
			        <input type="text" class="form-control" id="permisos" name="permisos" value="<%= empleado.getRole() %>" maxlength="100" disabled>
			    </div>
			    <label class="control-label col-sm-2" for="activado">Activado:</label>
				<div class="col-sm-3">
			        <input type="text" class="form-control" id="activado" name="activado" value="<%= empleado.getActivado() %>" maxlength="5" disabled>
			    </div>
			</div>
			<%-- Nombre & Nif --%>
			<div class="form-group">
				<label class="control-label col-sm-2" for="nombre">Nombre:</label>
				<div class="col-sm-3">
			        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Introduzca un nombre" value="<%= empleadoInfo.getNombre() %>" maxlength="100">
			    </div>
			    <label class="control-label col-sm-2" for="nif">NIF:</label>
				<div class="col-sm-3">
			        <input type="text" class="form-control" id="nif" name="nif" placeholder="Introduzca un NIF" value="<%= empleado.getNif() %>" maxlength="16">
			    </div>
			</div>
			<%-- Apellidos --%>
			<div class="form-group">
				<label class="control-label col-sm-2" for="apellidos">Apellidos:</label>
				<div class="col-sm-8">
			        <input type="text" class="form-control" id="apellidos" name="apellidos" placeholder="Introduzca los apellidos" value="<%= empleadoInfo.getApellidos() %>" maxlength="200">
			    </div>
			</div>
			<%-- Direccion & Localidad --%>
			<div class="form-group">
				<label class="control-label col-sm-2" for="direccion">Direccion:</label>
				<div class="col-sm-3">
			        <input type="text" class="form-control" id="direccion" name="direccion" placeholder="Introduzca una direccion" value="<%= empleadoInfo.getDireccion() %>" maxlength="200">
			    </div>
			    <label class="control-label col-sm-2" for="localidad">Localidad:</label>
				<div class="col-sm-3">
			        <input type="text" class="form-control" id="localidad" name="localidad" placeholder="Introduzca una localidad" value="<%= empleadoInfo.getLocalidad() %>" maxlength="45">
			    </div>
			</div>
			<%-- CP & Provincia --%>
			<div class="form-group">
				<label class="control-label col-sm-2" for="cp">CP:</label>
				<div class="col-sm-3">
			        <input type="text" class="form-control" id="cp" name="cp" placeholder="Introduzca un CP" value="<%= empleadoInfo.getCp() %>" maxlength="16">
			    </div>
			    <label class="control-label col-sm-2" for="provincia">Provincia:</label>
				<div class="col-sm-3">
			        <input type="text" class="form-control" id="provincia" name="provincia" placeholder="Introduzca una provincia" value="<%= empleadoInfo.getProvincia() %>" maxlength="45">
			    </div>
			</div>
			<%-- Phone1 & Phone2 --%>
			<div class="form-group">
				<label class="control-label col-sm-2" for="phone1">Phone1:</label>
				<div class="col-sm-3">
			        <input type="text" class="form-control" id="phone1" name="phone1" placeholder="Introduzca un teléfono" value="<%= empleadoInfo.getPhone1() %>" maxlength="32">
			    </div>
			    <label class="control-label col-sm-2" for="phone2">Phone2:</label>
				<div class="col-sm-3">
			        <input type="text" class="form-control" id="phone2" name="phone2" placeholder="Introduzca un teléfono" value="<%= empleadoInfo.getPhone2() %>" maxlength="32">
			    </div>
			</div>
		</fieldset>
			<%-- Edit button --%>
			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <input type="submit" class="btn btn-primary" value="Editar cuenta">
		      </div>
		    </div>
	
	</form>
	
</div>

</body>
</html>
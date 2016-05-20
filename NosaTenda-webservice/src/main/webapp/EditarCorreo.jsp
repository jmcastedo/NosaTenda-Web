<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.Map,
    	es.udc.jcastedo.NosaTenda.webservice.service.EmpleadoWTO" %>
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

	Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
	
	String correoNewErrorMessage = "";
	String correoConfirmErrorMessage = "";
	
	if (errors != null) {
		
		String errorHeader = "<font color=\"red\"><b>";
		String errorFooter = "</b></font>";
		
		if (errors.containsKey("correonew")) {
			correoNewErrorMessage = errorHeader + errors.get("correonew") + 
				errorFooter;
		}
		
		if (errors.containsKey("ccorreonew")) {
			correoConfirmErrorMessage = errorHeader + errors.get("ccorreonew") + 
				errorFooter;
		}
	}
	
	String correonew = request.getParameter("correonew");
	if (correonew==null) {
		correonew="";
	}
	
	String correo = "";
	if (empleado!=null) {
		correo = empleado.getCorreo();
	} else {
		correo = (String) request.getAttribute("correo");
	}

%>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="editcuentacorreo" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Cuenta</h1>
</div>

<div class="container">

	<div class="col-sm-2">
		<jsp:include page="SideBar.jsp" >
		  <jsp:param name="sidemenu" value="correo" />
		</jsp:include>
	</div>
	
	<div class="col-sm-10">

		<form method="POST" action="EditarCorreo" class="form-horizontal" role="form">
		
			<sec:csrfInput />
	
			<%-- Correo Viejo --%>
			<div class="form-group">
				<label class="control-label col-sm-2" for="correo">Correo actual:</label>
				<div class="col-sm-8">
			        <input type="text" class="form-control" id="correo" name="correo" value=<%= correo %> maxlength="100" readonly>
			    </div>
			</div>
			<%-- Correo Nuevo --%>
			<div class="form-group <% if (!correoNewErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
				<label class="control-label col-sm-2" for="correonew">Nuevo correo:</label>
				<div class="col-sm-8">
			        <input type="email" class="form-control" id="correonew" name="correonew" placeholder="Introduzca el nuevo correo" value="<%= correonew %>" maxlength="100">
			        <% if (!correoNewErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
			    </div>
			    <div class="col-sm-2">
			    	<%= correoNewErrorMessage %>
			    </div>
			</div>
			<%-- Confirm Correo --%>
			<div class="form-group <% if (!correoConfirmErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
				<label class="control-label col-sm-2" for="ccorreonew">Confirmar nuevo correo:</label>
				<div class="col-sm-8">
			        <input type="email" class="form-control" id="ccorreonew" name="ccorreonew" placeholder="Vuelva a introducir el nuevo correo" maxlength="100">
			        <% if (!correoConfirmErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
			    </div>
			    <div class="col-sm-2">
			    	<%= correoConfirmErrorMessage %>
			    </div>
			</div>
			<%-- Edit button --%>
			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <input type="submit" class="btn btn-primary" value="Confirmar cambios">
		        <a href="SeeCuenta" class="btn btn-primary" role="button">Cancelar</a>
		      </div>
		    </div>
	
		</form>
	
	</div>
	
</div>

</body>
</html>
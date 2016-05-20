<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.Map" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Cuenta</title>
</head>
<body>

<%
	Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
	
	String passwordOldErrorMessage = "";
	String passwordNewErrorMessage = "";
	String passwordConfirmErrorMessage = "";
	
	if (errors != null) {
		
		String errorHeader = "<font color=\"red\"><b>";
		String errorFooter = "</b></font>";
		
		if (errors.containsKey("password")) {
			passwordOldErrorMessage = errorHeader + errors.get("password") + 
				errorFooter;
		}
		
		if (errors.containsKey("passwordnew")) {
			passwordNewErrorMessage = errorHeader + errors.get("passwordnew") + 
				errorFooter;
		}
		
		if (errors.containsKey("cpasswordnew")) {
			passwordConfirmErrorMessage = errorHeader + errors.get("cpasswordnew") + 
				errorFooter;
		}
	}

%>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="editcuentapassword" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Cuenta</h1>
</div>

<div class="container">

	<div class="col-sm-2">
		<jsp:include page="SideBar.jsp" >
		  <jsp:param name="sidemenu" value="password" />
		</jsp:include>
	</div>
	
	<div class="col-sm-10">

		<form method="POST" action="EditarPassword" class="form-horizontal" role="form">
		
			<sec:csrfInput />
	
			<%-- Password Vieja --%>
			<div class="form-group <% if (!passwordOldErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
				<label class="control-label col-sm-2" for="password">Contraseña antigua:</label>
				<div class="col-sm-8">
			        <input type="password" class="form-control" id="password" name="password" placeholder="Introduzca su contraseña" maxlength="12">
			        <% if (!passwordOldErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
			    </div>
			    <div class="col-sm-2">
			    	<%= passwordOldErrorMessage %>
			    </div>
			</div>
			<%-- Password Nueva --%>
			<div class="form-group <% if (!passwordNewErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
				<label class="control-label col-sm-2" for="passwordnew">Nueva contraseña:</label>
				<div class="col-sm-8">
			        <input type="password" class="form-control" id="passwordnew" name="passwordnew" placeholder="Introduzca la nueva contraseña" maxlength="12">
			        <% if (!passwordNewErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
			    </div>
			    <div class="col-sm-2">
			    	<%= passwordNewErrorMessage %>
			    </div>
			</div>
			<%-- Confirm Password --%>
			<div class="form-group <% if (!passwordConfirmErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
				<label class="control-label col-sm-2" for="cpasswordnew">Confirmar nueva contraseña:</label>
				<div class="col-sm-8">
			        <input type="password" class="form-control" id="cpasswordnew" name="cpasswordnew" placeholder="Vuelva a introducir la nueva contraseña" maxlength="12">
			        <% if (!passwordConfirmErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
			    </div>
			    <div class="col-sm-2">
			    	<%= passwordConfirmErrorMessage %>
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
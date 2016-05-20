<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Cuenta creada</title>
</head>

<body>

<%
	String id = request.getParameter("id");
 %>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="newcuentaexito" />
</jsp:include>

<div class="container text-center">
	<br>
	<h3 class="text-primary">Cuenta creada con éxito con Id <%= id %></h3>
	<br>
	<h5 class="text-info"><span class="glyphicon glyphicon glyphicon-info-sign"  aria-hidden="true"></span> Recuerde que no podrá acceder con esta cuenta hasta que sea activada por un administrador <span class="glyphicon glyphicon glyphicon-info-sign"  aria-hidden="true"></span></h5>
</div>


</body>
</html>
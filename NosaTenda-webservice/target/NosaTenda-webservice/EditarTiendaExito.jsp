<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Tienda editada</title>
</head>

<body>

<%
	String id = request.getParameter("id");
 %>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="edittiendaexito" />
</jsp:include>

<div class="container text-center">
	<br>
	<h3 class="text-primary">Tienda editada con éxito</h3>
	<br>
	<a href="DetallesTienda?id=<%= id %>" class="btn btn-primary btn-xs" role ="button">Volver</a>	
</div>


</body>
</html>
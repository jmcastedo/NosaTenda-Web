<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Tienda creada</title>
</head>

<body>

<%
	String id = request.getParameter("id");
 %>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="addtiendaexito" />
</jsp:include>

<div class="container text-center">
	<br>
	<h3 class="text-primary">Tienda creada con éxito</h3>
	<br>
	<div class="btn-group">
		<a href="AddTienda" class="btn btn-primary btn-xs" role ="button">Añadir otra tienda</a>
		<a href="DetallesTienda?id=<%= id %>" class="btn btn-primary btn-xs" role ="button">Ver detalles de la tienda</a>	
	</div>
</div>



</body>
</html>
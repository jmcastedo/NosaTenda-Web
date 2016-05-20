<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Producto editado</title>
</head>

<body>

<%
	String id = request.getParameter("id");
 %>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="editproductoexito" />
</jsp:include>

<div class="container text-center">
	<br>
	<h3 class="text-primary">Producto editado con éxito</h3>
	<br>
	<a href="DetallesProducto?id=<%= id %>" class="btn btn-primary btn-xs" role ="button">Volver</a>	
</div>


</body>
</html>
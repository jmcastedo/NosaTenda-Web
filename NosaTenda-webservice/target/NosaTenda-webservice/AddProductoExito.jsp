<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Producto creado</title>
</head>

<body>

<%
	String id = request.getParameter("id");
 %>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="addproductoexito" />
</jsp:include>

<div class="container text-center">
	<br>
	<h3 class="text-primary">Producto creado con éxito</h3>
	<br>
	<div class="btn-group">
		<a href="AddProducto" class="btn btn-primary btn-xs" role ="button">Añadir otro producto</a>
		<a href="DetallesProducto?id=<%= id %>" class="btn btn-primary btn-xs" role ="button">Ver detalles del producto</a>	
	</div>
</div>



</body>
</html>
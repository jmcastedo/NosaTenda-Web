<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Producto en venta</title>
</head>

<body>

<%
	String id = request.getParameter("id");
 %>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="ventaproductoexito" />
</jsp:include>

<div class="container text-center">
	<br>
	<h3 class="text-primary">Producto puesto en venta con éxito</h3>
	<br>
	<div class="btn-group">
		<a href="DetallesProducto?id=<%= id %>" class="btn btn-primary btn-xs" role ="button">Volver a detalles del producto</a>
		<a href="SeeAllProductos" class="btn btn-primary btn-xs" role ="button">Ver todos los productos</a>
			
	</div>
</div>


</body>
</html>
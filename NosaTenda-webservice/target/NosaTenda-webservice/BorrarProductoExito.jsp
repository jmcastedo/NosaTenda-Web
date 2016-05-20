<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	
<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Producto borrado</title>
</head>

<body>

<%
	String id = request.getParameter("id");
 %>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="deleteproductoexito" />
</jsp:include>

<div class="container text-center">
	<br>
	<h3 class="text-primary">Producto con Id <%= id %> borrado con éxito</h3>
	<br>
	<a href="SeeProductos" class="btn btn-primary btn-xs" role ="button">Volver a la lista de productos</a>	
</div>


</body>
</html>
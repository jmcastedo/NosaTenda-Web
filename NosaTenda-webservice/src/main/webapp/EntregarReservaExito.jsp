<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	
<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Reserva entregada</title>
</head>

<body>

<%
	String id = request.getParameter("id");
 %>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="cancelarreservaexito" />
</jsp:include>

<div class="container text-center">
	<br>
	<h3 class="text-primary">Reserva con Id <%= id %> entregada con éxito</h3>
	<br>
	<a href="SeeReservas" class="btn btn-primary btn-xs" role ="button">Volver a la lista de reservas</a>	
</div>


</body>
</html>
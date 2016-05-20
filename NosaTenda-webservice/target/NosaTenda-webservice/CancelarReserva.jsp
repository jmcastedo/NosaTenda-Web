<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Nosa Tenda - Cancelar Reservas</title>
</head>

<body text="#000000" bgcolor="#ffffff">

<%
	String id = request.getParameter("id");
 %>

<div align="center">
<p>
	<font color="#000099" face="Arial, Helvetica, san-serif">
		<b>¿Está seguro de que desea cancelar la reserva con Id = <%= id %>?</b>
	</font>
</p>

<div align="center">
	<form method="POST" action="CancelarReserva?reservaId=<%= id %>">
		<input type="submit" value="Confirmar">
	</form>
	<form method="GET" action="SeeReservas">
		<input type="submit" value="Cancelar">
	</form>
</div>

</div>
</body>
</html>
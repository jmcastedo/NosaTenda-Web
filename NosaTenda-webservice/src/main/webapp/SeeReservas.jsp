<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="es.udc.jcastedo.NosaTenda.webservice.service.ReservaInfoWTO,
				java.util.List,
				java.util.ArrayList,
				java.text.SimpleDateFormat" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Ver Reservas</title>
</head>

<body>
<%
	List<ReservaInfoWTO> reservaWTOs = (List<ReservaInfoWTO>)request.getAttribute("reservaWTOs");
%>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="reserva" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Lista de Reservas</h1>
</div>

<div class="container">

<%! SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yyyy"); %>

<%
if (reservaWTOs!=null && reservaWTOs.size() > 0) {
%>

	<table class="table table-hover table-condensed">
	<thead>
		<tr>
				<th></th>
				<th>Producto</th>
				<th></th>
				<th class="text-center">Tienda</th>
				<th class="text-center">Fecha de la reserva</th>
				<th class="text-right">Unidades</th>
				<th class="text-right">Subtotal</th>
				<th class="text-right">Total</th>
				<th class="text-center">Estado</th>
				<th></th>
		</tr>
	</thead>
	
	<% for (int i=0; i < reservaWTOs.size(); i++) { %>
	
		<tbody>
			<tr>
				<th><%= i+1 %></th>
				<td><%= reservaWTOs.get(i).getProducto().getNombre() %></td>
				<td>
					<a href="<%= "DetallesProducto?id=" + reservaWTOs.get(i).getProducto().getId() %>" class="btn btn-primary btn-xs" role ="button">Detalles</a>
				</td>
				<td class="text-center"><%= reservaWTOs.get(i).getProducto().getTienda().getNombre() %></td>
				<td class="text-center"><%= date_format.format((reservaWTOs.get(i).getFecha()).getTime()) %></td>
				<td class="text-right"><%= reservaWTOs.get(i).getUnidades() %></td>
				<td class="text-right"><%= reservaWTOs.get(i).getPrecio() %></td>
				<td class="text-right"><%= reservaWTOs.get(i).getTotal() %></td>
				<td class="text-center"><%= reservaWTOs.get(i).getEstado() %></td>
				<td>
					<div class="btn-group-vertical">
						<% if (reservaWTOs.get(i).getEstado() == ReservaInfoWTO.ReservaStateWTO.PENDIENTE) { %>
							<button type="button" class="btn btn-primary btn-xs" role ="button" data-toggle="modal" data-target="#myModalE<%= i+1 %>">Entregar</button>
							<button type="button" class="btn btn-primary btn-xs" role ="button" data-toggle="modal" data-target="#myModalC<%= i+1 %>">Cancelar</button>
						<% } else { %>
							<a href="#" class="btn btn-primary btn-xs disabled" role ="button">Entregar</a>
							<a href="#" class="btn btn-primary btn-xs disabled" role ="button">Cancelar</a>
						<% } %>
					</div>
					
					<div class="modal fade" id="myModalE<%= i+1 %>" role="dialog">
					    <div class="modal-dialog">
					    
					      <!-- Modal content-->
					      <div class="modal-content">
					        <div class="modal-header">
					          <button type="button" class="close" data-dismiss="modal">&times;</button>
					          <h4 class="modal-title">Está a punto de marcar una reserva como ENTREGADA <span class="glyphicon glyphicon-exclamation-sign text-danger"></h4>
					        </div>
					        <div class="modal-body">
					          <p>¿Está seguro?</p>
					        </div>
					        <div class="modal-footer">
					          <form method="POST" action="EntregarReserva?reservaId=<%= reservaWTOs.get(i).getId() %>">
					            <sec:csrfInput />
								<input type="submit" class="btn btn-primary" value="Confirmar">
								<button type="button" class="btn btn-primary" data-dismiss="modal">Cancelar</button>
							  </form>	
					          
					        </div>
					      </div>
					      
					    </div>
					 </div>
					 
					 <div class="modal fade" id="myModalC<%= i+1 %>" role="dialog">
					    <div class="modal-dialog">
					    
					      <!-- Modal content-->
					      <div class="modal-content">
					        <div class="modal-header">
					          <button type="button" class="close" data-dismiss="modal">&times;</button>
					          <h4 class="modal-title">Está a punto de marcar una reserva como CANCELADA <span class="glyphicon glyphicon-exclamation-sign text-danger"></h4>
					        </div>
					        <div class="modal-body">
					          <p>¿Está seguro?</p>
					        </div>
					        <div class="modal-footer">
					          <form method="POST" action="CancelarReserva?reservaId=<%= reservaWTOs.get(i).getId() %>">
					            <sec:csrfInput />
								<input type="submit" class="btn btn-primary" value="Confirmar">
								<button type="button" class="btn btn-primary" data-dismiss="modal">Cancelar</button>
							  </form>	
					          
					        </div>
					      </div>
					      
					    </div>
					 </div>
					
				</td>
			</tr>
		</tbody>
	<% } %>
	
	</table>
<%
} else {
%>

	<h4>No se encontraron reservas</h4>

<%
}
%>

</div>


</body>
</html>
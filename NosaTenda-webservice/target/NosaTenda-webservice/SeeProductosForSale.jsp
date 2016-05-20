<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="es.udc.jcastedo.NosaTenda.webservice.service.ProductoWTO,
	es.udc.jcastedo.NosaTenda.webservice.service.VentasBlockWTO,
	es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils,
	java.util.List,java.util.ArrayList,
	java.util.Calendar,java.text.SimpleDateFormat" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	
<jsp:include page="HeadConfig.jsp" />
    
	<title>Nosa Tenda - Ver Productos en Venta</title>
</head>

<body>
<%
	List<ProductoWTO> productoWTOs = (List<ProductoWTO>)request.getAttribute("productoWTOs");

	List<VentasBlockWTO> ventasBlockWTOs = (List<VentasBlockWTO>)request.getAttribute("ventasBlockWTOs");
%>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="productosale" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Lista de Productos en Venta</h1>
</div>

<%
if (productoWTOs!=null && productoWTOs.size() > 0) {
%>

	<div class="container">
	
		<% SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); %>
		
		<p class="text-right"><span class="glyphicon glyphicon-stop text-warning"></span> Venta a punto de finalizar</p>
		<p class="text-right"><span class="glyphicon glyphicon-stop text-danger"></span> Stock bajo</p>

		<table class="table table-hover table-condensed">
		<thead>
			<tr>
				<th></th>
				<th>Nombre</th>
				<th class="text-center">Tienda</th>
				<th class="text-right">Precio</th>
				<th class="text-right">Stock</th>
				<th class="text-right">Ventas en el período</th>
				<th class="text-center">Fecha de puesta en venta</th>
				<th class="text-center">Fecha de retirada</th>
				<th></th>
				
			
			</tr>
		</thead>
		
		<% for (int i=0; i < productoWTOs.size(); i++) { %>
		
			<%
				//String uriBorrar = "BorrarProducto.jsp?id=" + productoWTOs.get(i).getId();
				String uriEditar = "EditarProducto?id=" + productoWTOs.get(i).getId();
				String uriDetalles = "DetallesProducto?id=" + productoWTOs.get(i).getId();
			%>
		<tbody>
		
			<tr>
			
				<th><%= i+1 %></th>
				<td><%= productoWTOs.get(i).getNombre() %></td>
				<td class="text-center"><%= productoWTOs.get(i).getTienda().getNombre() %>
				<td class="text-right"><%= ServletUtils.fmt(productoWTOs.get(i).getPrecio()) %></td>
				<% if (productoWTOs.get(i).getStock() <= 5) { %>
					<td class="text-right danger">
				<% } else { %>
					<td class="text-right">
				<% } %>
						<%= productoWTOs.get(i).getStock() %>
					</td>
				<td class="text-right"><%= ventasBlockWTOs.get(i).getVentas() %></td>
				<td class="text-center"><%= date_format.format((productoWTOs.get(i).getFecha_puesta_venta()).getTime()) %></td>
				<% 	Calendar compare = Calendar.getInstance();
					compare.add(Calendar.DAY_OF_MONTH, 3);
					
				if (compare.compareTo(productoWTOs.get(i).getFecha_retirada()) >= 0 ) { %>
					<td class="text-center warning">
				<% } else { %>
					<td class="text-center">
				<% } %>
						<%= date_format.format((productoWTOs.get(i).getFecha_retirada()).getTime()) %>
					</td>
				<td>
					<a href="<%= uriDetalles %>" class="btn btn-primary btn-xs" role ="button">Detalles</a>
				
					<!-- 
					<div class="btn-group-vertical">
						<a href="<%= uriEditar %>" class="btn btn-primary btn-xs" role ="button">Editar</a>
						<button type="button" class="btn btn-primary btn-xs" role ="button" data-toggle="modal" data-target="#myModal<%= i+1 %>">Borrar</button>
					</div>
					 -->
					
					<div class="modal fade" id="myModal<%= i+1 %>" role="dialog">
					    <div class="modal-dialog">
					    
					      <!-- Modal content-->
					      <div class="modal-content">
					        <div class="modal-header">
					          <button type="button" class="close" data-dismiss="modal">&times;</button>
					          <h4 class="modal-title">Está a punto de borrar un producto <span class="glyphicon glyphicon-exclamation-sign text-danger"></h4>
					        </div>
					        <div class="modal-body">
					          <p>¿Está seguro?</p>
					        </div>
					        <div class="modal-footer">
					          <form method="POST" action="BorrarProducto?productoId=<%= productoWTOs.get(i).getId() %>">
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
	
	</div>
<%
} else {
%>

	<div class="container text-center">
		<br>
		<h4>No se encontraron productos</h4>
	</div>

<%
}
%>

</body>
</html>
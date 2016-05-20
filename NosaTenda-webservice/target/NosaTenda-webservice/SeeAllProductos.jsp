<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="es.udc.jcastedo.NosaTenda.webservice.service.ProductoWTO,
				java.util.List,
				java.util.ArrayList" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	
<jsp:include page="HeadConfig.jsp" />
    
	<title>Nosa Tenda - Ver Productos</title>
</head>

<body>
<%
	List<ProductoWTO> productoWTOs = (List<ProductoWTO>)request.getAttribute("productoWTOs");

	List<ProductoWTO> productoWTOsEnVenta = (List<ProductoWTO>)request.getAttribute("productoWTOsEnVenta");
%>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="allproducto" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Lista de Productos</h1>
</div>

<%
if (productoWTOs!=null && productoWTOs.size() > 0) {
%>

	<div class="container">
	
		<p class="text-right"><span class="glyphicon glyphicon-stop text-primary"></span> Productos en venta</p>

		<table class="table table-hover table-condensed">
		<thead>
			<tr>
				<th></th>
				<th>Nombre</th>
				<th class="text-center">Tienda</th>
				<th>Precio</th>
				<th>Stock</th>
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
		<% if (productoWTOsEnVenta.contains(productoWTOs.get(i))) { %>
			<tr class="info">
		<% } else { %>
			<tr>
		<% } %>
				<th><%= i+1 %></th>
				<td><%= productoWTOs.get(i).getNombre() %></td>
				<td class="text-center"><%= productoWTOs.get(i).getTienda().getNombre() %></td>
				<td><%= productoWTOs.get(i).getPrecio() %></td>
				<td><%= productoWTOs.get(i).getStock() %></td>
				<td>
					<a href="<%= uriDetalles %>" class="btn btn-primary btn-xs" role ="button">Detalles</a>
					
					<!-- 
					<div class="btn-group-vertical">
						<a href="<%= uriEditar %>" class="btn btn-primary btn-xs" role ="button">Editar</a>
						<a href="<%= uriDetalles %>" class="btn btn-primary btn-xs" role ="button">Detalles</a>
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
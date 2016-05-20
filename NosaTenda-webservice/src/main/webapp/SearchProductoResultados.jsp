<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="es.udc.jcastedo.NosaTenda.webservice.service.ProductoWTO,
				java.util.List,
				java.util.ArrayList,
				java.util.Calendar,java.text.SimpleDateFormat,
				es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils" %>
<!DOCTYPE html>
<html>
<head>
	
<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Búsqueda</title>
</head>

<body>
<%
	List<ProductoWTO> productoWTOs = (List<ProductoWTO>)request.getAttribute("productoWTOs");
%>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="searchproductoresult" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Resultados de la búsqueda (<%= productoWTOs.size() %>)</h1>
</div>



<%
if (productoWTOs!=null && productoWTOs.size() > 0) {
%>

	<div class="container">
	
		<% SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); %>

		<table class="table table-hover table-condensed">
		<thead>
			<tr>
				<th></th>
				<th>Nombre</th>
				<th class="text-right">Precio</th>
				<th class="text-right">Stock</th>
				<th class="text-right">Tienda</th>
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
				<td class="text-right"><%= productoWTOs.get(i).getPrecio() %></td>
				<td class="text-right"><%= productoWTOs.get(i).getStock() %></td>
				<td class="text-right"><%= productoWTOs.get(i).getTienda().getNombre() %></td>
				<td class="text-center"><%= ServletUtils.noNulls(date_format.format((productoWTOs.get(i).getFecha_puesta_venta()).getTime())) %></td>
				<td class="text-center"><%= ServletUtils.noNulls(date_format.format((productoWTOs.get(i).getFecha_retirada()).getTime())) %></td>
				<td>
					<a href="<%= uriDetalles %>" class="btn btn-primary btn-xs" role ="button">Detalles</a>
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

<div class="container text-center">
	<br>
	<a href="SearchProducto.jsp" class="btn btn-primary btn-xs" role ="button">Realizar otra búsqueda</a>	
</div>

</body>
</html>
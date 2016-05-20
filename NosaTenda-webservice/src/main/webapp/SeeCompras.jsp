<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="es.udc.jcastedo.NosaTenda.webservice.service.CompraWTO,
				java.util.List,
				java.util.ArrayList,
				java.text.SimpleDateFormat,
				es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils" %>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Ver Compras</title>
</head>

<body>
<%
	List<CompraWTO> compraWTOs = (List<CompraWTO>)request.getAttribute("compraWTOs");
%>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="compra" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Lista de Compras</h1>
</div>

<div class="container">

<%! SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yyyy"); %>

<%
if (compraWTOs!=null && compraWTOs.size() > 0) {
%>

	<table class="table table-hover table-condensed">
	<thead>
		<tr>
			<th></th>
			<th>Nombre del Producto</th>
			<th></th>
			<th class="text-center">Tienda</th>
			<th class="text-center">Fecha de la compra</th>
			<th class="text-center">Forma de Pago</th>
			<th class="text-right">Unidades</th>
			<th class="text-right">Subtotal</th>
			<th class="text-right">Total</th>
			<th class="text-center">Estado</th>
			<th></th>
		</tr>
	</thead>
	
	<% for (int i=0; i < compraWTOs.size(); i++) { %>
	
		<%
			String uriFactura = "GenerateFactura?id=" + compraWTOs.get(i).getId();
		%>
	<tbody>
		<tr>
			<th><%= i+1 %></th>
			<td><%= compraWTOs.get(i).getProducto().getNombre() %></td>
			<td>
				<a href="<%= "DetallesProducto?id=" + compraWTOs.get(i).getProducto().getId() %>" class="btn btn-primary btn-xs" role ="button">Detalles</a>
			</td>
			<td class="text-center"><%= compraWTOs.get(i).getProducto().getTienda().getNombre() %></td>
			<td class="text-center"><%= date_format.format((compraWTOs.get(i).getFecha()).getTime()) %></td>
			<td class="text-center"><%= compraWTOs.get(i).getFormaPago() %></td>
			<td class="text-right"><%= compraWTOs.get(i).getUnidades() %></td>
			<td class="text-right"><%= ServletUtils.fmt(compraWTOs.get(i).getPrecio()) %></td>
			<td class="text-right"><%= ServletUtils.fmt(compraWTOs.get(i).getTotal()) %></td>
			<td class="text-right"><%= compraWTOs.get(i).getEstadoRecogida() %></td>
			<td>
				<a href="<%= uriFactura %>" class="btn btn-primary btn-xs" role ="button" target="_blank">Factura</a>
			</td>
		</tr>
	</tbody>
	<% } %>
	
	</table>
<%
} else {
%>

	<h4>No se encontraron compras</h4>

<%
}
%>

</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="es.udc.jcastedo.NosaTenda.webservice.service.CompraWTO,
    		java.text.SimpleDateFormat,
    		es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Factura</title>
</head>

<body>

<%

	CompraWTO compra = (CompraWTO) request.getAttribute("compra");

	SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yyyy");
	
 %>

<div class="container">
	<h1 class="text-center">FACTURA</h1>
</div>

<div class="container">

	<div class="row">
		<div class="col-sm-6" style="border:solid 1px black;">
			<p><b>Tienda:</b> <%= compra.getProducto().getTienda().getNombre() %></p>
			<p><b>NIF:</b> <%= compra.getProducto().getTienda().getNif() %></p>
			<p><b>Dirección:</b> <%= compra.getProducto().getTienda().getDireccion() %></p>
			<p><b>Localidad:</b> <%= compra.getProducto().getTienda().getLocalidad().getNombre() %></p>
			<p><b>CP:</b> <%= ServletUtils.noNulls(compra.getProducto().getTienda().getCp()) %></p>
			<p><b>Provincia:</b> <%= ServletUtils.noNulls(compra.getProducto().getTienda().getProvincia()) %></p>
		</div>
		<div class="col-sm-6" style="border:solid 1px black;">
			<p><b>Correo:</b> <%= compra.getCliente().getCorreo() %></p>
			<p><b>Nombre:</b> <%= ServletUtils.noNulls(compra.getCliente().getNombre()) %> <%= ServletUtils.noNulls(compra.getCliente().getApellidos()) %></p>
			<p><b>Dirección:</b> <%= ServletUtils.noNulls(compra.getCliente().getDireccion()) %></p>
			<p><b>Localidad:</b> <%= ServletUtils.noNulls(compra.getCliente().getLocalidad()) %> <b>CP:</b> <%= ServletUtils.noNulls(compra.getCliente().getCp()) %></p>
			<p><b>NIF/CIF:</b> <%= ServletUtils.noNulls(compra.getCliente().getNif()) %></p>
			<p><b>Teléfono:</b> <%= ServletUtils.noNulls(compra.getCliente().getPhone1()) %></p>
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-sm-4" style="border:solid 1px black;">
			Nº Factura: <%= compra.getNum_factura() %>
		</div>
		<div class="col-sm-4" style="border:solid 1px black;">
			Fecha: <%= date_format.format((compra.getFecha()).getTime()) %>
		</div>
		<div class="col-sm-4" style="border:solid 1px black;">
			Forma de pago: <%= compra.getFormaPago() %>
		</div>
	</div>
	<br>
	<div class="row" style="background-color:lightgrey">
		<div class="col-sm-1" style="border:solid 1px black;">
			Cod
		</div>
		<div class="col-sm-7" style="border:solid 1px black;">
			Artículo
		</div>
		<div class="col-sm-1" style="border:solid 1px black;">
			Precio
		</div>
		<div class="col-sm-1" style="border:solid 1px black;">
			IVA
		</div>
		<div class="col-sm-1" style="border:solid 1px black;">
			Und
		</div>
		<div class="col-sm-1" style="border:solid 1px black;">
			Total
		</div>
	</div>
	<div class="row">
		<div class="col-sm-1" style="border:solid 1px black;">
			<%= compra.getProducto().getId() %>
		</div>
		<div class="col-sm-7" style="border:solid 1px black;">
			<%= compra.getProducto().getNombre() %>
		</div>
		<div class="col-sm-1" style="border:solid 1px black;">
			<%= compra.getPrecio() %>
		</div>
		<div class="col-sm-1" style="border:solid 1px black;">
			<%= compra.getTax_percentage() %>%
		</div>
		<div class="col-sm-1" style="border:solid 1px black;">
			<%= compra.getUnidades() %>
		</div>
		<div class="col-sm-1" style="border:solid 1px black;">
			<%= compra.getTotal() %>
		</div>
	</div>
	
</div>

</body>
</html>
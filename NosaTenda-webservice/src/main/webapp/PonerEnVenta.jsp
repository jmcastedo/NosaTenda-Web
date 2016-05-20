<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.Map,
    	java.util.List,
    	es.udc.jcastedo.NosaTenda.webservice.service.ProductoWTO,
    	es.udc.jcastedo.NosaTenda.webservice.service.ProductoVentasBlockWTO,
    	es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Poner en venta</title>
</head>
<body>

<% 
	ProductoWTO producto = (ProductoWTO) request.getAttribute("producto");

	String precioErrorMessage = "";
	String stockErrorMessage = "";
	String fecha_retiradaErrorMessage = "";
	
	Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
	
	if (errors != null) {
		
		String errorHeader = "<font color=\"red\"><b>";
		String errorFooter = "</b></font>";
		
		if (errors.containsKey("precio")) {
			precioErrorMessage = errorHeader + errors.get("precio") + 
				errorFooter;
		}
		
		if (errors.containsKey("stock")) {
			stockErrorMessage = errorHeader + errors.get("stock") + 
				errorFooter;
		}
		
		if (errors.containsKey("fecha_retirada")) {
			fecha_retiradaErrorMessage = errorHeader + errors.get("fecha_retirada") + 
				errorFooter;
		}
	
	}
	
	String precio = "";
	String stock = "";
	String fecha_retirada = "";
%>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="ventaproducto" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Poner en venta</h1>
</div>

<div class="container">
	<div class="row">
		<!-- descripcion + imagen -->
		<div class="col-sm-7 col-sm-offset-1">
			<!-- descripcion -->
			<div class="row"><h3><%= producto.getNombre() %></h3></div>
			<div class="row"><p class="lead"><%= producto.getTienda().getNombre() %></p></div>
		</div>
		<div class="col-sm-3 col-sm-offset-1">
			<!-- imagen -->
			<img class="img-responsive center-block" src="<%= producto.getImagen() %>" alt="Imagen del producto">
		</div>
		
	</div>
	
	<form method="POST" action="PonerEnVenta" class="form-horizontal" role="form">
	
		<input type="hidden" name="id" value="<%= producto.getId() %>">
		
		<sec:csrfInput />
		<br>
		<%-- Precio & Stock --%>
		<div class="form-group <% if (!precioErrorMessage.equals("") || !stockErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
			<h3><label class="control-label col-sm-2" for="precio">Fijar precio</label></h3>
			<div class="col-sm-3">
				<input type="number" class="form-control" id="precio" name="precio" placeholder="Introduzca el precio" value="<%= precio %>" maxlength="8" min="0" step="0.01">
				<% if (!precioErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
			</div>
			<h3><label class="control-label col-sm-2 col-sm-offset-1" for="stock">Fijar stock</label></h3>
			<div class="col-sm-3">
				<input type="number" class="form-control" id="stock" name="stock" placeholder="Introduzca el stock" value="<%= stock %>" maxlength="8" min="0">
				<% if (!stockErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
			</div>
		</div>
		
		<%-- Fecha retirada --%>
		<div class="form-group <% if (!fecha_retiradaErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
			<h3><label class="control-label col-sm-3 col-sm-offset-3" for="fecha_retirada">Fijar fecha de retirada</label></h3>
			<div class="col-sm-3">
				<input type="datetime-local" class="form-control" id="fecha_retirada" name="fecha_retirada" placeholder="Introduzca la fecha de retirada" value="<%= fecha_retirada %>">
				<% if (!fecha_retiradaErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
			</div>
			<a href="#" data-toggle="tooltip" data-placement="right" title="La fecha de retirada no puede ser más tarde de dos meses desde la puesta en venta"><span class="glyphicon glyphicon-question-sign"></span></a>
		</div>
		
		<%-- Boton puesta en venta --%>
		<div class="form-group">        
	      <div class="col-sm-offset-5 col-sm-2">
	        <!-- <input type="submit" class="btn-lg btn-primary" value="Poner en venta" data-toggle="modal" data-target="#myModal"> -->
	        <button type="button" class="btn btn-lg btn-primary" role ="button" data-toggle="modal" data-target="#myModal">Poner en venta</button>
	        
			<div class="modal fade" id="myModal" role="dialog">
			    <div class="modal-dialog">
			    
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h4 class="modal-title">Confirme la operación</h4>
			        </div>
			        <div class="modal-body">
			          <p>El producto se pondrá en venta de forma inmediata, la acción es irreversible, los datos introducidos no podrán ser modificados durante la duración de la venta.<br><br>¿Desea continuar?</p>
			        </div>
			        <div class="modal-footer">
						<input type="submit" class="btn btn-primary" value="Confirmar">
						<button type="button" class="btn btn-primary" data-dismiss="modal">Cancelar</button>
			          
			        </div>
			      </div>
			      
			    </div>
			 </div>
	      </div>
	    </div>
		
	</form>
</div>

<script>
	$(document).ready(function(){
	    $('[data-toggle="tooltip"]').tooltip();   
	});
</script>

</body>
</html>
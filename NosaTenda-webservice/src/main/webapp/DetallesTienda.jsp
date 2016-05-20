<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List,
    	es.udc.jcastedo.NosaTenda.webservice.service.TiendaWTO,
    	es.udc.jcastedo.NosaTenda.webservice.service.CategoriaWTO,
    	es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Detalles producto</title>
</head>
<body>

<% 
	TiendaWTO tienda = (TiendaWTO) request.getAttribute("tienda");

	List<CategoriaWTO> categorias = (List<CategoriaWTO>) request.getAttribute("categorias");
%>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="detailtienda" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary"><%= tienda.getNombre() %></h1>
</div>

<div class="container">
	<div class="row">
		<!-- descripcion + imagen -->
		<div class="col-sm-7 col-sm-offset-1">
			<!-- descripcion -->
			<div class="row"><h3>Dirección</h3></div>
			<div class="row"><p class="lead"><%= tienda.getDireccion() + " " + tienda.getLocalidad().getNombre() + " " + ServletUtils.noNulls(tienda.getProvincia()) + " " + ServletUtils.noNulls(tienda.getCp()) %></p></div>
			<div class="row"><p class="lead"><%= "(" + tienda.getLat() + ", " + tienda.getLon() + ")" %></p></div>
		</div>
		<div class="col-sm-3 col-sm-offset-1">
			<!-- imagen -->
			<img class="img-responsive center-block" src="<%= tienda.getImagen() %>" alt="Imagen del producto">
		</div>
		
	</div>
	
	<div class="row">
		<!-- web + correo + nif -->
		<div class="col-sm-4 col-sm-offset-1">
			<div class="row"><h3>Web</h3></div>
			<div class="row"><p class="lead"><%= ServletUtils.noNulls(tienda.getWeb()) %></p></div>
		</div>
		<div class="col-sm-3">
			<div class="row"><h3>Correo electrónico</h3></div>
			<div class="row"><p class="lead"><%= tienda.getCorreo() %></p></div>
		</div>
		<div class="col-sm-3 col-sm-offset-1">
			<div class="row"><h3>NIF</h3></div>
			<div class="row"><p class="lead"><%= tienda.getNif() %></p></div>
		</div>
	</div>
	
	<div class="row">
		<!-- tel1 + tel2 + fax -->
		<div class="col-sm-4 col-sm-offset-1">
			<div class="row"><h3>Teléfono 1</h3></div>
			<div class="row"><p class="lead"><%= tienda.getPhone1() %></p></div>
		</div>
		<div class="col-sm-3">
			<div class="row"><h3>Teléfono 2</h3></div>
			<div class="row"><p class="lead"><%= ServletUtils.noNulls(tienda.getPhone2()) %></p></div>
		</div>
		<div class="col-sm-3 col-sm-offset-1">
			<div class="row"><h3>Fax</h3></div>
			<div class="row"><p class="lead"><%= ServletUtils.noNulls(tienda.getFax()) %></p></div>
		</div>
	</div>
	
	<div class="row">
		<!-- categorias -->
		<div class="col-sm-11 col-sm-offset-1">
			<div class="row"><h3>Categorías</h3></div>
			<div class="row">
				<p class="lead">
					<% for (int i=0; i < categorias.size(); i++) { %>
						<%= categorias.get(i).getNombre() + " " %>
					<% } %>
				</p>
			</div>
		</div>
	</div>
	<sec:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_ADMIN_TIENDA')">
	<div class="row">
		<!-- boton editar -->
		<div class="col-sm-2 col-sm-offset-5">
			<a href="<%= "EditarTienda?id=" + tienda.getId() %>" class="btn btn-primary" role ="button">Editar datos de la tienda</a>
		</div>
	</div>
	</sec:authorize>
</div>

</body>
</html>
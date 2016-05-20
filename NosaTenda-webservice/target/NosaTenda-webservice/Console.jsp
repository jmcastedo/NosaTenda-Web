<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="es.udc.jcastedo.NosaTenda.webservice.service.TiendaWTO,
    java.util.List,
    java.util.ArrayList"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	
<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda</title>
</head>

<body>

<%

	List<TiendaWTO> tiendaWTOs = (List<TiendaWTO>)request.getAttribute("tiendaWTOs");

	int filas;
	int resto;
%>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="console" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Nosa Tenda</h1>
</div>

<div class="container">

	<sec:authorize access="hasAnyRole('ROLE_USUARIO,ROLE_ADMIN_TIENDA,ROLE_ADMIN')">
	
	<br>
	<br>
	
	<% 
	if (tiendaWTOs != null && tiendaWTOs.size() > 0) {
		
		filas = tiendaWTOs.size()/3;
		resto = tiendaWTOs.size()%3;
		
		int contador = 0;
	%>
	
		<div class="container">
		
		<%
		for (int i=0; i < filas; i++) {
		%>
			<div class="row">
				<% for (int j=0; j < 3; j++) { %>
					<div class="col-md-4 text-center">
				      <a href="DetallesTienda?id=<%= tiendaWTOs.get(contador).getId() %>" class="thumbnail">
				        <h4><%= tiendaWTOs.get(contador).getNombre() %></h4> 
				        <img src="<%= tiendaWTOs.get(contador).getImagen() %>" alt="<%= tiendaWTOs.get(contador).getNombre() %>" class="img-responsive">
				      </a>
				    </div>
				    <%
				    contador++;
				}
			    %>
			</div>
		<%
		}
		%>
		<% if (resto > 0) { %>
		
			<div class="row">
				<%
				for (int i=0; i < resto; i++) {
				%>
					<div class="col-md-4 text-center">
				      <a href="DetallesTienda?id=<%= tiendaWTOs.get(contador).getId() %>" class="thumbnail">
				        <h4><%= tiendaWTOs.get(contador).getNombre() %></h4> 
				        <img src="<%= tiendaWTOs.get(contador).getImagen() %>" alt="<%= tiendaWTOs.get(contador).getNombre() %>" class="img-responsive">
				      </a>
				    </div>
				    <% contador++; %>
				<%
				}
				%>
			</div>
		
		<%
		}
		%>
	
		</div>
	
	<%
	}
	%>
	
	</sec:authorize>


</div>

</body>

</html>
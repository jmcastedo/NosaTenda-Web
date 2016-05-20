<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<% 
	String sidemenu = request.getParameter("sidemenu");
	String id = request.getParameter("id");

%>

<ul class="nav nav-pills nav-stacked">
  <li><a href="DetallesTienda?id=<%= id %>">Volver a la tienda</a></li>
  <sec:authorize access="hasRole('ROLE_ADMIN')">
  <li><a href="AddLocalidad">Añadir localidad</a></li>
  <li><a href="AddCategoria">Añadir categoría</a></li>
  </sec:authorize>
</ul>
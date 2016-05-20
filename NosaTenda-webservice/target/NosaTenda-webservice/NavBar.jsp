<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<% 
	String menu = request.getParameter("menu");

%>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <!-- <a class="navbar-brand" href="//localhost:8080/NosaTenda-webservice">Nosa Tenda</a> -->
      <a class="navbar-brand" href="Index.jsp">Nosa Tenda</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <sec:authorize access="hasAnyRole('ROLE_USUARIO,ROLE_ADMIN_TIENDA,ROLE_ADMIN')">
        <li class="dropdown <% if (menu.contains("producto")) { %>active<% } %>">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Productos<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <% if (!menu.equals("allproducto")) { %>
            	<li><a href="SeeAllProductos">Ver todos los productos</a></li>
            <% } %>
            <% if (!menu.equals("productosale")) { %>
            	<li><a href="SeeProductosForSale">Ver productos en venta</a></li>
            <% } %>
            <% if (!menu.equals("addproducto")) { %>
            	<li><a href="AddProducto">Añadir producto</a></li>
            <% } %>
            <% if (!menu.equals("searchproducto")) { %>
            	<li><a href="SearchProducto.jsp">Buscar producto</a></li>
            <% } %>
          </ul>
        </li>
        <li <% if (menu.contains("reserva")) { %>class="active"<% } %>>
          <a href="SeeReservas">Reservas</a>
        </li>
        <li class="dropdown <% if (menu.contains("compra")) { %>active<% } %>">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Compras<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <% if (!menu.equals("compra")) { %>
            	<li><a href="SeeCompras">Ver compras</a></li>
            <% } %>
            <% if (!menu.equals("searchcompra")) { %>
            	<li><a href="SearchCompra">Buscar compras</a></li>
            <% } %>
            <li><a href="#">Facturas</a></li>
          </ul>
        </li>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        <li <% if (menu.contains("empleado")) { %>class="active"<% } %>>
          <a href="SeeEmpleados">Empleados</a>
        </li>
        <li class="dropdown <% if (menu.contains("tienda")) { %>active<% } %>">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Tiendas<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <% if (!menu.equals("alltienda")) { %>
            	<li><a href="SeeTiendas">Ver todas las tiendas</a></li>
            <% } %>
            <% if (!menu.equals("addtienda")) { %>
            	<li><a href="AddTienda">Añadir tienda</a></li>
            <% } %>
          </ul>
        </li>
        </sec:authorize>
        
      </ul>
      <ul class="nav navbar-nav navbar-right">
      	<% if (request.getUserPrincipal() == null) { %>
        	<li><a href="CrearCuenta"><span class="glyphicon glyphicon-user"></span> Crear cuenta</a></li>
        	<li <% if (menu.contains("login")) { %>class="active"<% } %>><a href="Login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        <% } else { %>
        	<li <% if (menu.contains("cuenta")) { %>class="active"<% } %>><a href="SeeCuenta"><span class="glyphicon glyphicon-user"></span> <%= request.getUserPrincipal().getName() %></a></li>
        	<li>
        		<form class="navbar-form" method="POST" action="logout">
					<sec:csrfInput />
					<button type="submit" class="btn btn-link"><span class="glyphicon glyphicon-log-out"></span> Logout</button>
				</form>
        	</li>
        <% } %>
      </ul>
    </div>
  </div>
</nav>
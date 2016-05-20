<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="es.udc.jcastedo.NosaTenda.webservice.service.EmpleadoWTO,
				es.udc.jcastedo.NosaTenda.webservice.service.RoleWTO,
				es.udc.jcastedo.NosaTenda.webservice.service.TiendaWTO,
				java.util.List,
				java.util.ArrayList" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	
<jsp:include page="HeadConfig.jsp" />
    
	<title>Nosa Tenda - Ver Empleados</title>
</head>
<body>
<%
	List<EmpleadoWTO> empleadoWTOs = (List<EmpleadoWTO>)request.getAttribute("empleadoWTOs");

	List<RoleWTO> roleWTOs = (List<RoleWTO>)request.getAttribute("roleWTOs");
%>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="empleado" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Lista de Empleados</h1>
</div>

<%
if (empleadoWTOs!=null && empleadoWTOs.size() > 0) {
%>

	<div class="container">
	
		<form method="POST" action="EditarEmpleadosPermisos" class="form-horizontal" role="form">

			<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th></th>
					<th class="text-center">Correo</th>
					<th class="text-center">NIF</th>
					<th class="text-center">Tiendas</th>
					<th class="text-center">Permisos</th>
 					<th></th>
					<th class="text-center">Activado</th>
					<th></th>
					<th></th>
	
				</tr>
			</thead>
			
			<% for (int i=0; i < empleadoWTOs.size(); i++) { %>
			
				<%
					//String uriBorrar = "BorrarProducto.jsp?id=" + empleadoWTOs.get(i).getId();
					String uriEditar = "EditarProducto?id=" + empleadoWTOs.get(i).getId();
				%>
			<tbody>
				<tr>
					<th><%= i+1 %></th>
					<td><%= empleadoWTOs.get(i).getCorreo() %></td>
					<td class="text-center"><%= empleadoWTOs.get(i).getNif() %></td>
					<td class="text-center">
						<% if (!empleadoWTOs.get(i).getRole().equals("ROLE_ADMIN")) { %>
						<div class="dropdown">
							<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Acceso a:
							    <span class="caret"></span></button>
							    <ul class="dropdown-menu">
							    	<% for (TiendaWTO tienda: empleadoWTOs.get(i).getTiendas()) { %>
							    		<li><a href="#"><%= tienda.getNombre() %></a></li>
							    	<% } %>
							    </ul>
						</div>
						<% } %>
					</td>
					<form role="form" method="POST" action="EditarRole">
						<input type="hidden" name="empleadoId" value="<%= empleadoWTOs.get(i).getId() %>">
						<sec:csrfInput />
					<td>
							<select class="form-control input-sm" id="role" name="role">
								<% for (RoleWTO role: roleWTOs) { %>
									<% if (empleadoWTOs.get(i).getRole().equals(role.getNombre())) { %>
										<option value="<%= role.getNombre() %>" selected>
											<%= role.getNombre() %>
										</option>
									<% } else { %>
										<option value="<%= role.getNombre() %>">
											<%= role.getNombre() %>
										</option>
									<% } %>
								<% } %>
							</select>
					</td>
					<td class="text-center"><input type="submit" class="btn-xs btn-primary" value="Guardar"></td>
					</form>
					<td class="text-center">
						<% if (!empleadoWTOs.get(i).getActivado()) { %>
							<span class="glyphicon glyphicon-remove text-danger"></span>
						<% } else { %>
							<span class="glyphicon glyphicon-ok text-success"></span>
						<% } %>
					</td>
					<td class="text-center">
						<% if (!empleadoWTOs.get(i).getRole().equals("ROLE_ADMIN")) { %>			
						
						<% if (!empleadoWTOs.get(i).getActivado()) { %>
							<!-- <a href="ActivarEmpleado?id=<%= empleadoWTOs.get(i).getId() %>&op=activar" class="btn-sm btn-primary" role="button">Activar Cuenta</a> -->
							<form method="POST" action="ActivarEmpleado?id=<%= empleadoWTOs.get(i).getId() %>&op=activar">
								<sec:csrfInput />
								<input type="submit" class="btn-xs btn-primary" value="Activar Cuenta">
							</form>
						<% } else { %>
							<!-- <a href="ActivarEmpleado?id=<%= empleadoWTOs.get(i).getId() %>&op=desactivar" class="btn-sm btn-primary" role="button">Desactivar Cuenta</a> -->
							<form method="POST" action="ActivarEmpleado?id=<%= empleadoWTOs.get(i).getId() %>&op=desactivar">
								<sec:csrfInput />
								<input type="submit" class="btn-xs btn-primary" value="Desactivar Cuenta">
							</form>
						<% } %>
						
						<% } %>
					</td>
					<td class="text-center">
						<% if (!empleadoWTOs.get(i).getRole().equals("ROLE_ADMIN")) { %>
						
						<!-- <a href="BorrarEmpleado?id=<%= empleadoWTOs.get(i).getId() %>" class="btn-sm btn-primary btn-danger" role="button">Borrar Empleado</a> -->
						<button type="button" class="btn btn-xs btn-primary btn-danger" role ="button" data-toggle="modal" data-target="#myModal<%= i+1 %>">Borrar</button>
					
						<div class="modal fade" id="myModal<%= i+1 %>" role="dialog">
						    <div class="modal-dialog">
						    
						      <!-- Modal content-->
						      <div class="modal-content">
						        <div class="modal-header">
						          <button type="button" class="close" data-dismiss="modal">&times;</button>
						          <h4 class="modal-title">Está a punto de borrar un empleado <span class="glyphicon glyphicon-exclamation-sign text-danger"></h4>
						        </div>
						        <div class="modal-body">
						          <p>¿Está seguro?</p>
						        </div>
						        <div class="modal-footer">
						          <form method="POST" action="BorrarEmpleado?id=<%= empleadoWTOs.get(i).getId() %>">
						            <sec:csrfInput />
									<input type="submit" class="btn btn-primary" value="Confirmar">
									<button type="button" class="btn btn-primary" data-dismiss="modal">Cancelar</button>
								  </form>	
						          
						        </div>
						      </div>
						      
						    </div>
						 </div>
						 
						 <% } %>
					</td>
	
					
				</tr>
			</tbody>
			
			
			<% } %>
			
			</table>
			
		</form>
	
	</div>
<%
} else {
%>

	<div class="container text-center">
		<br>
		<h4>No se encontraron empleados</h4>
	</div>

<%
}
%>

</body>
</html>
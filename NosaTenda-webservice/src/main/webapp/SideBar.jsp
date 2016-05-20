<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<% 
	String sidemenu = request.getParameter("sidemenu");

%>

<ul class="nav nav-pills nav-stacked">
  <li><a href="SeeCuenta">Perfil</a></li>
  <li><a href="SeeCuentaEdit">Editar perfil</a></li>
  <li><a href="EditarCorreo">Cambiar correo</a></li>
  <li><a href="EditarPassword">Cambiar contraseña</a></li>
</ul>
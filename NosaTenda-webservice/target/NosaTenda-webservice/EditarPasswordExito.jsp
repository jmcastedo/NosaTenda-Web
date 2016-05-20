<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Cuenta</title>
</head>

<body>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="editcuentapasswordexito" />
</jsp:include>

<div class="container text-center">
	<br>
	<h3 class="text-primary">Contraseña cambiada con éxito</h3>
	<br>
	<a href="SeeCuenta" class="btn btn-primary btn-xs" role ="button">Volver al perfil</a>
</div>

</body>
</html>
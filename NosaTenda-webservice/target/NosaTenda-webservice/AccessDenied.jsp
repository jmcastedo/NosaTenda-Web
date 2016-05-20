<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	
<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Acceso Denegado</title>
</head>
<body>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="denied" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Acceso denegado</h1>
</div>

<div class="container text-center">

	<br>
	<h3 class="text-primary">No tiene suficientes permisos (error 403)</h3>
	<br>

</div>

</body>
</html>
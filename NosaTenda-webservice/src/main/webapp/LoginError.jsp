<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	
<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Login Incorrecto</title>
</head>
<body>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="login" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Login incorrecto</h1>
</div>

<div class="container text-center">

	<br>
	
	<br>
	<a href="Login.jsp" class="btn btn-primary" role ="button">Volver a intentarlo</a>	
</div>

</body>
</html>
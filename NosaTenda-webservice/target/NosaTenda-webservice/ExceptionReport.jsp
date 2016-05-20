<%@page import="org.springframework.http.HttpStatus"%>
<%@ page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.io.*"%>
<!DOCTYPE html>
<html>
<head>
	
<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Error inesperado</title>
</head>
<body>

<div class="container">
	<h1 class="text-left text-danger">Ha ocurrido un error en la aplicación</h1>
</div>

<!-- TODO buscar manera de ocultar esto en produccion -->
<div class="container">

	<br>
	<h3 class="text-info">
		<%= exception.getClass().toString() %>
	</h3>
	<br>
	<h4 class="text-info">
		<%= exception.getMessage() %>
		
	</h4>
	<br>
	<h5 class="text-info">
		<% 
			exception.printStackTrace(new PrintWriter(out));
		%>
	</h5>
	

</div>

</body>
</html>
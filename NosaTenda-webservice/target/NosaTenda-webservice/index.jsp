<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	
<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda</title>
</head>

<body>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="index" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Nosa Tenda</h1>
</div>

<div class="container">

	<sec:authorize access="hasAnyRole('ROLE_USUARIO,ROLE_ADMIN_TIENDA,ROLE_ADMIN')">
	
	<% response.sendRedirect("Console"); %>
	
	
	</sec:authorize>

	<!-- 
	<form method="GET" action="BootStrapExample.jsp">
		<input type="submit" value="BootStrapExample">
	</form>
	
	<form method="GET" action="BootStrapTutorial.jsp">
		<input type="submit" value="BootStrapTutorial">
	</form>
	
	<form method="GET" action="GoogleChartsTutorial.jsp">
		<input type="submit" value="GoogleChartsTutorial">
	</form>
	
	<form method="GET" action="GoogleComboChartsTutorial.jsp">
		<input type="submit" value="GoogleComboChartsTutorial">
	</form>
	 -->
</div>

</body>

</html>
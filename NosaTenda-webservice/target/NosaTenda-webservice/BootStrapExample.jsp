<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    
    <!-- Bootstrap -->
    <link rel="stylesheet" href="webjars/bootstrap/3.3.4/css/bootstrap.min.css">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="webjars/jquery/2.1.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="webjars/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <title>Bootstrap 101 Template</title>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <h1>Hello, world!</h1>


<div class="container-fluid">
  <h1>My First Bootstrap Page</h1>      
  <p>This part is inside a .container-fluid class.</p> 
  <p>The .container class provides a full width container, spanning the entire width of the viewport.</p>
  
  <sec:authorize access="hasRole('ROLE_USER')">
    This text is only visible to a user
    <br/>
  </sec:authorize>
  <sec:authorize access="hasRole('ROLE_SUPERVISOR')">
    This text is only visible to an admin
    <br/>
  </sec:authorize>
  	<sec:authorize url='/secure/index.jsp'>
	<p>
	You can currently access "/secure" URLs.
	</p>
	</sec:authorize>
	<sec:authorize url='/secure/extreme/index.jsp'>
	<p>
	You can currently access "/secure/extreme" URLs.
	</p>
	</sec:authorize>
	<p>
	Your principal object is....: <%= request.getUserPrincipal() %>
	</p>
	<p>	</p>
	<% if (request.getUserPrincipal() != null) { %>
		<form method="POST" action="logout">
			<sec:csrfInput />
			<input type="submit" value="Log out">
		</form>
<%-- 		<a href="<c:url value="j_spring_security_logout" />" > Logout1</a> --%>
   		<a href="logout"><span class="glyphicon glyphicon-log-out"></span> Logout2</a>
   		<form method="POST" action="logout">
			<sec:csrfInput />
			<span class="glyphicon glyphicon-log-out"></span><input type="submit" class="btn btn-link" value="Logout3">
		</form>
	<% } %>

</div>


</body>
</html>
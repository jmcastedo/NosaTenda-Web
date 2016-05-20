<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	
<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Login</title>
</head>
<body>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="login" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Login</h1>
</div>

<div class="container">

	<form method="POST" action="/NosaTenda-webservice/login" class="form-horizontal" role="form">
	
		<sec:csrfInput />
		<%-- User --%>
		<div class="form-group">
			<label class="control-label col-sm-2" for="username">Nombre:</label>
			<div class="col-sm-8">
		        <input type="text" class="form-control" id="username" name="username" placeholder="Introduzca un nombre" value="" maxlength="100">
		    </div>
		</div>
		<%-- Password --%>
		<div class="form-group">
			<label class="control-label col-sm-2" for="password">Contraseña:</label>
			<div class="col-sm-8">
		        <input type="password" class="form-control" id="password" name="password" placeholder="Introduzca la contraseña" value="" maxlength="100">
		    </div>
		</div>
		<%-- Remember me --%>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-8">
		        <div class="checkbox">
          			<label><input type="checkbox"> Recuérdame en este ordenador</label>
        		</div>
		    </div>
		</div>
		<%-- Create button --%>
		<div class="form-group">        
	      <div class="col-sm-offset-2 col-sm-10">
	        <input type="submit" class="btn btn-primary" value="Login">
	      </div>
	    </div>

	</form>

</div>

</body>
</html>
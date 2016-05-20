<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.Map,
    	es.udc.jcastedo.NosaTenda.webservice.service.EmpleadoWTO,
    	es.udc.jcastedo.NosaTenda.webservice.service.EmpleadoInfoWTO" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Cuenta</title>
</head>
<body>

<%
	EmpleadoWTO empleado = (EmpleadoWTO) request.getAttribute("empleado");
	EmpleadoInfoWTO empleadoInfo = (EmpleadoInfoWTO) request.getAttribute("empleadoInfo");

	Boolean edit = (Boolean) request.getAttribute("edit");
	
	String nombre = "";
	String apellidos = "";
	String direccion = "";
	String localidad = "";
	String cp = "";
	String provincia = "";
	String phone1 = "";
	String phone2 = "";
	
	if (empleadoInfo != null) {
		nombre = empleadoInfo.getNombre();
		apellidos = empleadoInfo.getApellidos();
		direccion = empleadoInfo.getDireccion();
		localidad = empleadoInfo.getLocalidad();
		cp = empleadoInfo.getCp();
		provincia = empleadoInfo.getProvincia();
		phone1 = empleadoInfo.getPhone1();
		phone2 = empleadoInfo.getPhone2();
	}
	
	Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
	
	String passwordErrorMessage = "";
	
	if (errors != null) {
		
		String errorHeader = "<font color=\"red\"><b>";
		String errorFooter = "</b></font>";
		
		if (errors.containsKey("password")) {
			passwordErrorMessage = errorHeader + errors.get("password") + 
				errorFooter;
		}

	}
	
	
%>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="cuenta" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Cuenta</h1>
</div>

<div class="container">

	<div class="col-sm-2">
      <jsp:include page="SideBar.jsp" >
		  <jsp:param name="sidemenu" value="perfil" />
		</jsp:include>
    </div>
	
</div>

</body>
</html>
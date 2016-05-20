<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.Map,
    	java.util.List,
    	es.udc.jcastedo.NosaTenda.webservice.service.TiendaWTO,
    	es.udc.jcastedo.NosaTenda.webservice.service.LocalidadWTO,
    	es.udc.jcastedo.NosaTenda.webservice.service.CategoriaWTO" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Editar Tienda</title>
</head>
<body>

<%

	Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");

	String nombreErrorMessage = "";
	String nifErrorMessage = "";
	String correoErrorMessage = "";
	String urlErrorMessage = "";
	String direccionErrorMessage = "";
	String phone1ErrorMessage = "";
	String latErrorMessage = "";
	String lonErrorMessage = "";
	
	if (errors != null) {
		
		if (errors.containsKey("nombre")) {
			nombreErrorMessage = "error";
		}
		if (errors.containsKey("nif")) {
			nifErrorMessage = "error";
		}
		if (errors.containsKey("correo")) {
			correoErrorMessage = "error";
		}
		if (errors.containsKey("imagen")) {
			urlErrorMessage = "error";
		}
		if (errors.containsKey("direccion")) {
			direccionErrorMessage = "error";
		}
		if (errors.containsKey("phone1")) {
			phone1ErrorMessage = "error";
		}
		if (errors.containsKey("lat")) {
			latErrorMessage = "error";
		}
		if (errors.containsKey("lon")) {
			lonErrorMessage = "error";
		}
	}

	TiendaWTO tienda = (TiendaWTO) request.getAttribute("tienda");

	String id = request.getParameter("id");
	if (id==null) {
		if (tienda != null) {
			id = Long.toString(tienda.getId());
		} else {
			id="";
		}
	}
	
	String nombre = request.getParameter("nombre");
	if (nombre==null) {
		if (tienda != null) {
			nombre = tienda.getNombre();
		} else {
			nombre="";
		}
	}
	
	String nif = request.getParameter("nif");
	if (nif==null) {
		if (tienda != null) {
			nif = tienda.getNif();
		} else {
			nif="";
		}
	}
	
	String correo = request.getParameter("correo");
	if (correo==null) {
		if (tienda != null) {
			correo = tienda.getCorreo();
		} else {
			correo="";
		}
	}
	
	String web = request.getParameter("web");
	if (web==null) {
		if (tienda != null) {
			web = tienda.getWeb() == null ? "" : tienda.getWeb();
		} else {
			web="";
		}
	}
	
	String imagen = request.getParameter("imagen");
	if (imagen==null) {
		if (tienda != null) {
			imagen = tienda.getImagen();
		} else {
			imagen="";
		}
	}
	
	String direccion = request.getParameter("direccion");
	if (direccion==null) {
		if (tienda != null) {
			direccion = tienda.getDireccion();
		} else {
			direccion="";
		}
	}
	
	String localidadId = request.getParameter("localidadId");
	if (localidadId==null) {
		if (tienda != null) {
			localidadId = Long.toString(tienda.getLocalidad().getId());
		} else {
			localidadId="-1";
		}
	}
	
	String cp = request.getParameter("cp");
	if (cp==null) {
		if (tienda != null) {
			cp = tienda.getCp() == null ? "" : tienda.getCp();
		} else {
			cp="";
		}
	}
	
	String provincia = request.getParameter("provincia");
	if (provincia==null) {
		if (tienda != null) {
			provincia = tienda.getProvincia() == null ? "" : tienda.getProvincia();
		} else {
			provincia="";
		}
	}
	
	String lat = request.getParameter("lat");
	if (lat==null) {
		if (tienda != null) {
			lat = Double.toString(tienda.getLat());
		} else {
			lat="";
		}
	}
	
	String lon = request.getParameter("lon");
	if (lon==null) {
		if (tienda != null) {
			lon = Double.toString(tienda.getLon());
		} else {
			lon="";
		}
	}
	
	String phone1 = request.getParameter("phone1");
	if (phone1==null) {
		if (tienda != null) {
			phone1 = tienda.getPhone1();
		} else {
			phone1="";
		}
	}
	
	String phone2 = request.getParameter("phone2");
	if (phone2==null) {
		if (tienda != null) {
			phone2 = tienda.getPhone2() == null ? "" : tienda.getPhone2();
		} else {
			phone2="";
		}
	}
	
	String fax = request.getParameter("fax");
	if (fax==null) {
		if (tienda != null) {
			fax = tienda.getFax() == null ? "" : tienda.getFax();
		} else {
			fax="";
		}
	}
	
	List<CategoriaWTO> categoriasTienda = (List<CategoriaWTO>) request.getAttribute("categoriasTienda");
	
	List<CategoriaWTO> categorias = (List<CategoriaWTO>) request.getAttribute("categorias");
	
	List<LocalidadWTO> localidades = (List<LocalidadWTO>) request.getAttribute("localidades");
	
%>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="editartienda" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Editar Tienda</h1>
</div>

<div class="container">

	<div class="col-sm-2">
		<jsp:include page="SideBar_Tienda.jsp" >
		  <jsp:param name="sidemenu" value="perfil" />
		  <jsp:param name="id" value="<%= id %>" />
		</jsp:include>
	</div>
	
	<div class="col-sm-10">

	<form method="POST" action="EditarTienda" class="form-horizontal" role="form">
	
		<sec:csrfInput />
		
			<input type="hidden" name="id" value="<%= id %>">
		
			<%-- Nombre & Nif --%>
			<div class="form-group">
				<label class="control-label col-sm-2" for="nombre">Nombre:</label>
				<div class="col-sm-3 <% if (!nombreErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
			        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Introduzca un nombre" value="<%= nombre %>" maxlength="100">
			        <% if (!nombreErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
			    </div>
			    <label class="control-label col-sm-2" for="nif">NIF:</label>
				<div class="col-sm-3 <% if (!nifErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
			        <input type="text" class="form-control" id="nif" name="nif" placeholder="Introduzca un NIF" value="<%= nif %>" maxlength="16">
			        <% if (!nifErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
			    </div>
			</div>
			<%-- Correo --%>
			<div class="form-group">
				<label class="control-label col-sm-2" for="correo">Correo:</label>
				<div class="col-sm-8 <% if (!correoErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
			        <input type="email" class="form-control" id="correo" name="correo" placeholder="Introduzca un correo" value="<%= correo %>" maxlength="100">
			        <% if (!correoErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
			    </div>
			</div>
			<%-- Web --%>
			<div class="form-group">
			    <label class="control-label col-sm-2" for="web">Web:</label>
				<div class="col-sm-8">
			        <input type="url" class="form-control" id="web" name="web" placeholder="Introduzca una URL" value="<%= web %>" maxlength="200" pattern="https?://.+">
			    </div>
			</div>
			<%-- Imagen --%>
			<div class="form-group">
				<label class="control-label col-sm-2" for="imagen">URL de la imagen:</label>
				<div class="col-sm-8 <% if (!urlErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
			        <input type="url" class="form-control" id="imagen" name="imagen" placeholder="Introduzca una URL con la imagen de la tienda" value="<%= imagen %>" maxlength="450" pattern="https?://.+">
			        <% if (!urlErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
			    </div>
			</div>
			<%-- Direccion & Localidad --%>
			<div class="form-group">
				<label class="control-label col-sm-2" for="direccion">Dirección:</label>
				<div class="col-sm-3 <% if (!direccionErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
			        <input type="text" class="form-control" id="direccion" name="direccion" placeholder="Introduzca una direccion" value="<%= direccion %>" maxlength="200">
			        <% if (!direccionErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
			    </div>
			    <label class="control-label col-sm-2" for="localidadId">Localidad:</label>
				<div class="col-sm-3">
					<select class="form-control" id="localidadId" name="localidadId">
						<% for (int i=0; i < localidades.size(); i++) { %>
							<% if (tienda != null) { %>
								<% if (tienda.getLocalidad().getId() == localidades.get(i).getId()) { %>
									<option value="<%= localidades.get(i).getId() %>" selected>
									<%= localidades.get(i).getNombre() %></option>
								<% } else { %>
									<option value="<%= localidades.get(i).getId() %>">
									<%= localidades.get(i).getNombre() %></option>
								<% } %>
							<% } else { %>
								<% if (Long.valueOf(localidadId) == localidades.get(i).getId()) { %>
									<option value="<%= localidades.get(i).getId() %>" selected>
										<%= localidades.get(i).getNombre() %></option>
								<% } else { %>
									<option value="<%= localidades.get(i).getId() %>">
										<%= localidades.get(i).getNombre() %></option>
								<% } %>
							<% } %>
						<% } %> 
					</select>
			    </div>
			</div>
			<%-- CP & Provincia --%>
			<div class="form-group">
				<label class="control-label col-sm-2" for="cp">Código Postal:</label>
				<div class="col-sm-3">
			        <input type="text" class="form-control" id="cp" name="cp" placeholder="Introduzca un código postal" value="<%= cp %>" maxlength="16">
			    </div>
			    <label class="control-label col-sm-2" for="provincia">Provincia:</label>
				<div class="col-sm-3">
			        <input type="text" class="form-control" id="provincia" name="provincia" placeholder="Introduzca una provincia" value="<%= provincia %>" maxlength="45">
			    </div>
			</div>
			<%-- Lat & Lon --%>
			<div class="form-group">
				<label class="control-label col-sm-2" for="lat">Latitud:</label>
				<div class="col-sm-3 <% if (!latErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
			        <input type="number" class="form-control" id="lat" name="lat" placeholder="Introduzca una latitud" value="<%= lat %>" step="0.000001">
			        <% if (!latErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
			    </div>
			    <label class="control-label col-sm-2" for="lon">Longitud:</label>
				<div class="col-sm-3 <% if (!lonErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
			        <input type="number" class="form-control" id="lon" name="lon" placeholder="Introduzca una longitud" value="<%= lon %>" step="0.000001">
			        <% if (!lonErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
			    </div>
			</div>
			<%-- Phone1 & Phone2 --%>
			<div class="form-group">
				<label class="control-label col-sm-2" for="phone1">Teléfono 1:</label>
				<div class="col-sm-3 <% if (!phone1ErrorMessage.equals("")) { %>has-error has-feedback<% } %>">
			        <input type="text" class="form-control" id="phone1" name="phone1" placeholder="Introduzca un teléfono" value="<%= phone1 %>" maxlength="32">
			        <% if (!phone1ErrorMessage.equals("")) { %><span class="glyphicon glyphicon-remove form-control-feedback"></span><% } %>
			    </div>
			    <label class="control-label col-sm-2" for="phone2">Teléfono 2:</label>
				<div class="col-sm-3">
			        <input type="text" class="form-control" id="phone2" name="phone2" placeholder="Introduzca otro teléfono" value="<%= phone2 %>" maxlength="32">
			    </div>
			</div>
			<%-- Fax --%>
			<div class="form-group">
				<label class="control-label col-sm-2" for="fax">Fax:</label>
				<div class="col-sm-3">
			        <input type="text" class="form-control" id="fax" name="fax" placeholder="Introduzca un fax" value="<%= fax %>" maxlength="32">
			    </div>
			</div>
			<%-- Categorias --%>
			<div class="form-group">
			    <label class="control-label col-sm-2" for="categorias">Categorías:</label>
			    <div class="col-sm-3">
					<select multiple class="form-control" id="categorias" name="categorias_group">
						<% for (int i=0; i < categorias.size(); i++) { %>
								<% if (categoriasTienda.contains(categorias.get(i))) { %>
									<option value="<%= categorias.get(i).getId() %>" selected>
									<%= categorias.get(i).getNombre() %></option>
								<% } else { %>
									<option value="<%= categorias.get(i).getId() %>">
									<%= categorias.get(i).getNombre() %></option>
								<% } %>
						<% } %> 
					</select>
				</div>
			</div>
			<br>
			<%-- Edit button --%>
			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
			  	<input type="submit" class="btn btn-primary" value="Confirmar cambios">
			  	<a href="DetallesTienda?id=<%= id %>" class="btn btn-primary" role="button">Cancelar</a>
		      </div>
		    </div>
		
			
	
	</form>
	
	</div>
	
</div>

</body>
</html>
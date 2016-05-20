<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.Map,
    	java.util.List,
    	es.udc.jcastedo.NosaTenda.webservice.service.TiendaWTO" %>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Buscar Compras por Tienda</title>
</head>
<body>

<%-- Get errors. --%>

<%
	String tiendaId = request.getParameter("tiendaId");

	if (tiendaId==null) {
		tiendaId="-1";
	}
	
	List<TiendaWTO> tiendas = (List<TiendaWTO>) request.getAttribute("tiendas");
%>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="searchcompra" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Buscar Compras por Tienda</h1>
</div>

<div class="container">

	<form method="GET" action="SearchCompraResultados" class="form-horizontal" role="form">
	
		<%-- Tienda --%>
		<div class="form-group">
			<label class="control-label col-sm-2" for="tiendaId">Escoja una tienda:</label>
			<div class="col-sm-8">
	      		<select class="form-control" id="tiendaId" name="tiendaId">
	      			<% for (int i=0; i < tiendas.size(); i++) { %>
					<% if (Long.valueOf(tiendaId) == tiendas.get(i).getId()) { %>
							<option value="<%= tiendas.get(i).getId() %>" selected>
								<%= tiendas.get(i).getNombre() %></option>
					<% } else { %>
							<option value="<%= tiendas.get(i).getId() %>">
								<%= tiendas.get(i).getNombre() %></option>
					<% } %>
				<% } %>
			    </select>
			</div>
		</div>
		<%-- Search button --%>
		<div class="form-group">        
	      <div class="col-sm-offset-2 col-sm-10">
	        <input type="submit" class="btn btn-primary" value="Buscar">
	      </div>
	    </div>
	
	</form>
	
</div>


</body>
</html>
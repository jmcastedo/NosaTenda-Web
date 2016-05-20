<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List,
    	es.udc.jcastedo.NosaTenda.webservice.service.ProductoWTO,
    	es.udc.jcastedo.NosaTenda.webservice.service.ProductoVentasBlockWTO,
    	es.udc.jcastedo.NosaTenda.webservice.service.utils.ServletUtils,
    	java.util.Calendar,java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Detalles producto</title>
</head>
<body>

<% 
	ProductoWTO producto = (ProductoWTO) request.getAttribute("producto");

	List<ProductoVentasBlockWTO> productoVentasBlockWTOs = (List<ProductoVentasBlockWTO>) request.getAttribute("productoVentasBlockWTOs");
	
	SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yyyy");
%>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="detailproducto" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary"><%= producto.getNombre() %></h1>
</div>

<div class="container">
	<div class="row">
		<!-- descripcion + imagen -->
		<div class="col-sm-7 col-sm-offset-1">
			<!-- descripcion -->
			<div class="row"><h3>Descripción</h3></div>
			<div class="row"><p class="lead"><%= producto.getDescripcion() %></p></div>
			<div class="row"><a href="<%= "EditarProducto?id=" + producto.getId() %>" class="btn btn-primary" role ="button">Editar</a></div>
		</div>
		<div class="col-sm-3 col-sm-offset-1">
			<!-- imagen -->
			<img class="img-responsive center-block" src="<%= producto.getImagen() %>" alt="Imagen del producto">
		</div>
		
	</div>
	
	<div class="row">
		<!-- precio + stock + tienda -->
		<div class="col-sm-3 col-sm-offset-1">
			<div class="row"><h3>En venta</h3></div>
			<% 	Calendar compare = Calendar.getInstance();
			
			if ( producto.getFecha_retirada() == null) { %>
				<div class="row"><p class="lead">No</p></div>
				<div class="row"><a href="<%= "PonerEnVenta?id=" + producto.getId() %>" class="btn btn-primary" role ="button">Poner en venta</a></div>
				
			<% } else if ((compare.compareTo(producto.getFecha_retirada()) < 0 ) && (compare.compareTo(producto.getFecha_puesta_venta()) > 0)) { %>
				<div class="row"><p class="lead">Sí (hasta el <%= date_format.format((producto.getFecha_retirada()).getTime()) %>)</p></div>
				<div class="row"><a href="<%= "PonerEnVenta?id=" + producto.getId() %>" class="btn btn-primary disabled" role ="button">Poner en venta</a></div>
				
			<% } else { %>
				<div class="row"><p class="lead">No</p></div>
				<div class="row"><a href="<%= "PonerEnVenta?id=" + producto.getId() %>" class="btn btn-primary" role ="button">Poner en venta</a></div>
				
			<% } %>
			
			
		</div>
		<div class="col-sm-2">
			<div class="row"><h3>Precio actual</h3></div>
			<div class="row"><p class="lead"><%= ServletUtils.fmt(producto.getPrecio()) %></p></div>
			<!-- <div class="row"><a href="<%= "ModificarPrecio?id=" + producto.getId() %>" class="btn btn-primary" role ="button">Modificar precio</a></div> -->
		</div>
		<div class="col-sm-2 col-sm-offset-1">
			<div class="row"><h3>Stock actual</h3></div>
			<div class="row"><p class="lead"><%= producto.getStock() %></p></div>
			<!-- <div class="row"><a href="<%= "ModificarStock?id=" + producto.getId() %>" class="btn btn-primary" role ="button">Modificar stock</a></div> -->
		</div>
		<div class="col-sm-2 col-sm-offset-1">
			<div class="row"><h3>Tienda</h3></div>
			<div class="row"><p class="lead"><%= producto.getTienda().getNombre() %></p></div>
		</div>
	</div>
	
	<div class="row">
		<br>
		<!-- gráficas -->
		<div class="col-sm-6" id="chart_div" style="width:600; height:300"></div>
		<div class="col-sm-6" id="chart_div2" style="width:600; height:300"></div>
	</div>
</div>


	<!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);
      
      // Callback that creates and populates a data table,
      // instantiates the bar chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Fechas');
        data.addColumn('number', 'Stock inicial');
        data.addColumn('number', 'Unidades vendidas');
        
        var ventaArray = [<% for (int i=0; i< productoVentasBlockWTOs.size(); i++) { %>
        					<%= productoVentasBlockWTOs.get(i).getVentas() %><%= i + 1 < productoVentasBlockWTOs.size() ? ",":"" %>
        				<% } %>];
        
        var stockArray = [<% for (int i=0; i< productoVentasBlockWTOs.size(); i++) { %>
							<%= productoVentasBlockWTOs.get(i).getStock_inicial() %><%= i + 1 < productoVentasBlockWTOs.size() ? ",":"" %>
						<% } %>];
        
        var tituloArray = [<% for (int i=0; i< productoVentasBlockWTOs.size(); i++) { %>
							<%= productoVentasBlockWTOs.get(i).intervalo() %><%= i + 1 < productoVentasBlockWTOs.size() ? ",":"" %>
						<% } %>];
        
        var precioArray = [<% for (int i=0; i< productoVentasBlockWTOs.size(); i++) { %>
							<%= productoVentasBlockWTOs.get(i).getPrecio() %><%= i + 1 < productoVentasBlockWTOs.size() ? ",":"" %>
						<% } %>];
        
        var size = ${productoVentasBlockWTOs.size()};
        var population;
        if (size >= 5) {
        	population = 5;
        } else {
        	population = size;
        }
        if (size != 0) {
        	var i;
            for (i=size-population; i<size; i++) {
            	
            	data.addRow([tituloArray[i],  stockArray[i], ventaArray[i]]);
            	
            }
        }

        // Set chart options
        var options = {'title': 'Unidades vendidas y stock por fecha',
		        		'legend':'top',
						'seriesType':'bars',
						'series':{1: {type: 'line'}},
						'vAxis':{format: ''},
                       'width':600,
                       'height':300};
        
        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
        chart.draw(data, options);
        
        // chart 2
        var data2 = new google.visualization.DataTable();
        data2.addColumn('number', 'Precio');
        data2.addColumn('number', 'Unidades');
        
     	if (size != 0) {
        	var i;
            for (i=size-population; i<size; i++) {
            	
            	data2.addRow([precioArray[i], ventaArray[i]]);
            	
            }
        }
        
        var options2 = {'title': 'Unidades vendidas según el precio',
        		'legend':'none',
				'hAxis':{title:'Precio', format: ''},
				'vAxis':{title:'Unidades', format:''},
               'width':600,
               'height':300};
        
     	// Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div2'));
        chart.draw(data2, options2);
      }
    </script>

</body>
</html>
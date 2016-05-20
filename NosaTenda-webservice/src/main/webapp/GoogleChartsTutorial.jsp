<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.Map,
		java.util.List,
		es.udc.jcastedo.NosaTenda.webservice.service.TiendaWTO,
		es.udc.jcastedo.NosaTenda.webservice.service.ProductoWTO" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="HeadConfig.jsp" />

	<title>Nosa Tenda - Google Charts Tutorial</title>
	
	<!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Topping');
        data.addColumn('number', 'Slices');
        data.addRows([
          ['10-03 to 15-03', 3],
          ['16-03 to 21-03', 1],
          ['13-04 to 20-04', 1],
          ['01-05 to 04-05', 1],
          ['07-06 to 10-06', 2]
        ]);

        // Set chart options
        var options = {'title':'How Much Pizza I Ate Last Night',
                       'width':500,
                       'height':300};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
        chart.draw(data, options);
        
        data.addRows([
			['11-06 to 18-06', 10]
        ]);
        
     	// Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div2'));
        chart.draw(data, options);
      }
    </script>
</head>
<body>

<jsp:include page="NavBar.jsp" >
  <jsp:param name="menu" value="tutorial" />
</jsp:include>

<div class="container">
	<h1 class="text-left text-primary">Google Charts Tutorial</h1>
</div>

<div class="container">
	<div class="col-sm-6" id="chart_div" style="width:500; height:300"></div>
	<div class="col-sm-6" id="chart_div2" style="width:500; height:300"></div>
</div>

</body>
</html>
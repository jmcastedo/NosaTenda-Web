<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nosa Tenda - Crear producto UTF</title>
</head>
<body text="#000000" bgcolor="#ffffff">

<div align="center">
	<p>
		<font color="#000099" face="Arial, Helvetica, san-serif">
			<b>Crear producto UTF</b>
		</font>
	</p>
</div>

<form method="post" action="AddProducto">

<table width="100%" border="0" align="center" cellspacing="12">

	<tr>
		<th align="right" width="50%">
			Nombre
		</th>
		<td align="left">
			<input type="text" name="nombre" size="64" maxlength="100">
		</td>
	</tr>
	
	<tr>
		<td width="50%"></td>
		<td align="left" width="50%">
			<input type="submit" value="Crear producto">
		</td>
	</tr>

</table>

</form>


</body>
</html>
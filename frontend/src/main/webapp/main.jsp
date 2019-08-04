<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Beverage Store - Group 1</title>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>Welcome to the Beverage Store</h1>
		
		<table cellpadding="50">
		<tr>
		<td><form action="${pageContext.request.contextPath}//InitializePage" method="get">
			<input class="btn btn-primary" type="submit" name="addBeverage" value="Place an order(for Customer)">
		</form></td>
		<td></td>
		<td><form action="${pageContext.request.contextPath}//GetBeverage" method="get">
			<input class="btn btn-primary" type="submit" name="getBeverage" value="Manage Beverages(for Salesman)">
		</form></td>
		<td></td>
		<td><form action="${pageContext.request.contextPath}//BIReportServlet" method="get">
			<input class="btn btn-primary" type="submit" name="bireport" value="BI Report">
		</form></td>
		</tr>
		</table>
		<br>
		<br>
		<h5>Developed by: Group 1</h5>
		<h6>Sushmita Zimal</h6>
		<h6>Sneha Metkari</h6>
		<h6>Pooja Kumbhar</h6>
		<h6>Venkatesh Parayitam</h6>
		<h6>Pravin Garad</h6>
		<h6>Yogeshwar Mareddy</h6>
	</div>
</body>
</html>

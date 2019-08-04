<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="de.uniba.dsg.dsam.model.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Salesman</title>
</head>
<body>
	 <% 	List<Beverage> listbeverage = (List<Beverage>) request.getAttribute("beveragelist");%>
	 
	<form  action="${pageContext.request.contextPath}//BeveragesServlet"  method="post">
		<h2>Add a new Beverage</h2>
		Manufacturer: <input type="text" name="manufacturer"><br>
		Beverage Name: <input type="text" name="beverageName"><br>
		Quantity: <input type="text" name="quantity"><br>
		Price: <input type="text" name="price"><br>
		Incentive Name: <input type="text" name="iname"><br>
		Incentive Type: <input type="radio" name="itype" value="gift" checked>Promotional Gift
						<input type="radio" name="itype" value="package">Trial Package<br>
		Incentive Description: <input type="text" name="idesc"><br>
		
		<input type="submit" value="Create a new Beverage" name="create"  value="create">
		
		<br>
		<br>
		<h2>Update Beverage Quantity</h2>
		Beverage Name:  <select name="beverage_update"><% for(Beverage beverage: listbeverage) { %><option value=<%=beverage.getName() %>><%=beverage.getName()%>
    					</option>
    					<%}%>
   						 </select>
		Quantity: <input type="text" name="quantityUpdate"><br>
		<input type="submit" value="Update quantity" name="update"  value="update">
		<br>
		<br>
		<h2>Delete a Beverage</h2>
		Beverage Name: <select name="beverage_delete"><% for(Beverage beverage: listbeverage) { %><option value=<%=beverage.getName() %>><%=beverage.getName()%>
    					</option>
    					<%}%>
   						 </select><br>
		<input type="submit" value="Delete Beverage" name="delete" value="delete">
	</form>
	
	
</body>
</html>
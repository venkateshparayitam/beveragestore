<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="de.uniba.dsg.dsam.model.*" %>
<!DOCTYPE html>
<html>
<head>
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
</head>
<body>
<form role="form" action="${pageContext.request.contextPath}//CustomerOrderFillerServlet" method="post">
<h2>Place Your Order</h2>

<table id = "display_table">
  <tr>
    <th>Manufacturer</th>
    <th>Name</th>
    <th>Price</th>
    <th>Select Qty.</th>
    <th>Available Qty.</th>
    <th>Incentive</th>
    <th>Selected</th>
  </tr>
  <% 	List<Beverage> listbeverage = (List<Beverage>) request.getAttribute("beveragelist");
  		List<String> incentive_names = (List<String>) request.getAttribute("incentivenames");
  int j = 1;
  int n = 0;
  for(Beverage beverage: listbeverage) { 
	  
	%>
  
  <tr>
    <td><%= beverage.getManufacturer() %></td>
    <td><%= beverage.getName() %></td>
    <td><%= beverage.getPrice() %></td>
    
    <td>
    <select name="beverage_qty_<%=j%>"><% for(int i=0;i<=beverage.getQuantity();i++){ %><option value=<%=i %>><%=i%>
    </option>
    <%}%>
    </select>
    </td>
    <td><%= beverage.getQuantity() %></td>
    <td><%= incentive_names.get(n)%></td>
    <td><input type="checkbox" id = "chckHead" name="checkbox" value=<%=beverage.getManufacturer()+","+beverage.getName()+","+beverage.getPrice()+","%><%=j%> /></td>
  </tr>
  <% j++;
     n++;
  } %>
  
</table>
 <button type="submit" class="btn btn-success" >Place Order</button>
 
</form>
</body>
</html>

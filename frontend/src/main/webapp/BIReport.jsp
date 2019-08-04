<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@ page import="java.util.*" %>
<%@ page import="de.uniba.dsg.dsam.model.*" %>
 
<%
Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
List<BIReport> bireport_list = (List<BIReport>) request.getAttribute("bireportlist");
int j = 1;
for(BIReport bireport: bireport_list) { 
map = new HashMap<Object,Object>(); map.put("label", bireport.getManufacturer()); map.put("y", bireport.getQuantity()); list.add(map);
}
 
String dataPoints = gsonObj.toJson(list);
%>
 
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
window.onload = function() { 
 
var chart = new CanvasJS.Chart("chartContainer", {
	theme: "light2",
	animationEnabled: true,
	exportFileName: "Beverages Sold",
	exportEnabled: true,
	title:{
		text: "Beverages Sold"
	},
	data: [{
		type: "pie",
		showInLegend: true,
		legendText: "{label}",
		toolTipContent: "{label}: <strong>{y}</strong>",
		indexLabel: "{label} {y}",
		dataPoints : <%out.print(dataPoints);%>
	}]
});
 
chart.render();
 
}
</script>
</head>
<body>
<div id="chartContainer" style="height: 370px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html>  
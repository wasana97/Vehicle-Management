<%@page import="com.oop.model.Vehicle"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.oop.service.VehicleServiceImpl"%>
<%@page import="com.oop.service.IVehicleService"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel = "stylesheet"
   type = "text/css"
   href = "Vehicle.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SLIIT OOP Vehicle Management</title>
</head>
<body>
	<h3>List of Vehicles</h3>
	SLIIT Vehicle Management App for OOP jsp servlet.
	<br>
	<br>
	  <div align="left">
		<table border="1" cellpadding="12">
		 <caption><h2>List of Vehicles</h2></caption>
		 <a href="homeView.jsp">Add Vehicle</a>
		  <tr>
                <th>Vehicle ID</th>
                <th>Vehicle Number</th>
                <th>Brand</th>
                <th>Owner NIC</th>
                <th>Number Of Passengers</th>
                <th>Select</th>
            </tr>
            <%
            	IVehicleService iVehicleService = new VehicleServiceImpl();
                                    	ArrayList<Vehicle> arrayList = iVehicleService.getVehicles();
                                    	
                                    	for(Vehicle vehicle : arrayList){
            %>
			 <tr>
				<td> <%=vehicle.getVehicleID() %> </td>
				<td> <%=vehicle.getVehicleNumber() %> </td>
				<td> <%=vehicle.getBrand() %> </td>
				<td> <%=vehicle.getOwnerNIC() %> </td>
				<td> <%=vehicle.getNoOfPassengers() %> </td>
					
				<td> 
				<form method="POST" action="GetVehicleServlet">
				<input type="hidden" name="vehicleID" value="<%=vehicle.getVehicleID()%>"/>
				 <input type="submit" value= "Select Vehicle" class="select-button" /> 
				 </form>
				 </td>	
				</tr>			
			<%	
			   }
            %>     
		</table>
		</div>
		
</body>
</html>
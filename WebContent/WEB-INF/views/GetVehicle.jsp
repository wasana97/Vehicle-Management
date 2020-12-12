<%@page import="com.oop.model.Vehicle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel = "stylesheet"
   type = "text/css"
   href = "Vehicle.css" />
<meta charset="UTF-8">
<title>SLIIT OOP Vehicle Management</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

	<h2 class="h2">Get Vehicle Page</h2>

	SLIIT Vehicle Management App for OOP jsp servlet.
	<br>
	<br>
	<%
		Vehicle vehicle = (Vehicle) request.getAttribute("vehicle");
	%>

	<form method="POST" action="UpdateVehicleServlet">
		<table>
			<tr>
				<td>Vehicle ID</td>
				<td><input type="text" name="vehicleID" disabled="disabled"
					value="<%=vehicle.getVehicleID()%>" /></td>
			</tr>
			<tr>
				<td>Vehicle Number</td>
				<td><input type="text" name="vehicleNumber"
					value="<%=vehicle.getVehicleNumber()%>" /></td>
			</tr>
			<tr>
				<td>Vehicle Brand</td>
				<td><input type="text" name="brand"
					value="<%=vehicle.getBrand()%>" /></td>
			</tr>
			<tr>
				<td>Owner NIC</td>
				<td><input type="text" name="ownerNIC"
					value="<%=vehicle.getOwnerNIC()%>" /></td>
			</tr>
			<tr>
				<td>Number Of Passengers</td>
				<td><input type="radio" name="noOfPassenger"
					value="3"  checked="checked" tabindex="1"/>3</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="radio" name="noOfPassenger" value="4"
					tabindex="2" /> 4</td>
			</tr>
			
			<tr>
				<td></td>
				<td><input type="radio" name="noOfPassenger" value="6"
					tabindex="3" /> 6</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="radio" name="noOfPassenger" value="10"
					tabindex="4" /> 10</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="radio" name="noOfPassenger" value="15"
					tabindex="5" /> 15</td>
			</tr>
			<tr>
				<td colspan="2"><input type="hidden" name="vehicleID"
					value="<%=vehicle.getVehicleID()%>" /> <input type="submit"
					value="Update Vehicle" class="update-button"/></td>
			</tr>
		</table>
	</form>

	<table>
		<tr>
			<td colspan="2">
				<form method="POST" action="DeleteVehicleServlet">
					<input type="hidden" name="vehicleID"
						value="<%=vehicle.getVehicleID()%>" /> <input type="submit"
						value="Delete Vehicle" class="delete-button"/>
				</form>
			</td>
		</tr>
	</table>

	<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>

</body>
</html>
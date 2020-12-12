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
<body class="body">

	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

	<h2 class="h2">Add Vehicle Page</h2>

	   SLIIT Vehicle Management App for Object Orineted Progrmming
	<br>
	<br>

	<form method="POST" action="AddVehicleServlet">
		<table>

			<tr>
				<td>Vehicle Number</td>
				<td><input type="text" name="vehicleNumber" /></td>
			</tr>
			<tr>
				<td>Vehicle Brand</td>
				<td><input type="text" name="brand" /></td>
			</tr>
			<tr>
				<td>Owner NIC</td>
				<td><input type="text" name="ownerNIC" /></td>
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
				<td colspan="2"><input type="submit" value="Add Vehicle" class="add-button" /> </td>
			</tr>
			<tr>	
				<td colspan="2"><input type="reset" value="Reset" class="reset-button" /></td>
			</tr>
		</table>
	</form>

	<form method="POST" action="ListVehicleServlet">
		<table>
			<tr>
				<td colspan="2"><input type="submit" value="List All Vehicles" class="list-button" />
				</td>
			</tr>
		</table>
	</form>

	<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>

</body>
</html>
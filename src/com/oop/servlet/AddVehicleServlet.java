package com.oop.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oop.model.Vehicle;
import com.oop.service.VehicleServiceImpl;
import com.oop.service.IVehicleService;


public class AddVehicleServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	public AddVehicleServlet() {
		super();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		Vehicle vehicle = new Vehicle();
		
		vehicle.setVehicleNumber(request.getParameter("vehicleNumber"));
		vehicle.setBrand(request.getParameter("brand"));
		vehicle.setOwnerNIC(request.getParameter("ownerNIC"));
		vehicle.setNoOfPassengers(request.getParameter("noOfPassenger"));
		

		IVehicleService iVehicleService = new VehicleServiceImpl();
		iVehicleService.addVehicle(vehicle);

		request.setAttribute("vehicle", vehicle);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListVehicles.jsp");
		dispatcher.forward(request, response);
	}

}

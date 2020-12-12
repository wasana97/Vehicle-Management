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

/**
 * Delete vehicles servlet
 */
public class DeleteVehicleServlet extends HttpServlet {


	
	private static final long serialVersionUID = 1871871796669342804L;

	
	public DeleteVehicleServlet() {
		super();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		String vehicleID = request.getParameter("vehicleID");			
		
		IVehicleService iVehicleService = new VehicleServiceImpl();
		iVehicleService.removeVehicle(vehicleID);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListVehicles.jsp");
		dispatcher.forward(request, response);
	}

}

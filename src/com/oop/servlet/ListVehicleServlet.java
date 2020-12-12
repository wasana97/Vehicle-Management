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
 * Servlet implementation for List of Vehicles
 */
public class ListVehicleServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	
	public ListVehicleServlet() {
		super();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListVehicles.jsp");
		dispatcher.forward(request, response);
	}

}

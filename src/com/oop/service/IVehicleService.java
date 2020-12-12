
package com.oop.service;

import com.oop.model.Vehicle;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;


public interface IVehicleService {

	
	public static final Logger log = Logger.getLogger(IVehicleService.class.getName());


	/**
	 * Add vehicles for vehicle table
	 * @param vehicle
	 */
	public void addVehicle(Vehicle vehicle);

	/**
	 * Get a particular Vehicle
	 * 
	 * @param vehicleID
	 * @return Vehicle
	 */
	public Vehicle getVehicleByID(String vehicleID);
	
	/**
	 * Get all list of vehicles
	 * 
	 * @return ArrayList<Vehicle>
	 */
	public ArrayList<Vehicle> getVehicles();
	
	/**
	 * Update existing vehicle
	 * @param vehicleID
	 * @param vehicle
	 * 
	 * @return
	 */
	public Vehicle updateVehicle(String vehicleID, Vehicle vehicle);

	/**
	 * Remove existing vehicle
	 * 
	 * @param vehicleID
	 */
	public void removeVehicle(String vehicleID);

}

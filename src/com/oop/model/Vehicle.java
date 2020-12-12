package com.oop.model;


public class Vehicle {

	private String VehicleID;
	
	private String VehicleNumber;

	private String Brand;

	private String OwnerNIC;

	private String NoOfPassengers;

	
	
	public String getVehicleID() {
		return VehicleID;
	}
	
	public void setVehicleID(String vehicleID) {
		VehicleID =vehicleID;
	}

	public String getVehicleNumber() {
		return VehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		VehicleNumber = vehicleNumber;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}

	public String getOwnerNIC() {
		return OwnerNIC;
	}

	public void setOwnerNIC(String ownerNIC) {
		OwnerNIC = ownerNIC;
	}

	public String getNoOfPassengers() {
		return NoOfPassengers;
	}

	public void setNoOfPassengers(String noOfPassengers) {
		NoOfPassengers = noOfPassengers;
	}


}

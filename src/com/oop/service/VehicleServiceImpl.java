package com.oop.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.oop.model.Vehicle;
import com.oop.util.CommonConstants;
import com.oop.util.CommonUtil;
import com.oop.util.DBConnectionUtil;
import com.oop.util.QueryUtil;


public class VehicleServiceImpl implements IVehicleService {
	

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(VehicleServiceImpl.class.getName());

	private static Connection connection;

	private static Statement statement;

	static{
		
		createVehicleTable();
	}

	private PreparedStatement preparedStatement;

	
	public static void createVehicleTable() {

		try {
			connection = DBConnectionUtil.getDBConnection();
			statement = connection.createStatement();
			// Drop table if already exists and as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_DROP_TABLE));
			// Create new vehicles table as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_CREATE_TABLE));

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 * Add set of vehicles for as a batch from the selected vehicle List
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * 
	 */
	@Override
	public void addVehicle(Vehicle vehicle) {

		String vehicleID = CommonUtil.generateIDs(getVehicleIDs());
		
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Query is available in VehicleQuery.xml file and use
			 * insert_vehicle key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_VEHICLES));
			connection.setAutoCommit(false);
			
			//Generate vehicle IDs
			vehicle.setVehicleID(vehicleID);
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, vehicle.getVehicleID());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, vehicle.getVehicleNumber());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, vehicle.getBrand());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, vehicle.getOwnerNIC());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, vehicle.getNoOfPassengers());

			// Add vehicle
			preparedStatement.execute();
			connection.commit();

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	/**
	 * Vehicle details can be retrieved based on the provided vehicle ID
	 * 
	 * @param vehicleID
	 *            - Vehicle details are filtered based on the ID
	 * 
	 * @see #actionOnVehicle()
	 */
	@Override
	public Vehicle getVehicleByID(String vehicleID) {

		return actionOnVehicle(vehicleID).get(0);
	}
	
	/**
	 * Get all list of vehicles
	 * 
	 * @return ArrayList<Vehicle> 
	 * 						- Array of vehicle list will be return
	 * 
	 * @see #actionOnVehicle()
	 */
	@Override
	public ArrayList<Vehicle> getVehicles() {
		
		return actionOnVehicle(null);
	}

	
	@Override
	public void removeVehicle(String vehicleID) {

		// Before deleting check whether vehicle ID is available
		if (vehicleID != null && !vehicleID.isEmpty()) {
			/*
			 * Remove vehicle query will be retrieved from VehicleQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_REMOVE_VEHICLE));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, vehicleID);
				preparedStatement.executeUpdate();
			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {
				
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					log.log(Level.SEVERE, e.getMessage());
				}
			}
		}
	}

	/**
	 * This performs GET vehicle by ID and Display all vehicles
	 * 
	 * @param vehicleID
	 *            ID of the vehicle to remove or select from the list

	 * @return ArrayList<Vehicle> Array of vehicle list will be return
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws ClassNotFoundException
	 *             - Thrown when an application tries to load in a class through
	 *             its string name using
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * @throws NullPointerException
	 *             - Service is not available
	 * 
	 * @see #getVehicles()
	 * @see #getVehicleByID(String)
	 */
	private ArrayList<Vehicle> actionOnVehicle(String vehicleID) {

		ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Before fetching vehicle it checks whether vehicle ID is
			 * available
			 */
			if (vehicleID != null && !vehicleID.isEmpty()) {
				/*
				 * Get vehicle by ID query will be retrieved from
				 * VehicleQuery.xml
				 */
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_VEHICLE));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, vehicleID);
			}
			/*
			 * If vehicle ID is not provided for get vehicle option it display
			 * all vehicles
			 */
			else {
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_ALL_VEHICLES));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Vehicle vehicle = new Vehicle();
				vehicle.setVehicleID(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				vehicle.setVehicleNumber(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				vehicle.setBrand(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				vehicle.setOwnerNIC(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
				vehicle.setNoOfPassengers(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				vehicleList.add(vehicle);
			}

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return vehicleList;
	}

	/**
	 * Get the updated vehicle
	 * 
	 * @param vehicleID
	 *            ID of the vehicle to remove or select from the list
	 * 
	 * @return return the Vehicle object
	 * 
	 */
	@Override
	public Vehicle updateVehicle(String vehicleID, Vehicle vehicle) {

		/*
		 * Before fetching vehicle it checks whether vehicle ID is available
		 */
		if (vehicleID != null && !vehicleID.isEmpty()) {
			/*
			 * Update vehicle query will be retrieved from VehicleQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_UPDATE_VEHICLE));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, vehicle.getVehicleNumber());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, vehicle.getBrand());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, vehicle.getOwnerNIC());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, vehicle.getNoOfPassengers());
				preparedStatement.executeUpdate();

			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {
				/*
				 * Close prepared statement and database connectivity at the end
				 * of transaction
				 */
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					log.log(Level.SEVERE, e.getMessage());
				}
			}
		}
		// Get the updated vehicle
		return getVehicleByID(vehicleID);
	}
	
	/**
	 *
	 * @return ArrayList<String> Array of vehicle id list will be return
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws ClassNotFoundException
	 *             - Thrown when an application tries to load in a class through
	 *             its string name using
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * @throws NullPointerException
	 *             - Service is not available
	 */
	private ArrayList<String> getVehicleIDs(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		/*
		 * List of vehicle IDs will be retrieved from VehicleQuery.xml
		 */
		try {
			connection = DBConnectionUtil.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_VEHICLE_IDS));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				arrayList.add(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
			}
		} catch (SQLException | SAXException | IOException | ParserConfigurationException
				| ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return arrayList;
	}
}

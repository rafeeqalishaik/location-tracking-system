package com.location.tracking.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Class Mobile.
 */
/**
 * @author Rafeeq Ali Shaik
 *
 */
@Document
public class Mobile {

    /** The mobile id. */
    private String mobileId;
    
    /** The vehicle id. */
    private String vehicleId;
    
    /** The driver id. */
    private String driverId;
    
    /** The locations. */
    private List<Location> locations;

    /**
     * Gets the mobile id.
     *
     * @return the mobile id
     */
    public String getMobileId() {
	return mobileId;
    }

    /**
     * Sets the mobile id.
     *
     * @param mobileId the new mobile id
     */
    public void setMobileId(String mobileId) {
	this.mobileId = mobileId;
    }

    /**
     * Gets the vehicle id.
     *
     * @return the vehicle id
     */
    public String getVehicleId() {
	return vehicleId;
    }

    /**
     * Sets the vehicle id.
     *
     * @param vehicleId the new vehicle id
     */
    public void setVehicleId(String vehicleId) {
	this.vehicleId = vehicleId;
    }

    /**
     * Gets the driver id.
     *
     * @return the driver id
     */
    public String getDriverId() {
	return driverId;
    }

    /**
     * Sets the driver id.
     *
     * @param driverId the new driver id
     */
    public void setDriverId(String driverId) {
	this.driverId = driverId;
    }

    /**
     * Gets the locations.
     *
     * @return the locations
     */
    public List<Location> getLocations() {
	return locations;
    }

    /**
     * Sets the locations.
     *
     * @param locations the new locations
     */
    public void setLocations(List<Location> locations) {
	this.locations = locations;
    }

}

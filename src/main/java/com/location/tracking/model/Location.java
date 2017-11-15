package com.location.tracking.model;

import java.util.Date;

/**
 * The Class Location.
 */
/**
 * @author Rafeeq Ali Shaik
 *
 */
public class Location {

    /** The latitude. */
    private String latitude;

    /** The longitude. */
    private String longitude;

    /** The location. */
    private String location;

    /** The timestamp. */
    private Date timestamp;

    /**
     * Instantiates a new location.
     */
    public Location() {
	super();
	this.timestamp = new Date();
    }

    /**
     * Instantiates a new location.
     *
     * @param latitude the latitude
     * @param longitude the longitude
     * @param location the location
     */
    public Location(String latitude, String longitude, String location) {
	super();
	this.latitude = latitude;
	this.longitude = longitude;
	this.location = location;
    }

    /**
     * Gets the latitude.
     *
     * @return the latitude
     */
    public String getLatitude() {
	return latitude;
    }

    /**
     * Sets the latitude.
     *
     * @param latitude the new latitude
     */
    public void setLatitude(String latitude) {
	this.latitude = latitude;
    }

    /**
     * Gets the longitude.
     *
     * @return the longitude
     */
    public String getLongitude() {
	return longitude;
    }

    /**
     * Sets the longitude.
     *
     * @param longitude the new longitude
     */
    public void setLongitude(String longitude) {
	this.longitude = longitude;
    }

    /**
     * Gets the location.
     *
     * @return the location
     */
    public String getLocation() {
	return location;
    }

    /**
     * Sets the location.
     *
     * @param location the new location
     */
    public void setLocation(String location) {
	this.location = location;
    }

    /**
     * Gets the timestamp.
     *
     * @return the timestamp
     */
    public Date getTimestamp() {
	return timestamp;
    }

    /**
     * Sets the timestamp.
     *
     * @param timestamp the new timestamp
     */
    public void setTimestamp(Date timestamp) {
	this.timestamp = timestamp;
    }
    
    @Override
    public String toString() {
	return "latitude: " + latitude + " longitude: " + longitude + " location: " + location + " timestamp: " + timestamp;
    }

}

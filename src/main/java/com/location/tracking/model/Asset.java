/*
 * 
 */
package com.location.tracking.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Class Asset.
 *
 * @author Rafeeq Ali Shaik
 */
@Document
public class Asset {
    
    /** The asset id. */
    private String assetId;

    /** The locations. */
    private List<Location> locations;

    /**
     * Gets the asset id.
     *
     * @return the asset id
     */
    public String getAssetId() {
	return assetId;
    }

    /**
     * Sets the asset id.
     *
     * @param assetId the new asset id
     */
    public void setAssetId(String assetId) {
	this.assetId = assetId;
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

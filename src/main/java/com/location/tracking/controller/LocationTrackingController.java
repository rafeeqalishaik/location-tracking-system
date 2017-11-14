package com.location.tracking.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.location.tracking.constants.AppConstants;
import com.location.tracking.exception.CustomException;
import com.location.tracking.model.Asset;
import com.location.tracking.model.Mobile;
import com.location.tracking.utils.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class LocationTrackingController.
 */
/**
 * @author Rafeeq Ali Shaik
 *
 */
@RestController
@RequestMapping(value = "/tracker")
public class LocationTrackingController {

    /** The mongo template. */
    private @Autowired MongoTemplate mongoTemplate;

    /**
     * Adds the asset event.
     *
     * @param asset
     *            the asset
     * @throws CustomException
     *             the custom exception
     */
    @RequestMapping(method = RequestMethod.POST, value = "/addAssetEvent")
    public void addAssetEvent(@RequestBody Asset asset) throws CustomException {

	if (asset.getAssetId() != null) {
	    mongoTemplate.upsert(new Query().addCriteria(Criteria.where("assetId").is(asset.getAssetId())),
		    new Update().push("locations", asset.getLocations().get(AppConstants.NUMERIC_ZERO)), Asset.class);
	} else {
	    throw new CustomException(HttpStatus.UNPROCESSABLE_ENTITY, "Asset must not be null", "/tracker/assetEvent");
	}

    }

    /**
     * Adds the mobile event.
     *
     * @param mobile
     *            the mobile
     * @throws CustomException
     *             the custom exception
     */
    @RequestMapping(method = RequestMethod.POST, value = "/addMobileEvent")
    public void addMobileEvent(@RequestBody Mobile mobile) throws CustomException {

	if (mobile.getMobileId() != null) {
	    mongoTemplate.updateFirst(new Query().addCriteria(Criteria.where("mobileId").is(mobile.getMobileId())),
		    new Update().push("locations", mobile.getLocations().get(AppConstants.NUMERIC_ZERO)), Mobile.class);
	} else {
	    throw new CustomException(HttpStatus.UNPROCESSABLE_ENTITY, "Mobile device entity must not be null",
		    "/tracker/deviceEvent");
	}

    }

    /**
     * Gets the pings by asset id.
     *
     * @param assetId
     *            the asset id
     * @return the pings by asset id
     * @throws CustomException
     *             the custom exception
     */
    @RequestMapping(value = "/getPingsByAssetId/{assetId}")
    public List<Asset> getPingsByAssetId(@PathVariable String assetId) throws CustomException {

	if (assetId != null) {
	    Query query = new Query().addCriteria(Criteria.where("assetId").is(assetId));
	    return mongoTemplate.find(query, Asset.class);
	} else {
	    throw new CustomException(HttpStatus.UNPROCESSABLE_ENTITY,
		    "Either assetId or startTime and endTime should not be null", "/tracker/getPingsByAssetId");
	}
    }

    /**
     * Gets the pings by mobile id.
     *
     * @param mobileId
     *            the mobile id
     * @return the pings by mobile id
     * @throws CustomException
     *             the custom exception
     */
    @RequestMapping("/getPingsByMobileId/{mobileId}")
    public List<Mobile> getPingsByMobileId(@PathVariable String mobileId) throws CustomException {
	if (mobileId != null) {
	    Query query = new Query().addCriteria(Criteria.where("mobileId").is(mobileId));
	    return mongoTemplate.find(query, Mobile.class);
	} else {
	    throw new CustomException(HttpStatus.UNPROCESSABLE_ENTITY, "mobileId must not be null",
		    "/tracker/getPingsByMobileId/{mobileId}");
	}
    }

    /**
     * Gets the pings by asset id and time interval.
     *
     * @param assetId
     *            the asset id
     * @param startTime
     *            the start time
     * @param endTime
     *            the end time
     * @return the pings by asset id and time interval
     * @throws ParseException
     *             the parse exception
     * @throws CustomException
     *             the custom exception
     */
    @RequestMapping(value = "/getPingsByAssetIdAndTimeInterval/{assetId}/{startTime}/{endTime}")
    public List<Asset> getPingsByAssetIdAndTimeInterval(@PathVariable String assetId, @PathVariable String startTime,
	    @PathVariable String endTime) throws ParseException, CustomException {
	if (assetId != null && startTime != null && endTime != null) {
	    Query query = new Query(Criteria.where("assetId").is(assetId).andOperator(
		    Criteria.where("locations.timestamp").gte(Utils.parseDate(startTime)),
		    Criteria.where("locations.timestamp").lte(Utils.parseDate(endTime))));
	    return mongoTemplate.find(query, Asset.class);
	} else {
	    throw new CustomException(HttpStatus.UNPROCESSABLE_ENTITY, "startTime and endTime must not be null",
		    "/getPingsByTimeInterval/{assetId}/{startTime}/{endTime}");
	}
    }

    /**
     * Gets the pings by mobile id and time interval.
     *
     * @param mobileId
     *            the mobile id
     * @param startTime
     *            the start time
     * @param endTime
     *            the end time
     * @return the pings by mobile id and time interval
     * @throws ParseException
     *             the parse exception
     * @throws CustomException
     *             the custom exception
     */
    @RequestMapping(value = "/getPingsByMobileIdAndTimeInterval/{mobileId}/{startTime}/{endTime}")
    public List<Mobile> getPingsByMobileIdAndTimeInterval(@PathVariable String mobileId, @PathVariable String startTime,
	    @PathVariable String endTime) throws ParseException, CustomException {

	if (mobileId != null && startTime != null && endTime != null) {
	    Query query = new Query(Criteria.where("mobileId").is(mobileId).andOperator(
		    Criteria.where("locations.timestamp").gte(Utils.parseDate(startTime)),
		    Criteria.where("locations.timestamp").lte(Utils.parseDate(endTime))));
	    return mongoTemplate.find(query, Mobile.class);
	} else {
	    throw new CustomException(HttpStatus.UNPROCESSABLE_ENTITY, "startTime and endTime must not be null",
		    "/getPingsByTimeInterval/{assetId}/{startTime}/{endTime}");
	}

    }
}

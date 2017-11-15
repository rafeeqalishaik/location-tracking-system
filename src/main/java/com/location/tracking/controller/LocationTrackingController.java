package com.location.tracking.controller;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.fields;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.location.tracking.constants.AppConstants;
import com.location.tracking.exception.CustomException;
import com.location.tracking.model.Asset;
import com.location.tracking.model.Location;
import com.location.tracking.model.Mobile;
import com.location.tracking.model.Response;
import com.location.tracking.utils.Utils;

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
     * @return the response entity
     * @throws CustomException
     */
    @RequestMapping(method = RequestMethod.POST, value = "/addAssetEvent")
    public ResponseEntity<?> addAssetEvent(@RequestBody Asset asset) throws CustomException {

	if (asset.getAssetId() != null) {
	    mongoTemplate.upsert(new Query().addCriteria(Criteria.where("assetId").is(asset.getAssetId())),
		    new Update().push("locations", asset.getLocations().get(AppConstants.NUMERIC_ZERO)), Asset.class);
	    return new ResponseEntity<>(new Response("SUCCESS", "Asset Added/Updated Successfully"), HttpStatus.OK);
	} else {
	    throw new CustomException(HttpStatus.UNPROCESSABLE_ENTITY, "Asset must not be null", "/tracker/assetEvent");
	}

    }

    /**
     * Adds the mobile event.
     *
     * @param mobile
     * @return the response entity
     * @throws CustomException
     */
    @RequestMapping(method = RequestMethod.POST, value = "/addMobileEvent")
    public ResponseEntity<?> addMobileEvent(@RequestBody Mobile mobile) throws CustomException {

	if (mobile.getMobileId() != null) {
	    mongoTemplate.updateFirst(new Query().addCriteria(Criteria.where("mobileId").is(mobile.getMobileId())),
		    new Update().push("locations", mobile.getLocations().get(AppConstants.NUMERIC_ZERO)), Mobile.class);
	    return new ResponseEntity<>(new Response("SUCCESS", "MobileDevice Added/Updated Successfully"),
		    HttpStatus.OK);
	} else {
	    throw new CustomException(HttpStatus.UNPROCESSABLE_ENTITY, "Mobile device entity must not be null",
		    "/tracker/deviceEvent");
	}

    }

    /**
     * Gets the pings by asset id.
     *
     * @param assetId
     * @return the pings by asset id
     * @throws CustomException
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
     * @return the pings by mobile id
     * @throws CustomException
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
     * @param startTime
     * @param endTime
     * @throws ParseException
     * @throws CustomException
     */
    @RequestMapping(value = "/getPingsByAssetIdAndTimeInterval/{assetId}/{startTime}/{endTime}")
    public ResponseEntity<?> getPingsByAssetIdAndTimeInterval(@PathVariable String assetId,
	    @PathVariable String startTime, @PathVariable String endTime) throws ParseException, CustomException {
	Aggregation agg = newAggregation(unwind("locations"),
		match(Criteria.where("assetId").is(assetId)
			.andOperator(Criteria.where("locations.timestamp").gte(Utils.parseDate(startTime))
				.lte(Utils.parseDate(endTime)))),
		project(fields().and("location", "$locations")), group("location"));
	AggregationResults<Location> aggregateResult = mongoTemplate.aggregate(agg, "asset", Location.class);

	List<Location> locations = aggregateResult.getMappedResults();

	if (locations != null && !locations.isEmpty()) {
	    return new ResponseEntity<>(locations, HttpStatus.OK);
	} else {
	    return new ResponseEntity<>(new Response("SUCCESS", "No results found for search criteria"),
		    HttpStatus.NOT_FOUND);
	}
    }

    /**
     * Gets the pings by mobile id and time interval.
     *
     * @param mobileId
     * @param startTime
     * @param endTime
     * @return the pings by mobile id and time interval
     * @throws ParseException
     * @throws CustomException
     */
    @RequestMapping(value = "/getPingsByMobileIdAndTimeInterval/{mobileId}/{startTime}/{endTime}")
    public ResponseEntity<?> getPingsByMobileIdAndTimeInterval(@PathVariable String mobileId,
	    @PathVariable String startTime, @PathVariable String endTime) throws ParseException, CustomException {
	Aggregation agg = newAggregation(unwind("locations"),
		match(Criteria.where("mobileId").is(mobileId)
			.andOperator(Criteria.where("locations.timestamp").gte(Utils.parseDate(startTime))
				.lte(Utils.parseDate(endTime)))),
		project(fields().and("location", "$locations")), group("location"));
	AggregationResults<Location> aggregateResult = mongoTemplate.aggregate(agg, "mobile", Location.class);

	List<Location> locations = aggregateResult.getMappedResults();

	if (locations != null && !locations.isEmpty()) {
	    return new ResponseEntity<>(locations, HttpStatus.OK);
	} else {
	    return new ResponseEntity<>(new Response("SUCCESS", "No results found for search criteria"),
		    HttpStatus.NOT_FOUND);
	}
    }

}
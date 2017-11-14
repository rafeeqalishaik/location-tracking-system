package com.location.tracking.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.location.tracking.exception.CustomException;
import com.location.tracking.model.TrackerResponseEntity;

/**
 * The Class RestExceptionHandler.
 */
/**
 * @author Rafeeq Ali Shaik
 *
 */
@ControllerAdvice
public class RestExceptionHandler {

    /**
     * Custom exception.
     *
     * @param customeException the custome exception
     * @return the response entity
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<TrackerResponseEntity> customException(CustomException customeException) {

	TrackerResponseEntity trackerResponseEntity = new TrackerResponseEntity();
	trackerResponseEntity.setMessage(customeException.getMessage());
	trackerResponseEntity.setStatus(customeException.getStatus().value());
	trackerResponseEntity.setTimestamp(customeException.getTimestamp());
	trackerResponseEntity.setPath(customeException.getPath());

	return new ResponseEntity<TrackerResponseEntity>(trackerResponseEntity, customeException.getStatus());

    }

}

package com.location.tracking.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

/**
 * The Class CustomException.
 */
/**
 * @author Rafeeq Ali Shaik
 *
 */
public class CustomException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6005437100958218845L;

    /** The status. */
    private HttpStatus status;

    /** The timestamp. */
    private long timestamp;

    /** The message. */
    private String message;

    /** The path. */
    private String path;

    /**
     * Instantiates a new custom exception.
     */
    public CustomException() {
	super();
    }

    /**
     * Instantiates a new custom exception.
     *
     * @param status the status
     * @param message the message
     * @param path the path
     */
    public CustomException(HttpStatus status, String message, String path) {
	super();
	this.timestamp = new Date().getTime();
	this.status = status;
	this.message = message;
	this.path = path;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public HttpStatus getStatus() {
	return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(HttpStatus status) {
	this.status = status;
    }

    /**
     * Gets the timestamp.
     *
     * @return the timestamp
     */
    public long getTimestamp() {
	return timestamp;
    }

    /**
     * Sets the timestamp.
     *
     * @param timestamp the new timestamp
     */
    public void setTimestamp(long timestamp) {
	this.timestamp = timestamp;
    }

    /* (non-Javadoc)
     * @see java.lang.Throwable#getMessage()
     */
    public String getMessage() {
	return message;
    }

    /**
     * Sets the message.
     *
     * @param message the new message
     */
    public void setMessage(String message) {
	this.message = message;
    }

    /**
     * Gets the path.
     *
     * @return the path
     */
    public String getPath() {
	return path;
    }

    /**
     * Sets the path.
     *
     * @param path the new path
     */
    public void setPath(String path) {
	this.path = path;
    }

}

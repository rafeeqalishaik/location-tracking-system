package com.location.tracking.model;

/**
 * The Class TrackerResponseEntity.
 */
/**
 * @author Rafeeq Ali Shaik
 *
 */
public class TrackerResponseEntity {

    /** The status. */
    private int status;

    /** The timestamp. */
    private long timestamp;

    /** The message. */
    private String message;

    /** The path. */
    private String path;

    /**
     * Gets the status.
     *
     * @return the status
     */
    public int getStatus() {
	return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(int status) {
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

    /**
     * Gets the message.
     *
     * @return the message
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
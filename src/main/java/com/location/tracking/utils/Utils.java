package com.location.tracking.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.location.tracking.constants.AppConstants;

/**
 * The Class Utils.
 *
 * @author Rafeeq Ali Shaik
 */
public class Utils {

    /**
     * Parses the date.
     *
     * @param timestamp
     *            the timestamp
     * @return the date
     * @throws ParseException
     *             the parse exception
     */
    public static Date parseDate(String timestamp) throws ParseException {

	SimpleDateFormat sdf = new SimpleDateFormat(AppConstants.DATE_FORMAT);
	sdf.setTimeZone(TimeZone.getTimeZone(AppConstants.UTC));
	return sdf.parse(timestamp);
    }

}

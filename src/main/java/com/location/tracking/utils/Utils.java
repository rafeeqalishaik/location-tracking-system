package com.location.tracking.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.location.tracking.constants.AppConstants;

public class Utils {

    public static Date parseDate(String timestamp) throws ParseException {
	SimpleDateFormat sdf = new SimpleDateFormat(AppConstants.DATE_FORMAT);
	return sdf.parse(timestamp);
    }

}

package com.demo.test.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by hemanth.shivashankrappa on May, 2018
 */
public class DateUtil {
    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static Calendar calendar = Calendar.getInstance();


    public static String getCurrentDateAsString() {
        return format.format(calendar.getTime());
    }


    public static String getCurrentDatePlusMonthAsString(int numberOfMonths) {
        calendar.add(Calendar.MONTH,numberOfMonths);
        return format.format(calendar.getTime());
    }
}

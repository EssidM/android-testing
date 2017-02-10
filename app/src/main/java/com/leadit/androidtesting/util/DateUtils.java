package com.leadit.androidtesting.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class DateUtils {

    private static final SimpleDateFormat DISPLAY;
    private static final SimpleDateFormat DISPLAY_SHORT;

    private static final long SECOND_MILLISECONDS = 1000l;
    private static final long MINUTE_MILLISECONDS =
            SECOND_MILLISECONDS * 60;
    private static final long HOUR_MILLISECONDS =
            MINUTE_MILLISECONDS * 60;
    public static final long DAY_MILLISECONDS =
            HOUR_MILLISECONDS * 24;

    static {
        //Use 12 or 24 hour time depending on device config.
        DISPLAY = new SimpleDateFormat(
                "EEEE, dd MMMM yyyy",
                Locale.getDefault());
        DISPLAY_SHORT = new SimpleDateFormat("EEE",
                Locale.getDefault());
        DISPLAY.setTimeZone(TimeZone.getDefault());
        DISPLAY_SHORT.setTimeZone(TimeZone.getDefault());
    }

    public static Date epocSecondsToDate(long epocSeconds) {
        Calendar c = Calendar.
                getInstance(TimeZone.getTimeZone("UTC"));
        c.setTimeInMillis(epocSeconds * 1000);
        return c.getTime();
    }

    public static String dateToDayDateString(Date date,
                                              boolean useShortFormat) {
        if (useShortFormat) {
            return DISPLAY_SHORT.format(date).toUpperCase();
        } else {
            return DISPLAY.format(date).toUpperCase();
        }
    }

    public static String epocSecondsToDisplayDateTimeString(long epocSeconds) {
        Date d = epocSecondsToDate(epocSeconds);
        return dateToDayDateString(d, false);
    }

}
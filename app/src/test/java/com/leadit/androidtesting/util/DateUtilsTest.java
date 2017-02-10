package com.leadit.androidtesting.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Mohamed Essid on 10/02/2017.
 */

public class DateUtilsTest {
    /**
     * tests epoc to date conversion, and checks the day of the test date
     */
    @Test
    public void testDateUtilFormat() {

        long epoc = 1446885450; //7th Nov 2015

        ///test EPOC to date conversion
        Date date = DateUtils.epocSecondsToDate(epoc);
        Assert.assertEquals("failed time millis conversion : ", date.getTime(), epoc * 1000);

        //if EPOC conversion succeeds , test if the tested date gives a correct DAY.
        String day = DateUtils.dateToDayDateString(date, true);
        Assert.assertEquals("day is wrong ", "SAM.", day);
    }
}

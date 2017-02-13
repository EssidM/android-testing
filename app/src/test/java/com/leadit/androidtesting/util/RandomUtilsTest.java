package com.leadit.androidtesting.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.hamcrest.Matchers.is;

/**
 * RandomUtils test class
 *
 * @author Mohamed Essid on 10/02/2017.
 */

public class RandomUtilsTest {

    /**
     * tests random string method
     */
    @Test
    public void testRandomString() {
        int expectedSize = 10;
        String random = RandomUtils.randomString(expectedSize);

        //verify that the generated string's length is as expected
        Assert.assertEquals("error in generating string length", expectedSize, random.length());

        //verify that string is alphabetic
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Assert.assertThat(String.format("%s is not alphabetic", random), pattern.matcher(random).matches(), is(true));
    }
}

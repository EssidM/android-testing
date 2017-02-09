package com.leadit.androidtesting.util;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * EmailValidator unit tests class
 *
 * @author Mohamed Essid on 09/02/2017.
 */

public class EmailValidatorTest {


    @Test
    public void testIsEmailValid() {
        String[] validEmails = {"mkyong@yahoo.com",
                "mkyong-100@yahoo.com", "mkyong.100@yahoo.com",
                "mkyong111@mkyong.com", "mkyong-100@mkyong.net",
                "mkyong.100@mkyong.com.au", "mkyong@1.com",
                "mkyong@gmail.com.com", "mkyong+100@gmail.com",
                "mkyong-100@yahoo-test.com"};

        for (String email : validEmails) {
            Assert.assertThat(String.format("Valid email test failed for %s ", email), EmailValidator.get().isValidEmail(email), is(true));
        }
    }


    /**
     * invalid email test
     */
    @Test
    public void InvalidEmailTest() {
        String[] invalidEmails = {"mkyong", "mkyong@.com.my",
                "mkyong123@gmail.a", "mkyong123@.com", "mkyong123@.com.com",
                ".mkyong@mkyong.com", "mkyong()*@gmail.com", "mkyong@%*.com",
                "mkyong..2002@gmail.com", "mkyong.@gmail.com",
                "mkyong@mkyong@gmail.com", "mkyong@gmail.com.1a"};

        for (String email : invalidEmails) {
            Assert.assertThat(String.format("Invalid email test failed for %s ", email), EmailValidator.get().isValidEmail(email), is(false));
        }
    }
}

package com.leadit.androidtesting.util;

import java.util.regex.Pattern;

/**
 * @author Mohamed Essid on 09/02/2017.
 */

public class EmailValidator {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    private static EmailValidator sInstance;

    private Pattern mPattern;

    /**
     * private constructor to insure singleton
     * <p>
     * use {@link EmailValidator#get()} to get instance
     */
    private EmailValidator() {
        mPattern = Pattern.compile(EMAIL_PATTERN);
    }

    /**
     * return an unique instance of {@link EmailValidator}
     * <p>
     * use this method wherever you want to get instance of  {@link EmailValidator}
     *
     * @return
     */
    public static EmailValidator get() {
        if (sInstance == null) {
            sInstance = new EmailValidator();
        }

        return sInstance;
    }

    /**
     * checks the validity of the given mail using EMAIL_PATTERN pattern
     *
     * @param email
     * @return
     */
    public boolean isValidEmail(String email) {
        return mPattern.matcher(email).matches();

    }
}

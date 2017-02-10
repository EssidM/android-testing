package com.leadit.androidtesting.util;

import android.util.Log;

import java.util.Random;

import timber.log.Timber;

/**
 * Random values generator utility class
 *
 * @author Mohamed Essid on 10/02/2017.
 */
public class RandomUtils {


    /**
     * generates a random string
     *
     * @param length of the generated string
     * @return
     */
    public static String randomString(int length) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        char[] result = new char[length];
        for (int i = 0; i < result.length; i++) {
            result[i] = alphabet.charAt(random.nextInt(alphabet.length() - 1));
        }

        String randomString = String.copyValueOf(result);
        Timber.d("Random generated string %s", randomString);
        return randomString;
    }
}

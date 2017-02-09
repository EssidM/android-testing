package com.leadit.androidtesting.util;

import android.content.Context;
import android.support.annotation.NonNull;

import com.leadit.androidtesting.R;

/**
 * Application resources (String etc..) util class
 * provides static methods to get
 *
 * @author Mohamed Essid on 09/02/2017.
 */

public class AppResourcesUtil {


    /**
     * returns the application name
     *
     * @param context
     * @return
     */
    public static String getAppName(@NonNull Context context) {
        return context.getString(R.string.app_name);
    }
}

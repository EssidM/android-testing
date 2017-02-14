package com.leadit.androidtesting.matcher;

import android.os.IBinder;
import android.support.test.espresso.Root;
import android.view.WindowManager;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * ToastMatcher
 * <p>
 * identifies a toast
 *
 * @author Mohamed Essid on 14/02/2017.
 */

public class ToastMatcher extends TypeSafeMatcher<Root> {

    @Override
    protected boolean matchesSafely(Root root) {
        int type = root.getWindowLayoutParams().get().type;
        if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
            IBinder windowToken = root.getDecorView().getWindowToken();
            IBinder apiToken = root.getDecorView().getApplicationWindowToken();
            if (windowToken == apiToken) {
                //means that the window isn't contained by any other window
                return true;
            }
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("is toast");
    }
}

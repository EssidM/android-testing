package com.leadit.androidtesting.matcher;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static android.support.test.espresso.intent.Checks.checkArgument;
import static android.support.test.espresso.intent.Checks.checkNotNull;
import static org.hamcrest.CoreMatchers.is;

/**
 * utilities class that contains all custom matchers
 *
 * @author Mohamed Essid on 16/02/2017.
 */

public class CustomMatchers {

    /**
     * check if an {@link EditText} has the expected hint
     *
     * @param itemTextHint the expected item hint
     * @return
     */
    public static Matcher<View> withItemHint(String itemTextHint) {
        //use preconditions to fail fast when a test when a test is creating an invalid matcher
        checkArgument(!TextUtils.isEmpty(itemTextHint));

        return withItemHint(is(itemTextHint));

    }

    /**
     * check if an {@link EditText} has the expected hint
     *
     * @param matcherText text matcher
     * @return
     */
    private static Matcher<View> withItemHint(final Matcher<String> matcherText) {
        // use preconditions to fail fast when a test is creating an invalid matcher.
        checkNotNull(matcherText);

        return new BoundedMatcher<View, EditText>(EditText.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("with item hint: " + matcherText);
            }

            @Override
            protected boolean matchesSafely(EditText item) {
                return matcherText.matches(item.getHint().toString());
            }
        };
    }
}

package com.leadit.androidtesting;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import com.leadit.androidtesting.util.Constants;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Second activity class
 *
 * @author Mohamed Essid on 13/02/2017.
 */
public class SecondActivityTest {

    //launch activity is set to false here to prevent that the activity is started automatically
    //when test is launched
    @Rule
    public ActivityTestRule<SecondActivity> mRule =
            new ActivityTestRule<>(SecondActivity.class, true, false);

    @Test
    public void testExtraTextDisplay() {
        //prepare intent with the extra string
        Intent intent = new Intent();
        intent.putExtra(Constants.IntentParams.INPUT, "Hello");
        mRule.launchActivity(intent);

        //verify that the string passed is displayed
        onView(withId(R.id.second_result_view)).check(matches(withText("Hello")));
    }
}
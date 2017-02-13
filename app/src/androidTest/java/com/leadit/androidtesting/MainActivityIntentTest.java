package com.leadit.androidtesting;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.leadit.androidtesting.util.Constants;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * MainActivity intent test
 * tests if activity swicth intent is well configured
 *
 * @author Mohamed Essid on 13/02/2017.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityIntentTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule
            = new IntentsTestRule<>(MainActivity.class);


    /**
     * perform init operations need in this test case
     * and tests activity switch intent
     */
    @Test
    public void testActivitySwitchIntent() {

        String expected = "HELLO";

        //set text to input to be passed to SecondActivity
        onView(withId(R.id.main_input)).perform(ViewActions.typeText(expected));

        ViewInteraction btnSwitch = onView(withId(R.id.main_btn_switch));
        //checks if the switch button exists
        btnSwitch.check(matches(notNullValue()));

        //checks the button test value
        btnSwitch.check(matches(withText(R.string.switch_activity)));

        //preform click on button to switch activity
        btnSwitch.perform(ViewActions.click());

        //verifies that the correct intent is called for the SecondActivity
        intended(toPackage(SecondActivity.class.getPackage().getName()));

        //verifies that extra string passed equals to expected
        intended(hasExtra(Constants.IntentParams.INPUT, expected));

    }
}

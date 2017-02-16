package com.leadit.androidtesting;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.leadit.androidtesting.matcher.HintMatcher;
import com.leadit.androidtesting.matcher.ToastMatcher;
import com.leadit.androidtesting.util.Constants;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Checks.checkArgument;
import static android.support.test.espresso.intent.Checks.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;

/**
 * {@link MainActivity} test class with Espresso framework
 *
 * @author Mohamed Essid on 13/02/2017.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule
            = new ActivityTestRule<>(MainActivity.class);

    /**
     * tests text change operation on main activity
     */
    @Test
    public void testTextChange() {
        //init input text with a string HELLO
        onView(withId(R.id.main_input)).perform(ViewActions.typeText("HELLO"), ViewActions.closeSoftKeyboard());

        //performing a click in order to change text
        onView(withId(R.id.main_btn_change_text)).perform(click());

        //check if the current value has changed
        // and verify that it's value corresponds to the expected one
        onView(withId(R.id.main_input)).check(matches(withText(Constants.TEST_TEXT)));
    }

    /**
     * tests if text set on MainActivity was displayed on result view in SecondActivity
     */
    @Test
    public void testTextChangeSecondActivity() {
        //setting text to input in MainActivity
        onView(withId(R.id.main_input)).perform(ViewActions.typeText("new text"));

        //performing a click in MainActivity to open SecondActivity with the text set in the
        //previous operation
        onView(withId(R.id.main_btn_switch)).perform(click());

        //Now we are in SecondActivity, check that the text received matches the expected
        onView(withId(R.id.second_result_view)).check(matches(withText("new text")));
    }

    /**
     * test show toast
     */
    @Test
    public void testShowToast() {
        onView(withId(R.id.main_btn_toast)).perform(click());
        onView(withText(R.string.toast)).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    /**
     * tests that async task is running totally and updated the view
     */
    @Test
    public void testAsyncTask() {
        onView(withId(R.id.main_btn_async)).perform(click());
        onView(withId(R.id.main_txt_task_status)).check(matches(withText(R.string.done)));
    }

    @Test
    public void testInputHint() {
        //checks that's main input has hint equals to expected value
        onView(withId(R.id.main_input)).check(matches(withItemHint("Enter input to be passed to nex activity")));
    }

    public static Matcher<View> withItemHint(String itemTextHint) {
        //use preconditions to fail fast when a test when a test is creating an invalid matcher
        checkArgument(!TextUtils.isEmpty(itemTextHint));

        return withItemHint(is(itemTextHint));

    }

    private static Matcher<View> withItemHint(final Matcher<String> matcherText) {
        checkNotNull(matcherText);

       return new HintMatcher(matcherText);
    }
}

# Android Testing
This project will cover samples or Android Test cases : <b>unit</b> and <b>instrumentation</b> tests.
For this we used some samples and tutorials like :

* [Official Android documentation](https://developer.android.com/training/testing/start/index.html)
* [Medium - the basis of Unit & intrumented Tests](https://medium.com/@ali.muzaffar/the-basics-of-unit-and-instrumentation-testing-on-android-7f3790e77bd#.a1yp8o8g2)
* [**myKong** Email Validation Tutorial](https://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/)
* [Android user interface testing with **Espresso**](http://www.vogella.com/tutorials/AndroidTestingEspresso/article.html#espresso_usageintroduction)
* [QA Automated - Test Toast Message] (http://www.qaautomated.com/2016/01/how-to-test-toast-message-using-espresso.html)
## Case 1: Android Email Validation (Unit Testing)
It's about unit testing based on JUnit 4.12
Create a class named app/src/main/.../<b>EmailValidator</b> & app/src/test/.../<b>EmailValidatorTest</b>
that will contains methods that test if Email validation is working properly.

```java
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

    /**
    * valid emails test
    */
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
     * invalid emails test
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
```

## Case 2 : DateUtils Test - Unit tests (Medium tutorial)
* Create utility class named **DateUtils**, for date conversion & format.
* Corresponding test class **DateUtilsTest** which test time conversion & day.

Don't forget to adapt expected day value according to you **Default Locale** (I used SAM. , because my default locale is FR).

##### DateUtil.java
```java
public class DateUtils {

    private static final SimpleDateFormat DISPLAY;
    private static final SimpleDateFormat DISPLAY_SHORT;

    private static final long SECOND_MILLISECONDS = 1000l;
    private static final long MINUTE_MILLISECONDS =
            SECOND_MILLISECONDS * 60;
    private static final long HOUR_MILLISECONDS =
            MINUTE_MILLISECONDS * 60;
    public static final long DAY_MILLISECONDS =
            HOUR_MILLISECONDS * 24;

    static {
        //Use 12 or 24 hour time depending on device config.
        DISPLAY = new SimpleDateFormat(
                "EEEE, dd MMMM yyyy",
                Locale.getDefault());
        DISPLAY_SHORT = new SimpleDateFormat("EEE",
                Locale.getDefault());
        DISPLAY.setTimeZone(TimeZone.getDefault());
        DISPLAY_SHORT.setTimeZone(TimeZone.getDefault());
    }

    public static Date epocSecondsToDate(long epocSeconds) {
        Calendar c = Calendar.
                getInstance(TimeZone.getTimeZone("UTC"));
        c.setTimeInMillis(epocSeconds * 1000);
        return c.getTime();
    }

    public static String dateToDayDateString(Date date,
                                              boolean useShortFormat) {
        if (useShortFormat) {
            return DISPLAY_SHORT.format(date).toUpperCase();
        } else {
            return DISPLAY.format(date).toUpperCase();
        }
    }

    public static String epocSecondsToDisplayDateTimeString(long epocSeconds) {
        Date d = epocSecondsToDate(epocSeconds);
        return dateToDayDateString(d, false);
    }

}
```
#####DateUtilTest
This the corresponding test class which will tests date conversion & format.
```java
public class DateUtilsTest {
    /**
     * tests epoc to date conversion, and checks the day of the test date
     */
    @Test
    public void testDateUtilFormat() {

        long epoc = 1446885450; //7th Nov 2015

        ///test EPOC to date conversion
        Date date = DateUtils.epocSecondsToDate(epoc);
        Assert.assertEquals("failed time millis conversion : ", date.getTime(), epoc * 1000);

        //if EPOC conversion succeeds , test if the tested date gives a correct DAY.
        String day = DateUtils.dateToDayDateString(date, true);
        Assert.assertEquals("day is wrong ", "SAM.", day);
    }
}
```
## Case 3: Unit test : RandomUtils
In this case, we made a class **RandomUtils** which has a method named **randomString(int length)** that returns
a random alphabetic string which length that corresponds to the parameter.

Below the final RandomUtils & RandomUtilsTest classes
* RandomUtils
```java
package com.leadit.androidtesting.util;

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
```
* RandomUtilsTest
```java
package com.leadit.androidtesting.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.hamcrest.Matchers.is;

/**
 *Random Utils test class
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
```

## Case 4: Unit Test with Mockito framework

From [Google official documentation</b>](https://developer.android.com/training/testing/unit-testing/local-unit-tests.html#build).

We created a class named StringUtil which contains a method named <b>getAppName(Context)</b>.
To be able to test this method, we need a mock context. that's why we used Mockito framework.

Note to :
* add Mockito dependency in app/build.gradle : <b>testCompile 'org.mockito:mockito-core:1.10.19'</b>
* add <b>@RunWith(MockitoJUnitRunner.class)</b> to run with Mockito
* add <b>@Mock</b> before context declaration
* simulate getting string from context with R.string.app_name <b>Mockito.when(mMockContext.getString(R.string.app_name)).thenReturn(FAKE_STRING)</b>


```java
package com.leadit.androidtesting;

import android.content.Context;

import com.leadit.androidtesting.util.StringUtil;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;

/**
 * StringUtil test class
 *
 * @author Mohamed Essid on 09/02/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class StringUtilTest {

    /**
     * a fake string used in tests
     */
    public static final String FAKE_STRING = "AndroidTesting";

    /**
     * mock context
     */
    @Mock
    Context mMockContext;

    /**
     * tests read a string from a mock context
     */
    @Test
    public void testReadStringFromContext() {
        // Given a mocked Context injected into the object under test...
        Mockito.when(mMockContext.getString(R.string.app_name)).thenReturn(FAKE_STRING);

        // ...when the string is returned from the object under test...
        String result = StringUtil.getAppName(mMockContext);

        // ...then the result should be the expected one.
        Assert.assertThat(result, is(FAKE_STRING));

    }


}
```

## Case 5: Instrumentation Test (Espresso) - Medium tutorial
Covers some Android Instrumentation  tutorials made by [Vogella](http://www.vogella.com/tutorials/AndroidTestingEspresso/article.html#espresso_introduction)

* As said in the tutorial, "Espresso is a testing framework for Android to make it easy to write reliable user interface tests."

* It relies on 3 components basically:
  * **ViewMatchers** finding a view in current view hierarchy.
  * **ViewActions** performing actions on view.
  * **ViewAssertions** asserting state is the following.

Library setup, Gradle dependencies and devices configuration (more info [here](http://www.vogella.com/tutorials/AndroidTestingEspresso/article.html#espresso_introduction))

* In this test class MainActivityTest, we'll test 2 features:
  * test text change on **MainActivity** after button click
  * check that the value from input is well passed to SecondActivity

Below you can check the final MainActivityTest class:
```java
package com.leadit.androidtesting;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.leadit.androidtesting.util.Constants;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * {@link MainActivity} test class with Espresso framework
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
        onView(withId(R.id.main_btn_change_text)).perform(ViewActions.click());

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
        onView(withId(R.id.main_btn_switch)).perform(ViewActions.click());

        //Now we are in SecondActivity, check that the text received matches the expected
        onView(withId(R.id.second_result_view)).check(matches(withText("new text")));
    }
}
```

## Case 6: Manual Rule's activity Launch - SecondActivity Test (Espresso)
In this case, we will test if ```SecondActivity``` displays correctly the string passed in intent.
For this,
  * we removed the automatic launch of the activity under test (SecondActivity) by setting
  the flag ```launchActivity``` to ```false``` as below

  * Intent now is configured before launching activity from the rule as defined below, in the final
```SecondActivityTest.java```

```java
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
```

## case 7 : MainActivity switch test : Intent test with Espresso

Test if the MainActivity is correctly configuring the intent in order to launch the SecondActivity.
For this, we've tested:
* **Intent Package** : to verify that the **intent's package*** corresponds to ```SecondActivity``'s package
* **Intent Param** : to verify the intent param ```INPUT``` contains the expected value.

In this test case we used ```android.support.test.espresso.intent.rule.IntentsTestRuleÌnetentsTestRule```

The test case was implemented in MainActivityIntentTest as below:
```java
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
```

## Case 8 : Test Toast on MainActivity (Espresso
In MainActivity, we've added a button that shows a Toast when clicked.
In Order to test that the toast is correctly shown we need :

  * in MainActivity , add a button and show a toast on click
  ```java
  public void onClick(View view) {
          switch (view.getId()) {
              //...
              case R.id.main_btn_toast:
                  Toast.makeText(this, R.string.toast, Toast.LENGTH_SHORT).show();
                  break;
          }
      }
  ```
  * ```ToastMatcher``` which identifies a Toast.
  ```java
/**
 * ToastMatcher
 * <p>
 * identifies a toast
 *
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
  ```
  * In **MainActivityTest** add ```testToastShown()`` which is a test method to check the toast is correctly displayed.
  ```java
    /**
     * test show toast
     */
    @Test
    public void testShowToast() {
        onView(withId(R.id.main_btn_toast)).perform(ViewActions.click());
        onView(withText(R.string.toast)).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }
  ````

  * Now you can run the the test which should be passed.

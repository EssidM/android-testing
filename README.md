# Android Testing
This project will cover samples or Android Test cases : <b>unit</b> and <b>instrumentation</b> tests.
For this we used some samples and tutorials like :

* [Official Android documentation](https://developer.android.com/training/testing/start/index.html)
* [myKong Email Validation Tutorial](https://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/)

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
```

## Case 2: Unit Test with Mockito framework

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

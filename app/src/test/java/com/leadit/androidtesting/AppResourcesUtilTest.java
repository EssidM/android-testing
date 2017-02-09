package com.leadit.androidtesting;

import android.content.Context;

import com.leadit.androidtesting.util.AppResourcesUtil;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;

/**
 * AppResourcesUtil test class
 *
 * @author Mohamed Essid on 09/02/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class AppResourcesUtilTest {

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
        String result = AppResourcesUtil.getAppName(mMockContext);

        // ...then the result should be the expected one.
        Assert.assertThat(result, is(FAKE_STRING));

    }


}

package com.leadit.androidtesting.matcher;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static android.support.test.internal.util.Checks.checkNotNull;

/**
 * Hint matcher on EditText
 *
 * @author Mohamed Essid on 15/02/2017.
 */

public class HintMatcher extends BoundedMatcher<View, EditText> {

    private Matcher<String> text;

    public HintMatcher(Matcher<String> text) {
        super(EditText.class);
        this.text = text;
    }

    @Override
    protected boolean matchesSafely(EditText item) {
        checkNotNull(item);
        return text.matches(item.getHint().toString());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("has hint");
    }

}

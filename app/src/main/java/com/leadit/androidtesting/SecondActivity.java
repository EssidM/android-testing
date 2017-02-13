package com.leadit.androidtesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.leadit.androidtesting.util.Constants;

public class SecondActivity extends AppCompatActivity {


    private TextView mResultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //getting input value from intent extras
        String input = getIntent().getExtras().getString(Constants.IntentParams.INPUT);

        mResultText = (TextView) findViewById(R.id.second_result_view);
        mResultText.setText(input);
    }
}

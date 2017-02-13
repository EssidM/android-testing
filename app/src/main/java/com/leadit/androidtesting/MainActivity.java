package com.leadit.androidtesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.leadit.androidtesting.util.Constants;
import com.leadit.androidtesting.util.RandomUtils;

public class MainActivity extends AppCompatActivity {


    private EditText mInputEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputEdit = (EditText) findViewById(R.id.main_input);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_change_text:
                mInputEdit.setText(Constants.TEST_TEXT);
            break;

            case R.id.main_btn_switch:
                Intent intent = new Intent(this, SecondActivity.class);
                intent.putExtra(Constants.IntentParams.INPUT, mInputEdit.getText().toString());
                this.startActivity(intent);
                break;
        }
    }
}

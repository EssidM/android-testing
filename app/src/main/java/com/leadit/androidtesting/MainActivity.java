package com.leadit.androidtesting;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.leadit.androidtesting.util.Constants;
import com.leadit.androidtesting.util.RandomUtils;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {


    private EditText mInputEdit;

    /**
     * async task progress
     */
    private ProgressDialog mProgress;

    /**
     * task status text
     */
    private TextView mTaskStatusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputEdit = (EditText) findViewById(R.id.main_input);

        mTaskStatusText = (TextView) findViewById(R.id.main_txt_task_status);
        mProgress = getProgress(this);

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

            case R.id.main_btn_toast:
                Toast.makeText(this, R.string.toast, Toast.LENGTH_SHORT).show();
                break;

            case R.id.main_btn_async:
                new Task().execute(RandomUtils.randomString(10));
                break;
        }
    }


    /**
     * configures and returns a progress diaog
     *
     * @param context
     */
    private ProgressDialog getProgress(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle(R.string.async);
        progressDialog.setMessage(context.getString(R.string.task_runnning));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        return progressDialog;
    }

    /**
     * fake async task
     */
    private class Task extends AsyncTask<String, Void, String> {

        private int seconds = 5;

        @Override
        protected void onPreExecute() {
            mProgress.setMax(seconds);
            mProgress.show();
            mTaskStatusText.setText(R.string.task_runnning);
        }

        @Override
        protected String doInBackground(String... params) {
            int i = 0;
            String taskName = params[0];
            do {
                i++;
                Timber.d("Task %s is running @ %s", taskName, i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mProgress.setProgress(i);
            } while (i < seconds);

            return "Long running asynchronous task";
        }

        @Override
        protected void onPostExecute(String s) {
            mProgress.cancel();
            mTaskStatusText.setText(R.string.done);
        }
    }

}

package com.gvip.snrb.sdk.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gvip.snrb.sdk.R;
import com.gvip.snrb.sdk.api.ApiClient;
import com.gvip.snrb.sdk.callbacks.ICallbackEvent;
import com.gvip.snrb.sdk.tasks.HttpGetTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pta on 9/4/2017.
 */

public class SearchNumbersActivity extends AppCompatActivity {

    private static final String TAG = SearchNumbersActivity.class.getSimpleName();

    private Activity mActivityThis;

    private EditText mAreaCodeEditText;
    private Button mSearchButton;
    private ProgressBar mProgressBar;

    private String mAreaCode;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        mActivityThis = this;

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mAreaCodeEditText = (EditText) findViewById(R.id.edit_text_area_code);
        mSearchButton = (Button) findViewById(R.id.button_search_area_code);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAreaCode = mAreaCodeEditText.getText().toString();
                if(mAreaCode != null && mAreaCode != ""){
                    if(mAreaCode.length() != 3){
                        Toast.makeText(getApplicationContext(), "Area code must be 3 digits", Toast.LENGTH_SHORT).show();
                    }else {
                        mAreaCode = mAreaCodeEditText.getText().toString();
                        makeNumbersRequest();
                    }
                }else {
                    mActivityThis.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Please enter an area code", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void makeNumbersRequest(){

        mProgressBar.setVisibility(View.VISIBLE);

        HttpGetTask task = new HttpGetTask(new ICallbackEvent<ArrayList<String>, Exception>() {
            @Override
            public void onSuccess(ArrayList<String> results) {
                mProgressBar.setVisibility(View.GONE);
                Intent intent = new Intent(SearchNumbersActivity.this, SelectNumberActivity.class);
                intent.putStringArrayListExtra("search_numbers_results", results);
                startActivity(intent);
            }

            @Override
            public void onError(final Exception error) {
                mActivityThis.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Error getting numbers: "+ error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        task.execute(ApiClient.buildSearchNumbersUrl(mAreaCode));

    }
}

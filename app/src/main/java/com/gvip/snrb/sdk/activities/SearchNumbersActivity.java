package com.gvip.snrb.sdk.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gvip.snrb.sdk.R;
import com.gvip.snrb.sdk.api.ApiClient;
import com.gvip.snrb.sdk.tasks.HttpGetTask;
import com.gvip.snrb.sdk.utils.HashGenerator;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by pta on 9/4/2017.
 */

public class SearchNumbersActivity extends AppCompatActivity {

    private static final String TAG = SearchNumbersActivity.class.getSimpleName();

    private EditText mAreaCodeEditText;
    private Button mSearchButton;
    private ProgressBar mProgressBar;

    private String mAreaCode;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
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
                    Toast.makeText(getApplicationContext(), "Please enter an area code", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void makeNumbersRequest(){
        ApiClient apiClient = ApiClient.getInstance();
        final String requestId = UUID.randomUUID().toString();
        String hash = HashGenerator.bin2hex(HashGenerator.createSHA256Hash(apiClient.getClientId() + mAreaCode + requestId + apiClient.getApiSecret()));

        String url = String.format("http://dev-commercial-api.azurewebsites.net/api/numbers/list/%s/areaCode/%s/requestId/%s/hash/%s",
                apiClient.getClientId(),
                mAreaCode,
                requestId,
                hash);

        HttpGetTask task = new HttpGetTask(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                try {
                    JSONArray jsonArray = new JSONArray(result);
                    ArrayList<String> results = new ArrayList<>();

                    for(int i = 0; i < jsonArray.length(); i++){
                        results.add(jsonArray.getString(i));
                    }

                    Intent intent = new Intent(SearchNumbersActivity.this, SelectNumberActivity.class);
                    intent.putStringArrayListExtra("search_numbers_results", results);
                    startActivity(intent);
                } catch (JSONException ex) {
                    Log.e(TAG, ex.getMessage());
                }

                mProgressBar.setVisibility(View.GONE);


            }
        };
        task.execute(url);

    }
}

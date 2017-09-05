package com.gvip.snrb.sdk.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gvip.snrb.sdk.api.ApiClient;
import com.gvip.snrb.sdk.tasks.HttpGetTask;
import com.gvip.snrb.sdk.utils.HashGenerator;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * Created by pta on 9/4/2017.
 */

public class SearchNumbersActivity extends AppCompatActivity {

    private static final String TAG = SearchNumbersActivity.class.getSimpleName();

    private String mAreaCode = "732";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeNumbersRequest();
    }

    private void makeNumbersRequest(){
        ApiClient apiClient = ApiClient.getInstance(this);
        String requestId = UUID.randomUUID().toString();
        String hash = HashGenerator.bin2hex(HashGenerator.createSHA256Hash(apiClient.getClientId() + mAreaCode + requestId + apiClient.getApiSecret()));

        String url = String.format("http://dev-commercial-api.azurewebsites.net/api/numbers/list/%s/areaCode/%s/requestId/%s/hash/%s",
                apiClient.getClientId(),
                mAreaCode,
                requestId,
                hash);

       HttpGetTask task = new HttpGetTask();
        try {
            String result = task.execute(url).get();
        } catch (InterruptedException e) {
            Log.e(TAG, e.getMessage());
        } catch (ExecutionException e) {
            Log.e(TAG, e.getMessage());
        }
    }
}

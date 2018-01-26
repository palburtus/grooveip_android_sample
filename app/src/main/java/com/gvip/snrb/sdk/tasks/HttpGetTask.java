package com.gvip.snrb.sdk.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.gvip.snrb.sdk.callbacks.ICallbackEvent;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by pta on 9/4/2017.
 */

public class HttpGetTask extends AsyncTask<String, Void, String> {

    private static final String TAG = HttpGetTask.class.getSimpleName();
    private static final String HTTP_GET = "GET";
    private static final int TIMEOUT = 10000;

    private ICallbackEvent mCallback;

    public HttpGetTask(ICallbackEvent callback){
        mCallback = callback;
    }

    @Override
    protected String doInBackground(String... params) {
        String param = params[0];
        String result = null;

        try {
            URL url = new URL(param);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(HTTP_GET);
            connection.setReadTimeout(TIMEOUT);
            connection.setConnectTimeout(TIMEOUT);

            connection.connect();

            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());

            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();

            String buffer = null;
            while((buffer = reader.readLine()) != null){
                stringBuilder.append(buffer);
            }

            reader.close();
            streamReader.close();
            result = stringBuilder.toString();

        } catch (MalformedURLException e) {
            Log.e(TAG, e.getMessage());
            mCallback.onError(e);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            mCallback.onError(e);
        }

        return result;
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

            mCallback.onSuccess(results);

        } catch (JSONException ex) {
            Log.e(TAG, ex.getMessage());
            mCallback.onError(ex);
        } catch (Exception ex) {
            mCallback.onError(ex);
        }
    }
}

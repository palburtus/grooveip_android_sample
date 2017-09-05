package com.gvip.snrb.sdk.tasks;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by pta on 9/4/2017.
 */

public class HttpGetTask extends AsyncTask<String, Void, String> {

    private static final String TAG = HttpGetTask.class.getSimpleName();
    private static final String HTTP_GET = "GET";
    private static final int TIMEOUT = 10000;

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
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }


        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}

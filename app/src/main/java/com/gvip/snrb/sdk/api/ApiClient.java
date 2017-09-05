package com.gvip.snrb.sdk.api;

import android.content.Context;

import com.gvip.snrb.sdk.R;

/**
 * Created by pta on 9/4/2017.
 */

public class ApiClient {

    private String mApiSecret;
    private String mClientId;

    private static ApiClient mApiClient;

    private ApiClient(){ }

    public static ApiClient getInstance(Context context){
        if(mApiClient != null){
            return mApiClient;
        }else {
            mApiClient = new ApiClient();
            if(context != null) {
                mApiClient.mApiSecret = context.getResources().getString(R.string.api_secret);
                mApiClient.mClientId = context.getResources().getString(R.string.customer_id);
                return mApiClient;
            }else {
                return null;
            }
        }
    }

    public String getApiSecret(){
        return mApiSecret;
    }

    public String getClientId(){
        return mClientId;
    }






}

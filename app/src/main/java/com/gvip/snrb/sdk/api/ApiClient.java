package com.gvip.snrb.sdk.api;

/**
 * Created by pta on 9/4/2017.
 */

public class ApiClient {

    private static final String API_SECRET = "";
    private static final String CLIENT_ID = "";

    private static ApiClient mApiClient;

    private ApiClient(){ }

    public static ApiClient getInstance(){
        if(mApiClient != null){
            return mApiClient;
        }else {
            return mApiClient = new ApiClient();
        }
    }

    public String getApiSecret(){
        return API_SECRET;
    }

    public String getClientId(){
        return CLIENT_ID;
    }






}

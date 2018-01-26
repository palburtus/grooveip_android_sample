package com.gvip.snrb.sdk.api;

import com.gvip.snrb.sdk.utils.HashGenerator;

import java.util.UUID;

/**
 * Created by pta on 9/4/2017.
 */

public class ApiClient {

    private static final String API_SECRET = "myapisecret";
    private static final String CLIENT_ID = "1";
    private static final String BASE_URL = "http://dev-commercial-api.azurewebsites.net/api";

    public static String buildSearchNumbersUrl(String areaCode){

        String requestId = getRequestId();
        String hash = HashGenerator.bin2hex(HashGenerator.createSHA256Hash(String.format("%s%s%s%s", CLIENT_ID, areaCode, requestId, API_SECRET)));

        String url = String.format("%s/numbers/list/%s/areaCode/%s/requestId/%s/hash/%s",
                BASE_URL,
                CLIENT_ID,
                areaCode,
                requestId,
                hash);

        return url;
    }

    private static String getRequestId(){
        return UUID.randomUUID().toString();
    }





}

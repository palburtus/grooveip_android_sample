package com.gvip.snrb.sdk;

import com.gvip.snrb.sdk.utils.HashGenerator;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;


public class HashGeneratorTests {

    private static final String API_SECRET = "";
    private static final String CLIENT_ID = "";

    @Test
    public void createSHA256_getNumbersParams_returnsString() throws Exception {
        String requestId = "eda96875-af1d-4855-b529-57e86ea7cf75";
        //String requestId = UUID.randomUUID().toString();
        String hash = HashGenerator.bin2hex(HashGenerator.createSHA256Hash(CLIENT_ID + "732" + requestId + API_SECRET));

        Assert.assertEquals("8c1172ffbcbd3df5e483873d4340deb40f0629b5ac67352cbc87d7be5453c2d3", hash);

    }

    @Test
    public void createSHA256_selectNumbersParams_returnsString() throws Exception {
        String requestId = "eda96875-af1d-4855-b529-57e86ea7cf75";
        //String requestId = UUID.randomUUID().toString();
        String hash = HashGenerator.bin2hex(HashGenerator.createSHA256Hash(CLIENT_ID + "+17324005446" + "732" + requestId + API_SECRET));

        Assert.assertEquals("97777ff75b0d9c03a86a8cd4143b3b44e2afaaff5f54ad7cd20ab1bcd45ace52", hash);

    }
}
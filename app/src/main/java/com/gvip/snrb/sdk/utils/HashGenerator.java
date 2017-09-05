package com.gvip.snrb.sdk.utils;

import android.util.Log;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Patrick on 9/5/2017.
 */

public class HashGenerator {

    private static final String TAG = HashGenerator.class.getSimpleName();
    private static final String HASH_ALGORITHM = "SHA-256";


    public static byte[] createSHA256Hash(String input){
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance (HASH_ALGORITHM);
            digest.reset ();
            return digest.digest(input.getBytes());
        }
        catch (NoSuchAlgorithmException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;

    }

    public static String bin2hex(byte[] data) {
        return String.format("%0" + (data.length * 2) + 'x', new BigInteger(1, data));
    }
}

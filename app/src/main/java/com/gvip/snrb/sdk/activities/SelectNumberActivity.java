package com.gvip.snrb.sdk.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Patrick on 9/14/2017.
 */

public class SelectNumberActivity extends AppCompatActivity {

    private static final String TAG = SelectNumberActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

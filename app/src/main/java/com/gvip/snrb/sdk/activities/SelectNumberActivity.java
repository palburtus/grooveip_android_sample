package com.gvip.snrb.sdk.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gvip.snrb.sdk.R;
import com.gvip.snrb.sdk.adapters.NumberListAdapter;
import com.gvip.snrb.sdk.callbacks.IOnNumberSelectedListener;

import java.util.ArrayList;

/**
 * Created by Patrick on 9/14/2017.
 */

public class SelectNumberActivity extends AppCompatActivity implements IOnNumberSelectedListener {

    private static final String TAG = SelectNumberActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;

    private ArrayList<String> mResults;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_numbers);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.results_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mResults = getIntent().getStringArrayListExtra("search_numbers_results");

        NumberListAdapter adapter = new NumberListAdapter(mResults, this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onNumberSelected(String number) {

    }
}

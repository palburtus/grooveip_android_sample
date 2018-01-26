package com.gvip.snrb.sdk.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gvip.snrb.sdk.R;

/**
 * Created by Patrick on 9/30/2017.
 */

public class NumbersInventoryActivity extends AppCompatActivity {

    private static final String TAG = NumbersInventoryActivity.class.getSimpleName();

    private TextView mNoNumbersTextView;
    private RecyclerView mRecyclerView;
    private Button mAddNumberButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_inventory);

        mNoNumbersTextView = (TextView) findViewById(R.id.no_numbers_text_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.inventory_recycler_view);
        mAddNumberButton = (Button) findViewById(R.id.add_number_button);
        mAddNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NumbersInventoryActivity.this, SearchNumbersActivity.class);
                startActivity(intent);
            }
        });

        mNoNumbersTextView.setVisibility(View.VISIBLE);
    }
}

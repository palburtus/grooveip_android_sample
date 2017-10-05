package com.gvip.snrb.sdk.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gvip.snrb.sdk.R;
import com.gvip.snrb.sdk.callbacks.IOnNumberSelectedListener;

/**
 * Created by Patrick on 9/14/2017.
 */

public class NumberViewHolder extends RecyclerView.ViewHolder {

    private View mRootView;
    private TextView mNumberTextView;

    public NumberViewHolder(View itemView) {
        super(itemView);
        mRootView = itemView;
        mNumberTextView = (TextView) itemView.findViewById(R.id.number_text_view);
    }

    public void bind(final String number, final IOnNumberSelectedListener numberSelectedListener){
        mNumberTextView.setText(number);
        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberSelectedListener.onNumberSelected(number);
            }
        });
    }
}

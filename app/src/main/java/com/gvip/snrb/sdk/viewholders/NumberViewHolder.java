package com.gvip.snrb.sdk.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gvip.snrb.sdk.R;

/**
 * Created by Patrick on 9/14/2017.
 */

public class NumberViewHolder extends RecyclerView.ViewHolder {

    private TextView mNumberTextView;

    public NumberViewHolder(View itemView) {
        super(itemView);
        mNumberTextView = (TextView) itemView.findViewById(R.id.number_text_view);
    }

    public void bind(String number){
        mNumberTextView.setText(number);
    }
}

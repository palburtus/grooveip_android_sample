package com.gvip.snrb.sdk.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gvip.snrb.sdk.R;
import com.gvip.snrb.sdk.viewholders.NumberViewHolder;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Patrick on 9/14/2017.
 */

public class NumberListAdapter extends RecyclerView.Adapter<NumberViewHolder> {

    private ArrayList<String> mItems;

    public NumberListAdapter(ArrayList<String> items){
        mItems = items;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.viewholder_number, parent, false);
        NumberViewHolder numberViewHolder = new NumberViewHolder(view);
        return numberViewHolder;
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        if(mItems == null){
            return 0;
        }else {
            return mItems.size();
        }
    }
}

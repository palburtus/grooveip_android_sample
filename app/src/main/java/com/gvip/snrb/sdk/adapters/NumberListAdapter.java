package com.gvip.snrb.sdk.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gvip.snrb.sdk.R;
import com.gvip.snrb.sdk.callbacks.IOnNumberSelectedListener;
import com.gvip.snrb.sdk.viewholders.NumberViewHolder;

import java.util.ArrayList;

/**
 * Created by Patrick on 9/14/2017.
 */

public class NumberListAdapter extends RecyclerView.Adapter<NumberViewHolder> {

    private IOnNumberSelectedListener mNumberSelectListener;
    private ArrayList<String> mItems = new ArrayList<>();

    public NumberListAdapter(IOnNumberSelectedListener numberSelectedListener){
        mNumberSelectListener = numberSelectedListener;
    }

    public NumberListAdapter(ArrayList<String> items, IOnNumberSelectedListener numberSelectedListener){
        mItems = items;
        mNumberSelectListener = numberSelectedListener;
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
        holder.bind(mItems.get(position), mNumberSelectListener);
    }

    @Override
    public int getItemCount() {
        if(mItems == null){
            return 0;
        }else {
            return mItems.size();
        }
    }

    public void addNumber(String number){
        mItems.add(number);
    }
}

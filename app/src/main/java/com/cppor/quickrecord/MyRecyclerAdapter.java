package com.cppor.quickrecord;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by willin on 2015/6/16.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    String[] mAdapter;

    private void initData() {
        mAdapter = new String[]{"1adidifif","ddgfoia2","gidgsoidgfojdgfs3","dgojidgfskjdgf4"};
    }

    public MyRecyclerAdapter() {
        initData();
    }

    public MyRecyclerAdapter(String[] data) {
        mAdapter = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (View)LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, null);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(mAdapter[position]);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mAdapter.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.txt_my);
        }
    }
}

package com.wutong.mvpsimple.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 单个viewtype 类型的通用adapter
 * Created by 吴同 on 2016/9/6 0006.
 */
public abstract class BaseSingleRVAdapter<E, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected Context mContext;
    protected ArrayList<E> mlist = new ArrayList<>();

    public BaseSingleRVAdapter(Context context) {
        mContext = context;
    }

    public void setData(ArrayList<E> list) {
        if (list != null) {
            mlist = list;
            notifyDataSetChanged();
        }
    }

    public ArrayList<E> getData() {
        return mlist;
    }

    protected abstract VH getVH(View itemView);

    @Override public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(getItemViewLayoutId(), null);
        return getVH(itemView);
    }


    @Override public void onBindViewHolder(VH holder, int position) {
        bindData(holder, mlist.get(position), position);
    }


    protected abstract int getItemViewLayoutId();

    protected abstract void bindData(VH holder, E t, int position);

    @Override public int getItemCount() {
        return mlist.size();
    }


}

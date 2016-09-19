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
public abstract class BaseRVAdapter<E, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected Context mContext;
    protected ArrayList<E> mlist = new ArrayList<>();

    public BaseRVAdapter(Context context) {
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





    @Override public int getItemCount() {
        return mlist.size();
    }

    @Override public void onBindViewHolder(VH holder, int position) {
        bindData(mContext,holder, mlist.get(position), position);
    }




    protected abstract void bindData(Context context,VH holder, E t, int position);



}

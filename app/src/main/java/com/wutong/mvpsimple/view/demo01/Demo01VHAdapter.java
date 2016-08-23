package com.wutong.mvpsimple.view.demo01;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 吴同 on 2016/8/23 0023.
 */
public class Demo01VHAdapter extends RecyclerView.Adapter<Demo01VHAdapter.MyViewHolder> {
    private final Context context;
    private ArrayList<String> strings = new ArrayList<>();

    public Demo01VHAdapter(Context context) {
        this.context = context;
        strings.add("xxxxa");
        strings.add("xxxxd");
        strings.add("xxxx");
        strings.add("xxxx");
        strings.add("xxxx");
        strings.add("xxxx");
        strings.add("xxxx");
        strings.add("xxxx");
        strings.add("xxxx");
        strings.add("xxxx");
        strings.add("xxxx");
        strings.add("xxxx");
        strings.add("xxxx");
        strings.add("xxxx");
        strings.add("xxxx");
        strings.add("xxxx");
        strings.add("xxxx1");
        strings.add("xxxx2");
    }

    @Override public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = new TextView(context);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 160));
        return new MyViewHolder(textView);
    }

    @Override public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setData(strings.get(position));
    }

    @Override public int getItemCount() {
        return strings.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }

        public void setData(String s) {
            ((TextView) itemView).setText(s);
        }
    }
}

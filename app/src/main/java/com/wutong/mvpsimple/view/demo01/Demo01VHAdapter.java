package com.wutong.mvpsimple.view.demo01;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wutong.mvpsimple.R;
import com.wutong.mvpsimple.base.adapter.BaseSingleRVAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 吴同 on 2016/8/23 0023.
 */
public class Demo01VHAdapter extends BaseSingleRVAdapter<String, Demo01VHAdapter.ViewHolder> {


    public Demo01VHAdapter(Context context) {
        super(context);
    }

    @Override protected ViewHolder getVH(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override protected int getItemViewLayoutId() {
        return R.layout.item_demo01_layout;
    }


    @Override protected void bindData(ViewHolder holder, String s, int position) {

    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.textView) TextView textView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

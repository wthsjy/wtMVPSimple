package com.wutong.mvpsimple.view.demo01.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wutong.mvpsimple.R;
import com.wutong.mvpsimple.base.adapter.BaseSingleRVAdapter;
import com.wutong.mvpsimple.data.entity.element.UserInfo;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 吴同 on 2016/8/23 0023.
 */
public class Demo01VHAdapter extends BaseSingleRVAdapter<UserInfo, Demo01VHAdapter.ViewHolder> {


    public Demo01VHAdapter(Context context) {
        super(context);
    }

    @Override protected ViewHolder getVH(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override protected int getItemViewLayoutId() {
        return R.layout.item_demo01_layout;
    }


    @Override protected void bindData(Context context, ViewHolder holder, UserInfo userInfo, int position) {
        holder.textView.setText(userInfo.getNickname());
        Picasso.with(context).load(userInfo.getAvatar()).into(holder.imageView);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.textView) TextView textView;
        @Bind(R.id.imageView) ImageView imageView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}

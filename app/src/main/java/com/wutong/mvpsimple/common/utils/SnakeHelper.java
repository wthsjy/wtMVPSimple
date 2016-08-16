package com.wutong.mvpsimple.common.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by 吴同 on 2016/8/12 0012.
 */
public class SnakeHelper {
    private final Context mContext;

    public SnakeHelper(@NonNull Context context) {
        mContext = context.getApplicationContext();
    }

    public void show(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }
}

package com.wutong.mvpsimple.common.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

/**
 * Created by 吴同 on 2016/8/12 0012.
 */
public class ToastHelper {
    private final Context mContext;

    public ToastHelper(@NonNull Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}

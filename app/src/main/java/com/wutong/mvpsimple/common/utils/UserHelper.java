package com.wutong.mvpsimple.common.utils;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * 用户管理
 * Created by 吴同 on 2016/8/12 0012.
 */
public class UserHelper {
    private Context mContext;

    private String info;

    public UserHelper(@NonNull Context context) {
        this.mContext = context.getApplicationContext();
    }

    public String getInfo() {
        return String.valueOf(Math.random());
    }
}

package com.wutong.mvpsimple.common.utils;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * 数据库管理
 * Created by 吴同 on 2016/8/12 0012.
 */
public class DBHelper {
    private Context mContext;

    public DBHelper(@NonNull Context context) {
        this.mContext = context.getApplicationContext();
    }
}

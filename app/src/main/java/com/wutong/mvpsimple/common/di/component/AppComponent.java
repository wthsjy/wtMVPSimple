package com.wutong.mvpsimple.common.di.component;

import android.content.Context;

import com.wutong.mvpsimple.common.di.module.AppModule;
import com.wutong.mvpsimple.common.utils.DBHelper;
import com.wutong.mvpsimple.common.utils.ToastHelper;
import com.wutong.mvpsimple.common.utils.UserHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 吴同 on 2016/8/12 0012.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Context getContext();

    UserHelper getUserHelper();

    DBHelper getDBHelper();

    ToastHelper getToastHelper();


}

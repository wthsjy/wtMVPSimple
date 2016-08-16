package com.wutong.mvpsimple.common.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.wutong.mvpsimple.base.App;
import com.wutong.mvpsimple.common.utils.DBHelper;
import com.wutong.mvpsimple.common.utils.ToastHelper;
import com.wutong.mvpsimple.common.utils.UserHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 吴同 on 2016/7/25 0025.
 */
@Module
public class AppModule {
    private final App app;

    public AppModule(@NonNull App app) {
        this.app = app;
    }

    @Singleton @Provides
    public Context provideContext() {
        return app;
    }

    @Singleton @Provides
    public UserHelper provideUserhelper() {
        return new UserHelper(app);
    }

    @Singleton @Provides
    public DBHelper provideDBHelper() {
        return new DBHelper(app);
    }

    @Singleton @Provides
    public ToastHelper provideToastHelper() {
        return new ToastHelper(app);
    }




}

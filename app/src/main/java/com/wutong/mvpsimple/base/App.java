package com.wutong.mvpsimple.base;

import android.app.Application;
import android.content.Intent;

import com.wutong.mvpsimple.common.di.component.AppComponent;
import com.wutong.mvpsimple.common.di.component.DaggerAppComponent;
import com.wutong.mvpsimple.common.di.module.AppModule;
import com.wutong.mvpsimple.common.eventbus.OnBusTokenError;
import com.wutong.mvpsimple.common.utils.LogHelper;
import com.wutong.mvpsimple.view.demo01.FDemo01Activity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by 吴同 on 2016/7/25 0025.
 */
public class App extends Application {
    private static final String TAG = App.class.getSimpleName() + "_TAG";
    private AppComponent mAppComponent;
    private static App instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initDagger2();
        initEventBus();

    }


    private void initDagger2() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public static AppComponent getAppComponent() {
        return App.getInstance().mAppComponent;
    }


    private void initEventBus() {
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public synchronized void onBusTokenError(OnBusTokenError busTokenError) {
        LogHelper.d(TAG, "xxxxxxxxxxxxxx" + System.currentTimeMillis());
        Intent intent = new Intent(this, FDemo01Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP
        |Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}

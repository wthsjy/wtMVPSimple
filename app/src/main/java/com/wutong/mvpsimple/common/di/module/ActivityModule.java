package com.wutong.mvpsimple.common.di.module;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wutong.mvpsimple.common.di.scope.ActivityScope;
import com.wutong.mvpsimple.common.utils.SnakeHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 吴同 on 2016/8/12 0012.
 */
@Module
public class ActivityModule {

    private RxAppCompatActivity mActivity;

    public ActivityModule(RxAppCompatActivity activity) {
        this.mActivity = activity;
    }

    @ActivityScope @Provides
    public RxAppCompatActivity provideActivity() {
        return mActivity;
    }

    @ActivityScope @Provides
    public SnakeHelper provideSnakeHelper() {
        return new SnakeHelper(mActivity);
    }
}

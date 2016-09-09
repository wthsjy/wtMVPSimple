package com.wutong.mvpsimple.view.home;

import com.wutong.mvpsimple.common.di.component.ActivityAbstractComponent;
import com.wutong.mvpsimple.common.di.component.AppComponent;
import com.wutong.mvpsimple.common.di.module.ActivityModule;
import com.wutong.mvpsimple.common.di.scope.ActivityScope;
import com.wutong.mvpsimple.common.utils.SnakeHelper;
import com.wutong.mvpsimple.view.home.view.MainActivity;

import dagger.Component;

/**
 * 首页 组件
 * Created by 吴同 on 2016/8/12 0012.
 */
@ActivityScope
@Component(dependencies = {AppComponent.class},
        modules = {ActivityModule.class})
public interface HomeComponent extends ActivityAbstractComponent {

    SnakeHelper getSnakeHelper();

    void inject(MainActivity mainActivity);


}

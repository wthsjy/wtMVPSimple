package com.wutong.mvpsimple.view.demo01;

import com.wutong.mvpsimple.common.di.component.ActivityAbstractComponent;
import com.wutong.mvpsimple.common.di.component.AppComponent;
import com.wutong.mvpsimple.common.di.module.ActivityModule;
import com.wutong.mvpsimple.common.di.scope.ActivityScope;
import com.wutong.mvpsimple.view.demo01.view.DemoVP01Fragment;
import com.wutong.mvpsimple.view.demo01.view.FDemo01Activity;

import dagger.Component;

/**
 * Created by 吴同 on 2016/8/16 0016.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface Demo01Component extends ActivityAbstractComponent {
    void inject(DemoVP01Fragment demoVP01Fragment);

    void inject(FDemo01Activity fDemo01Activity);

}

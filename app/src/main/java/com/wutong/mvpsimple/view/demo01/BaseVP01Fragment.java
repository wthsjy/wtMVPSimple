package com.wutong.mvpsimple.view.demo01;

import android.widget.TextView;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wutong.mvpsimple.R;
import com.wutong.mvpsimple.base.App;
import com.wutong.mvpsimple.base.BseViewPagerFragment;
import com.wutong.mvpsimple.common.di.module.ActivityModule;
import com.wutong.mvpsimple.common.utils.UserHelper;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by 吴同 on 2016/8/16 0016.
 */
public class BaseVP01Fragment extends BseViewPagerFragment<VPF01Presenter> implements Demo01Contaract.IDemo01View {
    @Inject UserHelper userHelper;

    @Bind(R.id.textView2) TextView textView2;

    @Override public int getLayutResId() {
        return R.layout.fragment_demo01;
    }


    @Override public void init() {
        textView2.setText(getArguments().getString("key") + "   " + userHelper.getInfo());
    }

    @Override protected void inject() {
        Demo01Component activityComponent = DaggerDemo01Component.builder()
                .appComponent(App.getAppComponent())
                .activityModule(new ActivityModule((RxAppCompatActivity) getActivity()))
                .build();
        activityComponent.inject(this);
    }


    @Override public void loadSuccess() {
    }
}

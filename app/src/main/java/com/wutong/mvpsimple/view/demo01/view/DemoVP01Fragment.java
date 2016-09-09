package com.wutong.mvpsimple.view.demo01.view;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wutong.mvpsimple.base.ClientApp;
import com.wutong.mvpsimple.base.fragment.BaseNormalListFragment;
import com.wutong.mvpsimple.common.di.module.ActivityModule;
import com.wutong.mvpsimple.common.utils.UserHelper;
import com.wutong.mvpsimple.view.demo01.DaggerDemo01Component;
import com.wutong.mvpsimple.view.demo01.Demo01Component;
import com.wutong.mvpsimple.view.demo01.Demo01Contaract;
import com.wutong.mvpsimple.view.demo01.presenter.VPF01Presenter;

import javax.inject.Inject;


/**
 * Created by 吴同 on 2016/8/16 0016.
 */
public class DemoVP01Fragment extends BaseNormalListFragment<VPF01Presenter, Demo01VHAdapter, String> implements Demo01Contaract.IDemo01View<String> {
    @Inject UserHelper userHelper;


    @Override protected Demo01VHAdapter getRVAdapter() {
        return new Demo01VHAdapter(getContext());
    }




    @Override protected void inject() {
        Demo01Component activityComponent = DaggerDemo01Component.builder()
                .appComponent(ClientApp.getAppComponent())
                .activityModule(new ActivityModule((RxAppCompatActivity) getActivity()))
                .build();
        activityComponent.inject(this);
    }

    @Override public void getNew() {
        mPresenter.getNew(null);
    }

    @Override public void loadMore() {
        mPresenter.loadMore(null);
    }
}

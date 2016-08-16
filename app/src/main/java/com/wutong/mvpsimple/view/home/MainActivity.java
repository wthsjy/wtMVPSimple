package com.wutong.mvpsimple.view.home;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.wt.load.container.core.WTLoadContainerView;
import com.wutong.mvpsimple.R;
import com.wutong.mvpsimple.base.App;
import com.wutong.mvpsimple.base.BaseActivity;
import com.wutong.mvpsimple.common.di.module.ActivityModule;
import com.wutong.mvpsimple.common.utils.ToastHelper;
import com.wutong.mvpsimple.common.utils.UserHelper;
import com.wutong.mvpsimple.view.demo01.FDemo01Activity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindDimen;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<HomePresenter> implements HomeContaract.IHomeView {
    private static final String TAG = MainActivity.class.getName();

    @Bind(R.id.button1) Button button1;
    @Bind(R.id.button2) Button button2;
    @Bind(R.id.button3) Button button3;
    @Bind(R.id.button4) Button button4;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @BindDimen(R.dimen.activity_horizontal_margin) int gap;

    @Inject UserHelper userHelper;
    @Inject ToastHelper toastHelper;
    @Bind(R.id.wt_load_container) WTLoadContainerView wtLoadContainer;


    @Override protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override protected void inject() {
        HomeComponent activityComponent = DaggerHomeComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
        activityComponent.inject(this);
    }

    @Override protected void init() {
        wtLoadContainer.setLoadContainerActionListener(new WTLoadContainerView.LoadContainerActionListener() {
            @Override public void onNetWorkErrorViewClick() {
                mPresenter.loadTestData();
                wtLoadContainer.showLoadingView();
            }

            @Override public void onNoDataViewClick() {

            }
        });
    }

    @OnClick(R.id.button1) void onclickLoading() {
        mPresenter.loadTestData();
        wtLoadContainer.showLoadingView();
        startActivity(new Intent(this, FDemo01Activity.class));

    }

    @OnClick(R.id.button2) void onclickNoData() {
        wtLoadContainer.showNoDataView();
    }

    @OnClick(R.id.button3) void onclickNetError() {
        wtLoadContainer.showNetErrorView();
    }

    @OnClick(R.id.button4) void onclickShowData() {
        wtLoadContainer.showDataView();
    }

    @Override public void loadSuccess() {
        wtLoadContainer.showDataView();
    }


}

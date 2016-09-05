package com.wutong.mvpsimple.view.home;

import android.content.Intent;
import android.os.Handler;
import android.widget.Button;

import com.wt.load.container.core.WTLoadContainerView;
import com.wutong.mvpsimple.R;
import com.wutong.mvpsimple.base.App;
import com.wutong.mvpsimple.base.BaseActivity;
import com.wutong.mvpsimple.common.di.module.ActivityModule;
import com.wutong.mvpsimple.view.demo01.FDemo01Activity;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<HomePresenter> implements HomeContaract.IHomeView {
    private static final String TAG = MainActivity.class.getName();


    @Bind(R.id.wt_load_container1) WTLoadContainerView wtLoadContainer1;
    @Bind(R.id.wt_load_container2) WTLoadContainerView wtLoadContainer2;
    @Bind(R.id.wt_load_container3) WTLoadContainerView wtLoadContainer3;
    @Bind(R.id.btn_jump) Button button;



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
        wtLoadContainer1.setLoadContainerActionListener(new WTLoadContainerView.LoadContainerActionListener() {
            @Override public void onNetWorkErrorViewClick(WTLoadContainerView view) {

            }

            @Override public void onNoDataViewClick(WTLoadContainerView view) {

            }
        });
        wtLoadContainer2.setLoadContainerActionListener(new WTLoadContainerView.LoadContainerActionListener() {
            @Override public void onNetWorkErrorViewClick(WTLoadContainerView view) {

            }

            @Override public void onNoDataViewClick(WTLoadContainerView view) {
                startActivity(new Intent(MainActivity.this, FDemo01Activity.class));
            }
        });
        wtLoadContainer3.setLoadContainerActionListener(new WTLoadContainerView.LoadContainerActionListener() {
            @Override public void onNetWorkErrorViewClick(WTLoadContainerView view) {
                mPresenter.loadTestData();
                view.showLoadingView();
            }

            @Override public void onNoDataViewClick(WTLoadContainerView view) {

            }
        });



        mPresenter.loadTestData();
        mPresenter.loadTestData();
        mPresenter.loadTestData();
        mPresenter.loadTestData();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
    }

    @Override public void loadSuccess() {
        wtLoadContainer1.showDataView();
        wtLoadContainer2.showNoDataView();
        wtLoadContainer3.showNetErrorView();
    }

    @OnClick(R.id.btn_jump) void jump() {
        startActivity(new Intent(this, FDemo01Activity.class));
    }
}

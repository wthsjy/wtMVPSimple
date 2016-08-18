package com.wutong.mvpsimple.view.home;

import android.os.Handler;

import com.wt.load.container.core.WTLoadContainerView;
import com.wutong.mvpsimple.R;
import com.wutong.mvpsimple.base.App;
import com.wutong.mvpsimple.base.BaseActivity;
import com.wutong.mvpsimple.common.di.module.ActivityModule;

import butterknife.Bind;

public class MainActivity extends BaseActivity<HomePresenter> implements HomeContaract.IHomeView, WTLoadContainerView.LoadContainerActionListener {
    private static final String TAG = MainActivity.class.getName();


    @Bind(R.id.wt_load_container1) WTLoadContainerView wtLoadContainer1;
    @Bind(R.id.wt_load_container2) WTLoadContainerView wtLoadContainer2;
    @Bind(R.id.wt_load_container3) WTLoadContainerView wtLoadContainer3;
    private Handler handler1 = new Handler();
    private Handler handler2 = new Handler();
    private Handler handler3 = new Handler();


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
            @Override public void onNetWorkErrorViewClick() {

            }

            @Override public void onNoDataViewClick() {

            }
        });
        wtLoadContainer2.setLoadContainerActionListener(new WTLoadContainerView.LoadContainerActionListener() {
            @Override public void onNetWorkErrorViewClick() {

            }

            @Override public void onNoDataViewClick() {

            }
        });
        wtLoadContainer3.setLoadContainerActionListener(new WTLoadContainerView.LoadContainerActionListener() {
            @Override public void onNetWorkErrorViewClick() {

            }

            @Override public void onNoDataViewClick() {

            }
        });

        handler1.postDelayed(new Runnable() {
            @Override public void run() {
                wtLoadContainer1.showDataView();
            }
        },2000);
        handler2.postDelayed(new Runnable() {
            @Override public void run() {
              wtLoadContainer2.showNoDataView();
            }
        },4000);
        handler3.postDelayed(new Runnable() {
            @Override public void run() {
                wtLoadContainer3.showNetErrorView();
            }
        },6000);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
    }

    @Override public void loadSuccess() {
    }


    @Override public void onNetWorkErrorViewClick() {

    }

    @Override public void onNoDataViewClick() {

    }
}

package com.wutong.mvpsimple.view.home;

import android.os.Handler;

import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wutong.mvpsimple.base.BasePresenter;
import com.wutong.mvpsimple.common.utils.LogHelper;
import com.wutong.mvpsimple.common.utils.RxHelper.BaseSubscriber;
import com.wutong.mvpsimple.data.entity.BaseEntity;
import com.wutong.mvpsimple.data.model.TestDataModel;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * Created by 吴同 on 2016/8/15 0015.
 */
public class HomePresenter extends BasePresenter<HomeContaract.IHomeView> implements HomeContaract.IHomePresenter {
    private final String TAG = HomePresenter.class.getSimpleName();
    private RxAppCompatActivity mActivity;

    @Inject Lazy<TestDataModel> testDataModel;


    @Inject public HomePresenter(RxAppCompatActivity activity) {
        mActivity = activity;
    }


    @Override public void loadTestData() {

        testDataModel.get().getData()
                .compose(mActivity.<BaseEntity>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseSubscriber<BaseEntity>() {
                    @Override public void onCompleted() {
                        LogHelper.d(TAG,"onCompleted");
                    }

                    @Override public void onError(Throwable e) {
                        LogHelper.d(TAG,"onError");
                        e.printStackTrace();

                    }

                    @Override public void onNext(BaseEntity o) {
                        LogHelper.d(TAG,"onNext");
                        new Handler().postDelayed(new Runnable() {
                            @Override public void run() {
                                mView.loadSuccess();
                            }
                        }, 2000);
                    }
                });
    }


}

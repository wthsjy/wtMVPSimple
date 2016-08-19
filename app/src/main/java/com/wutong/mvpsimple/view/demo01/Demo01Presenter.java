package com.wutong.mvpsimple.view.demo01;

import android.os.Handler;

import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wutong.mvpsimple.common.utils.RxHelper;
import com.wutong.mvpsimple.data.entity.BaseEntity;
import com.wutong.mvpsimple.data.model.TestDataModel;
import com.wutong.mvpsimple.base.BasePresenter;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * Created by 吴同 on 2016/8/16 0016.
 */
public class Demo01Presenter extends BasePresenter<Demo01Contaract.IDemo01View> implements Demo01Contaract.IDemo01Presenter {
    private final String TAG = Demo01Presenter.class.getSimpleName();
    private RxAppCompatActivity mActivity;

    @Inject Lazy<TestDataModel> testDataModel;


    @Inject public Demo01Presenter(RxAppCompatActivity activity) {
        mActivity = activity;
    }


    @Override public void loadTestData() {

        testDataModel.get().getData()
                .compose(mActivity.<BaseEntity>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new RxHelper.BaseSubscriber<BaseEntity>() {
                    @Override public void onCompleted() {

                    }

                    @Override public void onError(Throwable e) {
                        new Handler().postDelayed(new Runnable() {
                            @Override public void run() {
                                mView.loadSuccess();
                            }
                        },2000);

                    }

                    @Override public void onNext(BaseEntity o) {

                    }
                });
    }


}

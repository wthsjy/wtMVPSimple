package com.wutong.mvpsimple.view.demo01.presenter;

import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wutong.mvpsimple.base.presenter.BasePresenter;
import com.wutong.mvpsimple.common.utils.okhttp.BaseRetrofitSubscriber;
import com.wutong.mvpsimple.data.entity.BaseEntity;
import com.wutong.mvpsimple.data.model.TestDataModel;
import com.wutong.mvpsimple.view.demo01.Demo01Contaract;

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


     public void loadTestData() {

        testDataModel.get().getData("17749792015", "123456")
                .compose(mActivity.<BaseEntity>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseRetrofitSubscriber<BaseEntity>() {


                    @Override public void onStart() {

                    }

                    @Override protected void onSuccess(BaseEntity o) {

                    }


                    @Override public void onHttpError() {

                    }

                    @Override protected void onUnknowError(Throwable e) {

                    }
                });
    }


}

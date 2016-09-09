package com.wutong.mvpsimple.view.home.presenter;

import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wutong.mvpsimple.base.presenter.BasePresenter;
import com.wutong.mvpsimple.common.utils.okhttp.BaseRetrofitSubscriber;
import com.wutong.mvpsimple.data.entity.BaseEntity;
import com.wutong.mvpsimple.data.model.TestDataModel;
import com.wutong.mvpsimple.view.home.HomeContaract;

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

        testDataModel.get().getData("17749792015","123456")
                .compose(mActivity.<BaseEntity>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseRetrofitSubscriber<BaseEntity>() {

                    @Override public void onStart() {
                    }

                    @Override protected void onSuccess(BaseEntity o) {
                        mView.loadSuccess();
                    }


                    @Override public void onHttpError() {
                    }

                    @Override protected void onUnknowError(Throwable e) {

                    }
                });
    }


}

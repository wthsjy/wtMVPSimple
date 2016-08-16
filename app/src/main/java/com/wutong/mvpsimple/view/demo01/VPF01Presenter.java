package com.wutong.mvpsimple.view.demo01;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wutong.mvpsimple.data.model.TestDataModel;
import com.wutong.mvpsimple.base.BasePresenter;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * Created by 吴同 on 2016/8/16 0016.
 */
public class VPF01Presenter extends BasePresenter<Demo01Contaract.IDemo01View> implements Demo01Contaract.IDemo01Presenter {
    private final String TAG = VPF01Presenter.class.getSimpleName();
    private RxAppCompatActivity mActivity;

    @Inject Lazy<TestDataModel> testDataModel;


    @Inject public VPF01Presenter(RxAppCompatActivity activity) {
        mActivity = activity;
    }


    @Override public void loadTestData() {


    }


}

package com.wutong.mvpsimple.view.demo01.presenter;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wutong.mvpsimple.base.presenter.BasePresenter;
import com.wutong.mvpsimple.data.model.UserDataModel;
import com.wutong.mvpsimple.view.demo01.Demo01Contaract;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * Created by 吴同 on 2016/8/16 0016.
 */
public class Demo01Presenter extends BasePresenter<Demo01Contaract.IDemo01View> implements Demo01Contaract.IDemo01Presenter {
    private final String TAG = Demo01Presenter.class.getSimpleName();
    private RxAppCompatActivity mActivity;

    @Inject Lazy<UserDataModel> testDataModel;


    @Inject public Demo01Presenter(RxAppCompatActivity activity) {
        mActivity = activity;
    }


}

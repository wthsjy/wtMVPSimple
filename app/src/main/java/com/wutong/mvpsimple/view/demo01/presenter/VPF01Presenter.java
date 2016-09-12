package com.wutong.mvpsimple.view.demo01.presenter;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wutong.mvpsimple.base.presenter.BaseListPresenter;
import com.wutong.mvpsimple.common.utils.okhttp.BaseRetrofitSubscriber;
import com.wutong.mvpsimple.data.entity.UserListEntity;
import com.wutong.mvpsimple.data.model.UserDataModel;
import com.wutong.mvpsimple.view.demo01.Demo01Contaract;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * Created by 吴同 on 2016/8/16 0016.
 */
public class VPF01Presenter extends BaseListPresenter<Demo01Contaract.IDemo01View> implements Demo01Contaract.IDemo01Presenter {
    private final String TAG = VPF01Presenter.class.getSimpleName();
    private RxAppCompatActivity mActivity;

    @Inject Lazy<UserDataModel> testDataModel;
    private int page = 1;


    @Inject public VPF01Presenter(RxAppCompatActivity activity) {
        mActivity = activity;
    }


    @Override public void getNew(HashMap<String, Object> map) {
        testDataModel.get().getUserList(1)
                .subscribe(new BaseRetrofitSubscriber<UserListEntity>() {
                    @Override public void onStart() {

                    }

                    @Override protected void onSuccess(UserListEntity o) {
                        page = 1;
                        mView.getNewSuccess((ArrayList) o.getData());
                    }

                    @Override public void onHttpError() {
                        mView.getListFail();
                    }

                    @Override protected void onUnknowError(Throwable e) {
                        mView.getListFail();
                    }
                });

    }


    @Override public void loadMore(HashMap<String, Object> map) {
        testDataModel.get().getUserList(page + 1)
                .subscribe(new BaseRetrofitSubscriber<UserListEntity>() {
                    @Override public void onStart() {

                    }

                    @Override protected void onSuccess(UserListEntity o) {
                        page = page + 1;
                        mView.getLoadMoreSuccess((ArrayList) o.getData());
                    }

                    @Override public void onHttpError() {
                        mView.getListFail();
                    }

                    @Override protected void onUnknowError(Throwable e) {
                        mView.getListFail();
                    }
                });
    }


}

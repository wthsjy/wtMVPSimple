package com.wutong.mvpsimple.view.demo01.presenter;

import android.os.Handler;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wutong.mvpsimple.base.presenter.BaseListPresenter;
import com.wutong.mvpsimple.common.utils.LogHelper;
import com.wutong.mvpsimple.data.model.TestDataModel;
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

    @Inject Lazy<TestDataModel> testDataModel;


    @Inject public VPF01Presenter(RxAppCompatActivity activity) {
        mActivity = activity;
    }


    @Override public void getNew(HashMap<String, Object> map) {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                ArrayList<String> list = new ArrayList<>();
              for (int i = 0;i<40;i++){
                  list.add(i+"xxxxxxss"+Math.random());
              }
                LogHelper.d(TAG,"getNew()");
                mView.getNewSuccess(list);
            }
        },3000);

    }
int index = 0;
    @Override public void loadMore(HashMap<String, Object> map) {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                ArrayList<String> list = new ArrayList<>();
                index++;
                if (index <= 3){
                    for (int i = 0;i<20;i++){
                        list.add(i+"eeeeexxxxxxss"+Math.random());
                    }
                }
                if (index == 5){
                    index = 0;
                }

                mView.getLoadMoreSuccess(list);
            }
        },3000);
    }
}

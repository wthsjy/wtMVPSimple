package com.wutong.mvpsimple.base.presenter;

import com.wutong.mvpsimple.common.utils.LogHelper;

/**
 * Created by 吴同 on 2016/8/15 0015.
 */
public abstract class BasePresenter<V> {
    protected V mView;

    public void dettachView() {
        if (mView != null){
            LogHelper.d("BasePresenter<"+mView.getClass().getSimpleName()+">",
                    "dettachView: mView set null ");
        }

        mView = null;

    }

    public void attachView(V  view) {
        mView = view;
        if (mView!=null){
            LogHelper.d("BasePresenter<"+mView.getClass().getSimpleName()+">",
                    "attachView: mView instance of "+mView.getClass().getSimpleName());
        }


    }

}

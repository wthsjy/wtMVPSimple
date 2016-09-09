package com.wutong.mvpsimple.base.fragment;

/**
 * Created by 吴同 on 2016/8/23 0023.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;
import com.wutong.mvpsimple.base.presenter.BasePresenter;
import com.wutong.mvpsimple.base.view.IView;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * 用于一般的Activity 中的 fragment
 * Created by 吴同 on 2016/8/16 0016.
 */
public abstract class BaseNormalFragment<T extends BasePresenter> extends RxFragment implements IView {
    protected View mRootView;
    @Inject public T mPresenter;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
    }


    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayutResId(), container, false);
            ButterKnife.bind(this, mRootView);
            mPresenter.attachView(this);
            init();
        }

        return mRootView;
    }


    @Override public void onDestroyView() {
        super.onDestroyView();
    }

    @Override public void onDestroy() {
        ButterKnife.unbind(this);
        mPresenter.dettachView();
        super.onDestroy();

    }


    /**
     * 布局
     *
     * @return
     */
    protected abstract int getLayutResId();

    /**
     * 界面展示后初始化，只执行一次
     */
    protected abstract void init();

    /**
     * 注入
     */
    protected abstract void inject();
}
package com.wutong.mvpsimple.base.fragment;

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
 * 用于viewpager 中的 fragment
 * Created by 吴同 on 2016/8/16 0016.
 */
public abstract class BaseViewPagerFragment<T extends BasePresenter> extends RxFragment implements IView {
    protected View mRootView;
    protected boolean isVisibleToUser = false;
    /**
     * 标志位，View已经初始化完成。
     */
    private boolean isPrepared = false;
    /**
     * 是否已经第一次加载
     */
    private boolean isFirstLoadAlready = false;

    @Inject T mPresenter;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
    }


    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayutResId(), container, false);
            ButterKnife.bind(this, mRootView);
            mPresenter.attachView(this);

        }
        isPrepared = true;
        lazyLoad();
        return mRootView;
    }


    @Override public void onDestroyView() {
        super.onDestroyView();
        isPrepared = false;

    }

    @Override public void onDestroy() {
        ButterKnife.unbind(this);
        mPresenter.dettachView();
        super.onDestroy();

    }

    /**
     * 要实现延迟加载Fragment内容,需要在 onCreateView
     * isPrepared = true;
     */
    protected void lazyLoad() {
        if (!isVisibleToUser || !isPrepared || isFirstLoadAlready) {
            return;
        }
        isFirstLoadAlready = true;
        init();
    }

    /**
     * 如果是与ViewPager一起使用，调用的是setUserVisibleHint
     *
     * @param isVisibleToUser 是否显示出来了
     */
    @Override public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser) {
            lazyLoad();
        }

    }


    /**
     * 布局
     *
     * @return
     */
    public abstract int getLayutResId();

    /**
     * 界面展示后初始化，只执行一次
     */
    public abstract void init();

    /**
     * 注入
     */
    protected abstract void inject();



}

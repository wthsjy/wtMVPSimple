package com.wutong.mvpsimple.base.activity;

import android.os.Bundle;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wutong.mvpsimple.base.presenter.BasePresenter;
import com.wutong.mvpsimple.base.view.IView;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by 吴同 on 2016/8/9 0009.
 */
public abstract class BaseActivity<T extends BasePresenter> extends RxAppCompatActivity implements IView {
    @Inject
    public T mPresenter;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mPresenter.attachView(this);
        init();

    }

    @Override protected void onResume() {
        super.onResume();
    }

    /**
     * 初始化
     */
    protected abstract void init();

    /**
     * 布局
     *
     * @return
     */
    protected abstract int getLayoutId();

    @Override protected void onDestroy() {
        mPresenter.dettachView();
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    /**
     * 注入dagger2
     */
    protected abstract void inject();


}

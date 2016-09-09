package com.wutong.mvpsimple.base.presenter;

import java.util.HashMap;

/**
 * 含上下拉刷新的presenter
 * Created by 吴同 on 2016/9/9 0009.
 */
public abstract class BaseListPresenter<V> extends BasePresenter<V> {
    public abstract void getNew(HashMap<String, Object> map);

    public abstract void loadMore(HashMap<String, Object> map);
}

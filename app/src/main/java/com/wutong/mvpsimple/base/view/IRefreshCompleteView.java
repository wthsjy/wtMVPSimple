package com.wutong.mvpsimple.base.view;

import java.util.ArrayList;

/**
 * 下拉刷新，上拉加载更多的回调
 * Created by 吴同 on 2016/9/9 0009.
 */
public interface IRefreshCompleteView<E> extends IView {
    public void getNewSuccess(ArrayList<E> obj);

    public void getLoadMoreSuccess(ArrayList<E> obj);

    public void getListFail();
}

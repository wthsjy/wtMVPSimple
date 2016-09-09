package com.wutong.mvpsimple.base.fragment;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wutong.mvpsimple.R;
import com.wutong.mvpsimple.base.adapter.BaseSingleRVAdapter;
import com.wutong.mvpsimple.base.presenter.BaseListPresenter;
import com.wutong.mvpsimple.base.view.IRefreshCompleteView;
import com.wutong.mvpsimple.common.utils.ToastHelper;

import java.io.Serializable;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * 一般的activity 中的列表形式 fragment
 * Created by 吴同 on 2016/9/9 0009.
 */
public abstract class BaseNormalListFragment<T extends BaseListPresenter, AD extends BaseSingleRVAdapter, E extends Serializable>
        extends BaseNormalFragment<T> implements XRecyclerView.LoadingListener, IRefreshCompleteView<E> {
    @Bind(R.id.recycler_view) XRecyclerView mRecyclerView;
    protected AD mAdapter;


    @Override protected int getLayutResId() {
        return R.layout.layout_common_refresh_list;
    }


    @Override protected void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //设置布局管理器
        mRecyclerView.setLayoutManager(layoutManager);
        //设置adapter
        mAdapter = getRVAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLoadingListener(this);
        setXRecyclerViewRefreshMode(true, true);
        mRecyclerView.setRefreshing(true);


    }

    /**
     * 上下拉模式
     *
     * @param refresh
     * @param loadmore
     */
    public void setXRecyclerViewRefreshMode(boolean refresh, boolean loadmore) {
        mRecyclerView.setPullRefreshEnabled(refresh);
        mRecyclerView.setLoadingMoreEnabled(loadmore);
    }

    /**
     * 获取adapter
     *
     * @return
     */
    protected abstract AD getRVAdapter();

    /**
     * 此处注入compoent
     */
    @Override protected abstract void inject();

    @Override public void getNewSuccess(ArrayList<E> list) {
        mRecyclerView.refreshComplete();
        mRecyclerView.setLoadingMoreEnabled(true);
        if (list == null) {
            return;
        }
        mAdapter.setData((ArrayList) list);

    }

    @Override public void getLoadMoreSuccess(ArrayList<E> list) {
        mRecyclerView.loadMoreComplete();
        if (list == null || list.size() == 0) {
            mRecyclerView.setLoadingMoreEnabled(false);
            return;
        }
        ArrayList<E> oldList = mAdapter.getData();
        oldList.addAll(list);
        mAdapter.setData(oldList);

    }

    @Override public void getListFail() {
        mRecyclerView.refreshComplete();
        mRecyclerView.loadMoreComplete();
    }

    @Override public abstract void onRefresh();

    @Override public abstract void onLoadMore();
}

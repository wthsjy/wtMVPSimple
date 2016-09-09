package com.wutong.mvpsimple.base.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wutong.mvpsimple.R;
import com.wutong.mvpsimple.base.adapter.BaseSingleRVAdapter;
import com.wutong.mvpsimple.base.presenter.BaseListPresenter;
import com.wutong.mvpsimple.base.view.IRefreshCompleteView;
import com.ybao.pullrefreshview.layout.BaseFooterView;
import com.ybao.pullrefreshview.layout.BaseHeaderView;
import com.ybao.pullrefreshview.layout.PullRefreshLayout;
import com.ybao.pullrefreshview.simple.view.NormalFooterView;
import com.ybao.pullrefreshview.simple.view.NormalHeaderView;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.Bind;

/**
 * 一般的activity 中的列表形式 fragment
 * Created by 吴同 on 2016/9/9 0009.
 */
public abstract class BaseNormalListFragment<T extends BaseListPresenter, AD extends BaseSingleRVAdapter, E extends Serializable>
        extends BaseNormalFragment<T> implements IRefreshCompleteView<E>, BaseHeaderView.OnRefreshListener, BaseFooterView.OnLoadListener {
    @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
    @Bind(R.id.pullRefreshLayout) PullRefreshLayout pullRefreshLayout;
    @Bind(R.id.header) NormalHeaderView header;
    @Bind(R.id.footer) NormalFooterView footer;
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

        header.setOnRefreshListener(this);
        footer.setOnLoadListener(this);
        header.startRefresh();
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
        header.stopRefresh();
        if (list == null) {
            return;
        }
        mAdapter.setData((ArrayList) list);

    }

    @Override public void getLoadMoreSuccess(ArrayList<E> list) {
        footer.stopLoad();
        if (list == null || list.size() == 0) {
            return;
        }
        ArrayList<E> oldList = mAdapter.getData();
        oldList.addAll(list);
        mAdapter.setData(oldList);

    }

    @Override public void getListFail() {
        footer.stopLoad();
        header.stopRefresh();
    }

    public abstract void getNew();

    public abstract void loadMore();


    @Override public void onRefresh(BaseHeaderView baseHeaderView) {
        getNew();
    }

    @Override public void onLoad(BaseFooterView baseFooterView) {
        loadMore();
    }
}

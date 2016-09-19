package com.wutong.mvpsimple.base.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.dinuscxj.refresh.RecyclerRefreshLayout;
import com.wt.load.container.core.WTLoadContainerView;
import com.wutong.mvpsimple.R;
import com.wutong.mvpsimple.base.adapter.BaseRVAdapter;
import com.wutong.mvpsimple.base.presenter.BaseListPresenter;
import com.wutong.mvpsimple.base.view.IRefreshCompleteView;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.Bind;

/**
 * 一般的activity 中的列表形式 fragment
 * Created by 吴同 on 2016/9/9 0009.
 */
public abstract class BaseNormalListFragment<T extends BaseListPresenter, AD extends BaseRVAdapter, E extends Serializable>
        extends BaseNormalFragment<T> implements IRefreshCompleteView<E>, RecyclerRefreshLayout.OnRefreshListener, WTLoadContainerView.LoadContainerActionListener {
    private static final String TAG = BaseNormalListFragment.class.getSimpleName();
    @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
    @Bind(R.id.wtLoadContainerLayout) WTLoadContainerView wtLoadContainerView;
    @Bind(R.id.pullRefreshLayout) RecyclerRefreshLayout pullRefreshLayout;

    protected AD mAdapter;
    private boolean mIsLoading = false;
    private boolean hasMore = true;
    private int defaultPageSize = 0;


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
        pullRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.addOnScrollListener(new AutoLoadEventDetector());
        wtLoadContainerView.setLoadContainerActionListener(this);
        autoGetNew();


    }

    private void autoGetNew() {
        mIsLoading = true;
        getNew();
        wtLoadContainerView.showLoadingView();
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
        Toast.makeText(getContext(),list.size()+"",Toast.LENGTH_SHORT).show();
        mIsLoading = false;
        pullRefreshLayout.setRefreshing(false);
        if (list == null) {
            hasMore = false;
            wtLoadContainerView.showNoDataView();
        } else {
            mAdapter.setData((ArrayList) list);
            defaultPageSize = list.size();
            if (defaultPageSize > 0) {
                hasMore = true;
                wtLoadContainerView.showDataView();
            } else {
                hasMore = false;
                wtLoadContainerView.showNoDataView();
            }
        }

    }

    @Override public void getLoadMoreSuccess(ArrayList<E> list) {
        mIsLoading = false;
        pullRefreshLayout.setRefreshing(false);
        if (list == null || list.size() == 0) {
            hasMore = false;
        } else {
            ArrayList<E> oldList = mAdapter.getData();
            oldList.addAll(list);
            mAdapter.setData(oldList);
            if (list.size() < defaultPageSize) {
                hasMore = false;
            } else {
                hasMore = true;
            }
        }


    }

    @Override public void getListFail() {
        mIsLoading = false;
        pullRefreshLayout.setRefreshing(false);
        wtLoadContainerView.showNetErrorView();
    }

    public abstract void getNew();

    public abstract void loadMore();


    @Override public void onRefresh() {
        getNew();
    }

    @Override public void onNetWorkErrorViewClick(WTLoadContainerView view) {
        autoGetNew();
    }

    @Override public void onNoDataViewClick(WTLoadContainerView view) {
        autoGetNew();
    }

    private class AutoLoadEventDetector extends RecyclerView.OnScrollListener {

        @Override
        public void onScrolled(RecyclerView view, int dx, int dy) {
            RecyclerView.LayoutManager manager = view.getLayoutManager();
            if (manager.getChildCount() > 0) {
                int count = manager.getItemCount();
                int last = ((RecyclerView.LayoutParams) manager
                        .getChildAt(manager.getChildCount() - 1).getLayoutParams()).getViewAdapterPosition();

                if (last == count - 1 && (!mIsLoading) && hasMore) {
                    mIsLoading = true;
                    loadMore();
                }
            }
        }
    }
}

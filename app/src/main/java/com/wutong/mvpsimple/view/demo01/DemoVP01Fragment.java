package com.wutong.mvpsimple.view.demo01;

import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wutong.mvpsimple.R;
import com.wutong.mvpsimple.base.ClientApp;
import com.wutong.mvpsimple.base.fragment.BaseViewPagerFragment;
import com.wutong.mvpsimple.common.di.module.ActivityModule;
import com.wutong.mvpsimple.common.utils.UserHelper;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by 吴同 on 2016/8/16 0016.
 */
public class DemoVP01Fragment extends BaseViewPagerFragment<VPF01Presenter> implements Demo01Contaract.IDemo01View {
    @Inject UserHelper userHelper;

    @Bind(R.id.textView2) TextView textView2;
    @Bind(R.id.recycler_view) XRecyclerView mRecyclerView;

    @Override public int getLayutResId() {
        return R.layout.fragment_demo01;
    }


    @Override public void init() {
        textView2.setText(getArguments().getString("key") + "   " + userHelper.getInfo());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //设置布局管理器
        mRecyclerView.setLayoutManager(layoutManager);
        //设置adapter
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            strings.add("xxxx" + i);
        }
        Demo01VHAdapter adapter = new Demo01VHAdapter(getActivity());
        mRecyclerView.setAdapter(adapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.notifyDataSetChanged();
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        mRecyclerView.refreshComplete();
                        mRecyclerView.setLoadingMoreEnabled(true);
                    }
                }, 5000);
            }

            @Override public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        mRecyclerView.loadMoreComplete();
                        mRecyclerView.setLoadingMoreEnabled(false);
                    }
                }, 5000);
            }
        });


    }

    @Override protected void inject() {
        Demo01Component activityComponent = DaggerDemo01Component.builder()
                .appComponent(ClientApp.getAppComponent())
                .activityModule(new ActivityModule((RxAppCompatActivity) getActivity()))
                .build();
        activityComponent.inject(this);
    }


    @Override public void loadSuccess() {
    }


    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

package com.wutong.mvpsimple.view.demo01;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.wutong.mvpsimple.R;
import com.wutong.mvpsimple.base.App;
import com.wutong.mvpsimple.base.BaseActivity;
import com.wutong.mvpsimple.common.di.module.ActivityModule;

import butterknife.Bind;

/**
 * Created by 吴同 on 2016/8/16 0016.
 */
public class FDemo01Activity extends BaseActivity<Demo01Presenter> {

    @Bind(R.id.smart_tab_layout) SmartTabLayout smartTabLayout;
    @Bind(R.id.view_pager) ViewPager viewPager;

    @Override protected void inject() {
        Demo01Component activityComponent = DaggerDemo01Component.builder()
                .appComponent(App.getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
        activityComponent.inject(this);
    }

    @Override protected int getLayoutId() {
        return R.layout.activity_fdemo01;
    }


    @Override protected void init() {
        FragmentPagerItems.Creator creator = FragmentPagerItems.with(this);
        for (int i = 0; i < 4; i++) {
            Bundle bundle = new Bundle();
            bundle.putString("key", i + "");
            creator.add("0" + i, BaseVP01Fragment.class, bundle);
        }


        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), creator
                .create());
        viewPager.setAdapter(adapter);
        smartTabLayout.setViewPager(viewPager);
    }


}

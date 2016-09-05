package com.wutong.mvpsimple.data.model;

import com.wutong.mvpsimple.common.utils.LogHelper;
import com.wutong.mvpsimple.common.utils.okhttp.RetrofitHelper;
import com.wutong.mvpsimple.data.entity.BaseEntity;
import com.wutong.mvpsimple.data.model.api.TestApiService;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 吴同 on 2016/8/12 0012.
 */
public class TestDataModel {
    TestApiService apiService;
    private static final String TAG = TestDataModel.class.getName();

    @Inject @Singleton
    public TestDataModel() {
        LogHelper.d(TAG, "TestDataModel init");
        apiService = RetrofitHelper.getInstance().getRetrofit().create(TestApiService.class);

    }


    public Observable<BaseEntity> getData(String username,String password) {
        return apiService.getData(username,password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

}

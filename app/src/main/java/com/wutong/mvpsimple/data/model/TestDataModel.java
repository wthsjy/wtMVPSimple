package com.wutong.mvpsimple.data.model;

import com.wutong.mvpsimple.common.utils.LogHelper;
import com.wutong.mvpsimple.data.entity.BaseEntity;
import com.wutong.mvpsimple.data.model.api.TestApiService;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
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

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://t2.refineit.cn:98/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        apiService = retrofit.create(TestApiService.class);

    }


    public Observable<BaseEntity> test() {
        return apiService.getData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

}

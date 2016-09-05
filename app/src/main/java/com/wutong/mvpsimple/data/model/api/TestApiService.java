package com.wutong.mvpsimple.data.model.api;

import com.wutong.mvpsimple.data.entity.BaseEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 测试API 使用
 * Created by 吴同 on 2016/8/12 0012.
 */
public interface TestApiService {
    @FormUrlEncoded
    @POST(value = "boot05/api/user/v1/login") Observable<BaseEntity> getData(
            @Field("username") String username,
            @Field("password") String password
    );
}

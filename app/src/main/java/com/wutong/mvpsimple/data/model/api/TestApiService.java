package com.wutong.mvpsimple.data.model.api;

import com.wutong.mvpsimple.data.entity.BaseEntity;

import retrofit2.http.POST;
import rx.Observable;

/**
 * 测试API 使用
 * Created by 吴同 on 2016/8/12 0012.
 */
public interface TestApiService {
    @POST(value = "/test/text") Observable<BaseEntity> getData();
}

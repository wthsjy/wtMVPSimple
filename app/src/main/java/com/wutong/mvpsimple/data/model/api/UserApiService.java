package com.wutong.mvpsimple.data.model.api;

import com.wutong.mvpsimple.data.entity.UserListEntity;

import java.util.HashMap;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 测试API 使用
 * Created by 吴同 on 2016/8/12 0012.
 */
public interface UserApiService {
    @FormUrlEncoded
    @POST(value = "boot05/api/user/v1/userList") Observable<UserListEntity> getData(
            @FieldMap HashMap<String, Object> map
    );
}

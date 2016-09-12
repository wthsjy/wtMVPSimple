package com.wutong.mvpsimple.data.model;

import com.wutong.mvpsimple.common.utils.okhttp.BaseReqParams;
import com.wutong.mvpsimple.common.utils.okhttp.RetrofitHelper;
import com.wutong.mvpsimple.data.entity.UserListEntity;
import com.wutong.mvpsimple.data.model.api.UserApiService;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 吴同 on 2016/8/12 0012.
 */
public class UserDataModel {
    UserApiService apiService;
    private static final String TAG = UserDataModel.class.getName();

    @Inject @Singleton
    public UserDataModel() {
        apiService = RetrofitHelper.getInstance().getRetrofit().create(UserApiService.class);

    }


    public Observable<UserListEntity> getUserList(int pageIndex) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", pageIndex);
        return apiService.getData(BaseReqParams.getREQMap(map)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

}

package com.wutong.mvpsimple.data.entity;

import com.wutong.mvpsimple.data.entity.element.UserInfo;

import java.util.List;

/**
 * Created by 吴同 on 2016/9/12 0012.
 */
public class UserListEntity extends BaseEntity {


    private List<UserInfo> data;



    public List<UserInfo> getData() {
        return data;
    }

    public void setData(List<UserInfo> data) {
        this.data = data;
    }


}

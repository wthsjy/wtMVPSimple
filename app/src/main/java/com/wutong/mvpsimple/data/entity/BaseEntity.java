package com.wutong.mvpsimple.data.entity;

import java.io.Serializable;

/**
 * Created by 吴同 on 2016/8/15 0015.
 */
public class BaseEntity implements Serializable {
    public String code;
    public String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

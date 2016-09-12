package com.wutong.mvpsimple.data.entity;

import java.io.Serializable;

/**
 * Created by 吴同 on 2016/8/15 0015.
 */
public class BaseEntity implements Serializable {
    public String code;
    public String t;
    public String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

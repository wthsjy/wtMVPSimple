package com.wutong.mvpsimple.common.utils.okhttp;

import java.util.HashMap;

/**
 * Created by 吴同 on 2016/9/12 0012.
 */
public class BaseReqParams {
    /**
     * 请求参数封装，此处添加系统参数
     * @param map
     * @return
     */
    public static HashMap<String, Object> getREQMap(HashMap<String, Object> map) {
        if (map == null) {
            map = new HashMap<>();
        }

        return map;
    }
}

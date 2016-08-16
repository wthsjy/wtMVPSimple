package com.wutong.mvpsimple.common.utils;

import com.wutong.mvpsimple.data.entity.BaseEntity;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by 吴同 on 2016/8/15 0015.
 */
public class RxHelper {
    public static abstract class  BaseSubscriber<T extends BaseEntity> extends Subscriber<T> {
        @Override public abstract void onCompleted();
        @Override public abstract  void onError(Throwable e);
        @Override public abstract  void onNext(T o);
    }


    @Inject
    public RxHelper() {
    }



}


package com.wutong.mvpsimple.common.utils.okhttp;

import com.wutong.mvpsimple.BuildConfig;
import com.wutong.mvpsimple.common.eventbus.OnBusTokenError;
import com.wutong.mvpsimple.data.entity.BaseEntity;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by 吴同 on 2016/8/19 0019.
 */
public abstract class BaseRetrofitSubscriber<T extends BaseEntity> extends Subscriber<T> {
    /**
     * 返回实体为null，一般是服务器异常导致
     */
    public static class NullEntityException extends RuntimeException {
        public NullEntityException(String detailMessage) {
            super(detailMessage);
        }
    }

    /**
     * token失效异常
     */
    public static class TokenInvalidException extends RuntimeException {
        public TokenInvalidException(String detailMessage) {
            super(detailMessage);
        }
    }



    @Override public void onCompleted() {

    }

    @Override public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof TokenInvalidException) {
            onTokenError();
            return;
        } else if (e instanceof HttpException || e instanceof NullEntityException) {
            onHttpError();
            return;
        } else {
            onUnknowError(e);
        }


    }


    @Override public void onNext(T o) {
        if (o == null) {
            throw new NullEntityException("服务器出错了");
        }
        if (BuildConfig.TOKEN_ERROR_CODE.equals(o.getCode())) {
            throw new TokenInvalidException("登录信息已过期，请重新登录");
        }

        onSuccess(o);


    }

    /**
     * 请求开始
     */
    @Override public abstract void onStart();
    /**
     * 请求正常
     * @param o
     */
    protected abstract void onSuccess(T o);

    /**
     * 网络错误
     */
    public abstract void onHttpError();

    /**
     * 其他错误
     */
    protected abstract void onUnknowError(Throwable e);

    /**
     * token 错误
     */
    private void onTokenError() {
        EventBus.getDefault().post(new OnBusTokenError());
    }

}

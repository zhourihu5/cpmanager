package com.yiqiao.cpmanager.subscribers;

import com.yiqiao.cpmanager.BuildConfig;
import com.yiqiao.cpmanager.http.exception.ApiException;

import rx.Subscriber;

/**
 * @author YorkYu
 * @version V1.0
 * @Project: Retrofit2RxjavaDemo
 * @Package york.com.retrofit2rxjavademo.subscribers
 * @Description:
 * @time 2016/8/11 10:48
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {
    @Override
    public void onError(Throwable e) {
        if(e instanceof ApiException){
            onError((ApiException)e);
        }else{
            ApiException apiException=  new ApiException(e,1000);
            if(BuildConfig.DEBUG){
                e.printStackTrace();
                apiException.message=e.getMessage();
            }else{
                apiException.message="未知错误";
            }
            onError(apiException);
        }
    }

    /**
     * 错误回调
     */
    protected abstract void onError(ApiException ex);
}
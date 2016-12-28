package com.yiqiao.cpmanager.transformer;

import com.yiqiao.cpmanager.entity.HttpResult;
import com.yiqiao.cpmanager.http.ExceptionEngine;
import com.yiqiao.cpmanager.http.exception.ErrorType;
import com.yiqiao.cpmanager.http.exception.ServerException;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by York on 2016/7/23.
 */
public class PageErrorTransformer<T extends HttpResult> implements Observable.Transformer<T, T> {
    @Override
    public Observable<T> call(Observable<T> observable) {

        return observable
                .map(new Func1<T, T>() {
                    @Override
                    public T call(T t) {
                        if (t.getCode() != ErrorType.SUCCESS) throw new ServerException(t.getMessage(), t.getCode());
                        return t;
                    }
                })
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends T>>() {
                    @Override
                    public Observable<? extends T> call(Throwable throwable) {
                        //ExceptionEngine为处理异常的驱动器
                        throwable.printStackTrace();
                        return Observable.error(ExceptionEngine.handleException(throwable));
                    }
                });
    }

    public static <T extends HttpResult> PageErrorTransformer<T> create() {
        return new PageErrorTransformer<T>();
    }

    private static PageErrorTransformer instance = null;

    private PageErrorTransformer(){
    }
    /**
     * 双重校验锁单例(线程安全)
     */
    public static<T extends HttpResult> PageErrorTransformer<T> getInstance() {
        if (instance == null) {
            synchronized (PageErrorTransformer.class) {
                if (instance == null) {
                    instance = new PageErrorTransformer<>();
                }
            }
        }
        return instance;
    }
}
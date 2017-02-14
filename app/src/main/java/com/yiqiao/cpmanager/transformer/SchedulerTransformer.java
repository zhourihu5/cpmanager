package com.yiqiao.cpmanager.transformer;

import com.yiqiao.cpmanager.http.ExceptionEngine;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by York on 2016/7/23.
 */
public class SchedulerTransformer<T> implements Observable.Transformer<T, T> {
    @Override
    public Observable<T> call(Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends T>>() {
                    @Override
                    public Observable<? extends T> call(Throwable throwable) {
                        //ExceptionEngine为处理异常的驱动器
                        throwable.printStackTrace();
                        return Observable.error(ExceptionEngine.handleException(throwable));
                    }
                });
    }

    public static <T> SchedulerTransformer<T> create() {
        return new SchedulerTransformer<>();
    }

    private static SchedulerTransformer instance = null;

    private SchedulerTransformer(){
    }
    /**
     * 双重校验锁单例(线程安全)
     */
    public static<T> SchedulerTransformer<T> getInstance() {
        if (instance == null) {
            synchronized (SchedulerTransformer.class) {
                if (instance == null) {
                    instance = new SchedulerTransformer<>();
                }
            }
        }
        return instance;
    }
}
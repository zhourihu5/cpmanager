package com.yiqiao.cpmanager.transformer;

import com.yiqiao.cpmanager.entity.HttpResult;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by York on 2016/7/23.
 */
public class PageTransformer<T extends HttpResult> implements Observable.Transformer<T, T> {
    @Override
    public Observable<T> call(Observable<T> observable) {

        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .compose(PageErrorTransformer.<T>getInstance())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
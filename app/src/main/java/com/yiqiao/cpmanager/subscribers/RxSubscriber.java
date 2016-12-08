package com.yiqiao.cpmanager.subscribers;

import android.content.Context;
import android.util.Log;

import com.yiqiao.cpmanager.util.ToastUtil;

import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.util.DialogHelper;
import com.yiqiao.cpmanager.util.NetworkUtil;


/**
 * @author YorkYu
 * @version V1.0
 * @Project: Retrofit2RxjavaDemo
 * @Package york.com.retrofit2rxjavademo.subscribers
 * @Description:
 * @time 2016/8/11 10:54
 */
public abstract class RxSubscriber<T> extends BaseSubscriber<T> {
    public RxSubscriber(Context context) {
        this.mContext = context;
    }
    private Context mContext;
    private static final String TAG = RxSubscriber.class.getSimpleName();
    @Override
    public void onStart() {
        super.onStart();

        // if  NetworkAvailable no !   must to call onCompleted
        if (!NetworkUtil.isNetworkAvailable(mContext)) {
//            Toast.makeText(mContext, "当前无网络，请检查网络情况", Toast.LENGTH_SHORT).show();
            ToastUtil.shortShow("当前无网络，请检查网络情况");
            onCompleted();
        } else {
            DialogHelper.showProgressDlg(mContext, "正在加载数据");

        }
    }

    @Override
    public void onCompleted() {
        DialogHelper.stopProgressDlg();
    }

    @Override
    protected void onError(ApiException ex) {
        DialogHelper.stopProgressDlg();
        Log.d(TAG, "onError: " + ex.message + "code: " + ex.code);
//        Toast.makeText(mContext, ex.message , Toast.LENGTH_SHORT).show();
        ToastUtil.shortShow(ex.message);
    }

    @Override
    public abstract void onNext(T t);
}

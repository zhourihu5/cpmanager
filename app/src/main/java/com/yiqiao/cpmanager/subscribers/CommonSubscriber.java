package com.yiqiao.cpmanager.subscribers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.util.NetworkUtil;


/**
 * @author YorkYu
 * @version V1.0
 * @Project: Retrofit2RxjavaDemo
 * @Package york.com.retrofit2rxjavademo.subscribers
 * @Description:
 * @time 2016/8/11 10:54
 */
public abstract class CommonSubscriber<T> extends BaseSubscriber<T> {
    public CommonSubscriber(Context context) {
        this.mContext = context;
    }
    private static final String TAG = CommonSubscriber.class.getSimpleName();
    private Context mContext;
    @Override
    public void onStart() {
        super.onStart();

        // if  NetworkAvailable no !   must to call onCompleted
        if (!NetworkUtil.isNetworkAvailable(mContext)) {
            Toast.makeText(mContext, "当前无网络，请检查网络情况", Toast.LENGTH_SHORT).show();
            onCompleted();
        } else {
            Log.d(TAG, "network available");
        }
    }

    @Override
    public void onCompleted() {
        Log.d(TAG, "onCompleted~ ");
    }

    @Override
    protected void onError(ApiException ex) {
        Log.e(TAG, "onError: " + ex.message + "code: " + ex.code);
        Toast.makeText(mContext, ex.message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public abstract void onNext(T t);
}

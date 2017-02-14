package com.yiqiao.cpmanager.subscribers;

import com.yiqiao.cpmanager.http.exception.ApiException;


/**
 * @author YorkYu
 * @version V1.0
 * @Project: Retrofit2RxjavaDemo
 * @Package york.com.retrofit2rxjavademo.subscribers
 * @Description:
 * @time 2016/8/11 10:54
 */
public abstract class RxSubscriber<T> extends BaseSubscriber<T> {
    public RxSubscriber(Callback context) {
        this.mContext = context;
    }
    private Callback mContext;
    private static final String TAG = RxSubscriber.class.getSimpleName();
    @Override
    public void onStart() {
        super.onStart();

        // if  NetworkAvailable no !   must to call onCompleted
        if (!mContext.isNetworkAvailable()) {
//            Toast.makeText(mContext, "当前无网络，请检查网络情况", Toast.LENGTH_SHORT).show();
//            ToastUtil.shortShow("当前无网络，请检查网络情况");
            mContext.onNetUnAvailable();
            ApiException apiException=new ApiException(new Throwable("网络不可用"),400);
            apiException.message="网络不可用";
            onError(apiException);
        } else {
//            DialogHelper.showProgressDlg(mContext, "正在加载数据");
            mContext.onRequestStart();
        }
    }

    @Override
    public void onCompleted() {
//        DialogHelper.stopProgressDlg();
        mContext.onRequestCompleted();
    }

    @Override
    protected void onError(ApiException ex) {
//        DialogHelper.stopProgressDlg();
//        Log.d(TAG, "onError: " + ex.message + "code: " + ex.code);
//        Toast.makeText(mContext, ex.message , Toast.LENGTH_SHORT).show();
//        ToastUtil.shortShow(ex.message);
        mContext.onRequestError( ex);
    }

    @Override
    public abstract void onNext(T t);

    public interface Callback{

        boolean isNetworkAvailable();

        void onNetUnAvailable();

        void onRequestStart();

        void onRequestCompleted();

        void onRequestError(ApiException ex);
    }
}

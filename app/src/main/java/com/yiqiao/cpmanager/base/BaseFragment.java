package com.yiqiao.cpmanager.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.analytics.MobclickAgent;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.util.DialogHelper;
import com.yiqiao.cpmanager.util.LogUtils;
import com.yiqiao.cpmanager.util.NetworkUtil;
import com.yiqiao.cpmanager.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by codeest on 16/8/11.
 * 无MVP的Fragment基类
 */

public abstract class BaseFragment extends SupportFragment implements RxSubscriber.Callback {

    protected View mView;
    protected BaseActivity mActivity;
    protected Context mContext;
    private Unbinder mUnBinder;
    private boolean isInited = false;

    protected CompositeSubscription mCompositeSubscription;
    protected boolean isVisible;
    protected boolean isPrepared;
    protected boolean isFirst=true;

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }
    protected void addSubscrebe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }
    @Override
    public void onAttach(Context context) {
        mActivity = (BaseActivity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared=true;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
        if (savedInstanceState == null) {
            if (!isHidden()) {
                isInited = true;
                initEventAndData();
            }
        } else {
            if (!isSupportHidden()) {
                isInited = true;
                initEventAndData();
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!isInited && !hidden) {
            isInited = true;
            initEventAndData();
        }
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtils.e("setUserVisibleHint "+isVisibleToUser);

        if(getUserVisibleHint()){
            isVisible = true;
            lazyLoad();
        }else{
            isVisible = false;
//            onInvisible();
        }
    }


    protected boolean lazyLoad() {
        if(!isPrepared || !isVisible || !isFirst){
            return false;
        }
        if(isFirst){
            lazyLoadData();
            isFirst = false;
        }
        return true;
    }

    protected void lazyLoadData() {
        LogUtils.e("lazyLoadData");
    }

    protected String getPageName(){
        return  getClass().getSimpleName();
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart( getPageName());
        if(getUserVisibleHint()){
            setUserVisibleHint(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getPageName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unSubscribe();
    }
    protected void toActivity(Class clazz){
        Intent intent=new Intent(mActivity,clazz);
        startActivity(intent);
    }

    protected abstract int getLayoutId();
    protected abstract void initEventAndData();


    @Override
    public boolean isNetworkAvailable() {
        return NetworkUtil.isNetworkAvailable(mContext);
    }

    @Override
    public void onNetUnAvailable() {
        ToastUtil.shortShow("当前无网络，请检查网络情况");
    }

    @Override
    public void onRequestStart() {
        DialogHelper.showProgressDlg(mContext, "正在加载数据");
    }

    @Override
    public void onRequestCompleted() {
        DialogHelper.stopProgressDlg();
    }

    @Override
    public void onRequestError(ApiException ex) {
        DialogHelper.stopProgressDlg();
        ToastUtil.shortShow(ex.message);
    }
}

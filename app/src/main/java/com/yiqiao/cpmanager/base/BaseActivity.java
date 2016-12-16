package com.yiqiao.cpmanager.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.utils.BarUtils;
import com.umeng.analytics.MobclickAgent;
import com.yiqiao.cpmanager.app.App;
import com.yiqiao.cpmanager.ui.activity.LoginActivity;
import com.yiqiao.cpmanager.util.SharedPreferenceUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by codeest on 16/8/11.
 * 无MVP的activity基类
 */

public abstract class BaseActivity extends SupportActivity {

    protected Activity mContext;

    private Unbinder mUnBinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
//        setContentView(R.layout.toolbar_layout_public);
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        App.getInstance().addActivity(this);
        setStatusBar();
        initEventAndData();
    }

    protected void setStatusBar() {
//        StatusBarUtil.setColor(this, ContextCompat.getColor(this,R.color.colorPrimary),0);
        BarUtils.setColor(this, Color.WHITE,0);
        BarUtils.StatusBarLightMode(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
        mUnBinder.unbind();
        unSubscribe();
    }

    protected abstract int getLayout();

    protected abstract void initEventAndData();

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition();
            } else {
                finish();
            }
        }
    }

    protected CompositeSubscription mCompositeSubscription;

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
    protected void toActivity(Class clazz){
        Intent intent=new Intent(this,clazz);
        startActivity(intent);
    }
    protected boolean isLogin(){
        boolean isLogin= SharedPreferenceUtil.getLoginState();
        if(!isLogin){
            toActivity(LoginActivity.class);
        }
        return isLogin;
//        return true;
    }
}

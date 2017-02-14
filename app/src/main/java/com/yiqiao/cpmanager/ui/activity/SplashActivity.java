package com.yiqiao.cpmanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.blankj.utilcode.utils.SPUtils;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.app.Constants;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.util.SharedPreferenceUtil;

import butterknife.BindView;

/**
 * Created by Xu on 2016/11/23.
 * 关于我们
 */


public class SplashActivity extends BaseActivity {

    @BindView(R.id.llContent)
    LinearLayout llContent;

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setStatusBar() {
//        super.setStatusBar();
    }

    @Override
    protected void initEventAndData() {
//        AlphaAnimation animation = new AlphaAnimation(0.5f, 1.0f);
//        animation.setDuration(2000);
//        llContent.setAnimation(animation);
//        animation.setAnimationListener(new Animation.AnimationListener() {
//                                           @Override
//                                           public void onAnimationStart(Animation animation) {
//                                           }
//
//                                           @Override
//                                           public void onAnimationEnd(Animation animation) {
//                                               jump();
//
//                                           }
//
//                                           @Override
//                                           public void onAnimationRepeat(Animation animation) {
//                                               //linearLayout.setVisibility(View.GONE);
//                                           }
//                                       }
//        );
        llContent.postDelayed(new Runnable() {
            @Override
            public void run() {
                jump();
            }
        },1000);

    }

    private void jump() {
        // 判断是否是第一次开启应用
        SPUtils spUtils = SharedPreferenceUtil.getAppSp();
        boolean isFirstOpen = spUtils.getBoolean(Constants.FIRST_OPEN, true);
        // 如果是第一次启动，则先进入功能引导页
        if (isFirstOpen) {
            Intent intent = new Intent(mContext, GuidePageActivity.class);
            startActivity(intent);
            finish();
            return;
        } else {
            toActivity(MainActivity.class);
            finish();
        }
    }

}

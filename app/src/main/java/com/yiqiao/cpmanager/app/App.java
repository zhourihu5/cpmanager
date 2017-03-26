package com.yiqiao.cpmanager.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;
import android.util.Config;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.baidu.location.service.LocationService;
import com.blankj.utilcode.utils.SPUtils;
import com.github.moduth.blockcanary.BlockCanary;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.yiqiao.cpmanager.BuildConfig;
import com.yiqiao.cpmanager.component.CrashHandler;
import com.yiqiao.cpmanager.util.SharedPreferenceUtil;
import com.yiqiao.cpmanager.widget.AppBlockCanaryContext;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
//import cn.xiaoneng.uiapi.Ntalker;

/**
 * Created by codeest on 2016/8/2.
 */
public class App extends Application{

    private static App instance;
    private Set<Activity> allActivities;

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
//    public static float DIMEN_RATE = -1.0F;
//    public static int DIMEN_DPI = -1;

    public static synchronized App getInstance() {
        return instance;
    }

    static {
        AppCompatDelegate.setDefaultNightMode(// theme to be difined
                AppCompatDelegate.MODE_NIGHT_NO);
    }
    public LocationService locationService;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //初始化屏幕宽高
        getScreenSize();

        //初始化日志
        if(BuildConfig.DEBUG){
//            Logger.init(getPackageName()).hideThreadInfo();
            Logger.init(getPackageName());
//            SPUtils spUtils= SharedPreferenceUtil.getAppSp();//todo test
//            spUtils .putInt(Constants.USER_ID,3876251);//todo test
        }else {
            Logger.init(getPackageName()).logLevel(LogLevel.NONE);
        }

        //初始化错误收集
        CrashHandler.init(new CrashHandler(getApplicationContext()));

        //初始化内存泄漏检测
        LeakCanary.install(this);

        //初始化过度绘制检测
        BlockCanary.install(this, new AppBlockCanaryContext()).start();

        //初始化tbs x5 webview
        QbSdk.allowThirdPartyAppDownload(true);
        QbSdk.initX5Environment(getApplicationContext(), QbSdk.WebviewInitType.FIRSTUSE_AND_PRELOAD, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
            }

            @Override
            public void onViewInitFinished(boolean b) {
            }
        });

        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new LocationService(getApplicationContext());
//        SDKInitializer.initialize(getApplicationContext());
        JPushInterface.setDebugMode(BuildConfig.DEBUG);
        JPushInterface.init(this);

        com.umeng.socialize.Config.DEBUG = BuildConfig.DEBUG;
        UMShareAPI.get(this);
        String siteid="kf_9328";
//        Ntalker.getInstance().initSDK(this,siteid, "46F2265D-96CF-4DAC-87CF-6B49A3DB6B5D");
//        Ntalker.getInstance().enableDebug(BuildConfig.DEBUG);
    }
    //各个平台的配置，建议放在全局Application或者程序入口
    {
        //微信 wx12342956d1cab4f9,a5ae111de7d9ea137e88a5e02c07c94d
//        PlatformConfig.setWeixin("wxf1bea8e164549611", "wxf1bea8e164549611eqiao123661234");
        PlatformConfig.setWeixin("wxf1bea8e164549611", "eqiao123661234");
        PlatformConfig.setQQZone("1105530045", "NoWnQVJkW4aXWXEL");
    }

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<Activity>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public void getScreenSize() {
        WindowManager windowManager = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
//        DIMEN_RATE = dm.density / 1.0F;
//        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if(SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }


}

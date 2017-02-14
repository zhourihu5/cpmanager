package com.yiqiao.cpmanager.util;

import com.orhanobut.logger.Logger;
import com.yiqiao.cpmanager.BuildConfig;

/**
 * Created by codeest on 2016/8/3.
 */
public class LogUtils {

    public static boolean isDebug = BuildConfig.DEBUG;
    private static final String TAG = "LogUtils";

    public static void e(String msg,Object o) {
        if(isDebug) {
            Logger.e(msg, o);
        }
    }

    public static void e(String msg) {
        Logger.e(msg);
    }

    public static void w(String tag,Object o) {
        if(isDebug) {
            Logger.w(tag, o);
        }
    }

    public static void w(Object o) {
        LogUtils.w(TAG,o);
    }

    public static void d(String msg) {
        if(isDebug) {
            Logger.d(msg);
        }
    }

    public static void i(String msg) {
        if(isDebug) {
            Logger.i(msg);
        }
    }
}

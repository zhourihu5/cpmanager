package com.yiqiao.cpmanager.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.yiqiao.cpmanager.app.App;
import com.yiqiao.cpmanager.app.Constants;

/**
 * Created by codeest on 16/8/31.
 */

public class SharedPreferenceUtil {


    private static final String SHAREDPREFERENCES_NAME = "my_sp";

    public static SharedPreferences getAppSp() {
        return App.getInstance().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static boolean getLoginState() {
        return getAppSp().getBoolean(Constants.LOGIN, false);
    }

    public static void setLoginState(boolean state) {
        getAppSp().edit().putBoolean(Constants.LOGIN, state).commit();
    }

    public static boolean getNoImageState() {
        return false;
    }
}

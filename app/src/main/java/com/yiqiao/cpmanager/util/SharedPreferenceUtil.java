package com.yiqiao.cpmanager.util;

import com.blankj.utilcode.utils.SPUtils;
import com.yiqiao.cpmanager.app.App;
import com.yiqiao.cpmanager.app.Constants;

/**
 * Created by codeest on 16/8/31.
 */

public class SharedPreferenceUtil {


    private static final String SHAREDPREFERENCES_NAME = "my_sp";

    public static SPUtils getAppSp() {
//        return App.getInstance().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        return new SPUtils(App.getInstance(), SHAREDPREFERENCES_NAME);
    }

//    public static boolean getLoginState() {
//        return getAppSp().getBoolean(Constants.LOGIN, false);
//    }
//
//    public static void setLoginState(boolean state) {
//        getAppSp().putBoolean(Constants.LOGIN, state);
//    }

    public static boolean getNoImageState() {
        return false;
    }
}

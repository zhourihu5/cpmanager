package com.yiqiao.cpmanager.util;

import com.blankj.utilcode.utils.ToastUtils;
import com.yiqiao.cpmanager.app.App;

/**
 * Created by codeest on 2016/8/4.
 */
public class ToastUtil {


    public static void shortShow(String msg) {
        ToastUtils.showShortToastSafe(App.getInstance(),msg);
    }

}

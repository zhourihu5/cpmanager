package com.yiqiao.cpmanager.util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.ToastUtils;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.app.App;

/**
 * Created by codeest on 2016/8/4.
 */
public class ToastUtil {


    public static void shortShow(String msg) {
        ToastUtils.showShortToastSafe(App.getInstance(),msg);
    }

}

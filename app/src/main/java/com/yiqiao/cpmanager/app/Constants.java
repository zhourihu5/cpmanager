package com.yiqiao.cpmanager.app;

import java.io.File;

/**
 * Created by codeest on 2016/8/3.
 */
public class Constants {

    //================= TYPE ====================

    public static final int TYPE_ZHIHU = 101;

    public static final int TYPE_ANDROID = 102;

    public static final int TYPE_IOS = 103;

    public static final int TYPE_WEB = 104;

    public static final int TYPE_GIRL = 105;

    public static final int TYPE_WECHAT = 106;

    public static final int TYPE_GANK = 107;

    public static final int TYPE_SETTING = 108;

    public static final int TYPE_LIKE = 109;

    public static final int TYPE_ABOUT = 110;

    //================= KEY ====================

//    public static final String KEY_API = "f95283476506aa756559dd28a56f0c0b"; //需要APIKEY请去 http://apistore.baidu.com/ 申请,复用会减少访问可用次数
    public static final String KEY_API = "52b7ec3471ac3bec6846577e79f20e4c"; //需要APIKEY请去 http://www.tianapi.com/#wxnew 申请,复用会减少访问可用次数

    //================= PATH ====================

//    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_DATA = App.getInstance().getExternalCacheDir().getAbsolutePath() + File.separator ;

    public static final String PATH_CACHE = PATH_DATA+ "data"+ File.separator;

    public static final String PATH_CACHE_NET = PATH_CACHE + "NetCache";
    public static final String LOG_PATH =PATH_CACHE + "log"+File.separator;
    public static final String UPDATE_FILE_PATH = PATH_CACHE + File.separator + "update"+ File.separator+"cpmanager.apk";

    //================= UMENG ====================

    public static final String EVENT_TAB_HOME = "tab_home";

    //================= PREFERENCE ====================

    public static final String LOGIN = "login";

}

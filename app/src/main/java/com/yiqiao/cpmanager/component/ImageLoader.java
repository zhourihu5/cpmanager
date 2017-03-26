package com.yiqiao.cpmanager.component;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yiqiao.cpmanager.util.SharedPreferenceUtil;

/**
 * Created by codeest on 2016/8/2.
 */
public class ImageLoader {

    public static void load(Context context, String url, ImageView iv) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
        if (!SharedPreferenceUtil.getNoImageState()) {
            Glide.with(context).load(url).crossFade().centerCrop().into(iv);
        }
    }
    public static void load(Context context, String url, ImageView iv,int drawable ) {    //使用Glide加载图片
        if (!SharedPreferenceUtil.getNoImageState()) {
            Glide.with(context).load(url).crossFade().centerCrop().placeholder(drawable).error(drawable).into(iv);
        }
    }


    public static void loadNetOnly(Context context, String url, ImageView iv) {    //不缓存，全部从网络加载
        Glide.with(context).load(url).crossFade().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
    }
}

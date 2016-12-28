package com.yiqiao.cpmanager.util;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Xu on 2016/12/16.
 */

public class AssetsUtil {


    public static String getAssetsText(Context context,String fileName){
        String text= null;
        try {
            InputStream is=context.getAssets().open(fileName);
            text = FileUtils.readTextInputStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

}

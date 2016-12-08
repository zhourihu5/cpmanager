package com.yiqiao.cpmanager.component;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.yiqiao.cpmanager.BuildConfig;
import com.yiqiao.cpmanager.app.App;
import com.yiqiao.cpmanager.app.Constants;
import com.yiqiao.cpmanager.ui.activity.MainActivity;
import com.yiqiao.cpmanager.util.FileUtils;
import com.yiqiao.cpmanager.util.LogUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by codeest on 2016/8/3.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static Thread.UncaughtExceptionHandler defaultHandler = null;

    private Context context = null;

    private final String TAG = CrashHandler.class.getSimpleName();

    public CrashHandler(Context context) {
        this.context = context;
    }

    /**
     * 初始化,设置该CrashHandler为程序的默认处理器
     */
    public static void init(CrashHandler crashHandler) {
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(crashHandler);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        LogUtils.e(TAG, ex.toString());
        LogUtils.e(TAG, collectCrashDeviceInfo());
        LogUtils.e(TAG, getCrashInfo(ex));
        if(BuildConfig.DEBUG){
            saveLog(ex);
        }
        // 调用系统错误机制
        defaultHandler.uncaughtException(thread, ex);
//        ToastUtil.shortShow("抱歉,程序发生异常即将退出");

        //todo 重新启动
        Intent intent = new Intent(App.getInstance(), MainActivity.class);
        App.getInstance().startActivity(intent);

        App.getInstance().exitApp();
    }

    /**
     * 得到程序崩溃的详细信息
     */
    public String getCrashInfo(Throwable ex) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        ex.setStackTrace(ex.getStackTrace());
        ex.printStackTrace(printWriter);
        return result.toString();
    }
    /**
     * 保存异常日志
     *
     * @param excp
     */
    public void saveLog(Throwable excp) {
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String errorlog = simpleDateFormat.format(date);
        String savePath = "";
        String logFilePath = "";
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            //判断是否挂载了SD卡
            if (FileUtils.hasSdcard()) {
                savePath = Constants.LOG_PATH;
                File file = new File(savePath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                logFilePath = savePath + errorlog;
            } else {
                return;
            }


            File logFile = new File(logFilePath);
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            fw = new FileWriter(logFile, true);
            pw = new PrintWriter(fw);
            pw.println("--------------------" + errorlog + "---------------------");
            excp.printStackTrace(pw);
            pw.println("-------------------- device info ---------------------");
            pw.println(collectCrashDeviceInfo());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileUtils.closeQuietly(pw, fw);
        }
    }
    /**
     * 收集程序崩溃的设备信息
     */
    public String collectCrashDeviceInfo() {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            String versionName = pi.versionName;
            String model = android.os.Build.MODEL;
            String androidVersion = android.os.Build.VERSION.RELEASE;
            String manufacturer = android.os.Build.MANUFACTURER;
            return versionName + "  " + model + "  " + androidVersion + "  " + manufacturer;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

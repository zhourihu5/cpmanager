package com.yiqiao.cpmanager.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.PermissionManager.TPermissionType;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.yiqiao.cpmanager.base.BaseActivity;

/**
 * 继承这个类来让Activity获取拍照的能力<br>
 * Author: crazycodeboy
 * Date: 2016/9/21 0007 20:10
 * Version:3.0.0
 * 技术博文：http://www.cboy.me
 * GitHub:https://github.com/crazycodeboy
 * Eamil:crazycodeboy@gmail.com
 */
public abstract class MyTakePhotoActivity extends BaseActivity implements TakePhoto.TakeResultListener,InvokeListener{
    private static final String TAG = TakePhotoActivity.class.getName();
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(this,type,invokeParam,this);
    }

    /**
     *  获取TakePhoto实例
     * @return
     */
    public TakePhoto getTakePhoto(){
        if (takePhoto==null){
            takePhoto= (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this,this));
        }
        return takePhoto;
    }
    @Override
    public void takeSuccess(TResult result) {
//        Log.i(TAG,"takeSuccess：" + result.getImage().getPath());
    }
    @Override
    public void takeFail(TResult result,String msg) {
//        Log.i(TAG, "takeFail:" + msg);
    }
    @Override
    public void takeCancel() {
//        Log.i(TAG, getResources().getString(com.jph.takephoto.R.string.msg_operation_canceled));
    }

    @Override
    public TPermissionType invoke(InvokeParam invokeParam) {
        TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }
}

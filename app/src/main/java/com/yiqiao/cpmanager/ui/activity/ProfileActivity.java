package com.yiqiao.cpmanager.ui.activity;

import android.animation.ValueAnimator;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.utils.SPUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.app.Constants;
import com.yiqiao.cpmanager.base.MyTakePhotoActivity;
import com.yiqiao.cpmanager.component.ImageLoader;
import com.yiqiao.cpmanager.entity.CreateOrderRequestVo;
import com.yiqiao.cpmanager.entity.CreateOrderVo;
import com.yiqiao.cpmanager.entity.HttpResult;
import com.yiqiao.cpmanager.entity.SkuDetailVo;
import com.yiqiao.cpmanager.http.RetrofitHelper;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.transformer.PageTransformer;
import com.yiqiao.cpmanager.ui.fragment.GoodsDetailFragment;
import com.yiqiao.cpmanager.util.LogUtils;
import com.yiqiao.cpmanager.util.SharedPreferenceUtil;
import com.yiqiao.cpmanager.util.SystemUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import rx.Subscription;

/**
 * Created by Xu on 2016/11/23.
 */

public class ProfileActivity extends MyTakePhotoActivity implements
        EasyPermissions.PermissionCallbacks{

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.ivLogo)
    ImageView ivLogo;
    @BindView(R.id.llHead)
    LinearLayout llHead;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.llModifyPwd)
    LinearLayout llModifyPwd;

    PopMenu popMenu;
    @BindView(R.id.viewMain)
    LinearLayout viewMain;

    TakePhotoHelper takePhotoHelper;
    String phone;
    @Override
    protected int getLayout() {
        return R.layout.activity_profile;
    }

    @Override
    protected void initEventAndData() {
        tvTitle.setText("账户信息");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
       SPUtils spUtils= SharedPreferenceUtil.getAppSp();
        String uname=spUtils.getString(Constants.USER_NAME);
        phone=spUtils.getString(Constants.USER_PHONE);
        String img=spUtils.getString(Constants.USER_IMG);

        tvName.setText(uname);
        tvPhone.setText(phone);
        ImageLoader.load(mContext,img,ivLogo,R.drawable.img_head);
    }

    @OnClick({R.id.ivBack, R.id.llHead, R.id.llModifyPwd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressedSupport();
                break;
            case R.id.llHead:
                if(popMenu==null){
                    popMenu = new PopMenu();
                }
                popMenu.showAtLocation(viewMain, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
                setBackgroundAlpha(1,alpha,200);
                break;
            case R.id.llModifyPwd:
                toActivity(ModifyPwdActivity.class);
                break;
        }
    }

    private static final int RC_CAMERA_PERM = 123;

//    @AfterPermissionGranted(RC_CAMERA_PERM)
//    public void cameraTask() {
//        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
//            // Have permission, do the thing!
//            Toast.makeText(this, "TODO: Camera things", Toast.LENGTH_LONG).show();
//        } else {
//            // Ask for one permission
//            EasyPermissions.requestPermissions(this, "该应用需要相机权限拍照上传图片",
//                    RC_CAMERA_PERM, Manifest.permission.CAMERA);
//        }
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }
    //todo 上传头像
    private void uploadPic(final File file) {//商品详情接口 套餐详情单独
        LogUtils.e("uploadPic");
//        id="75";
        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

// MultipartBody.Part  和后端约定好Key，这里的partName是用image
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("pic", file.getName(), requestFile);

// 添加描述
        String descriptionString = phone;
        RequestBody description =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), descriptionString);



        Subscription rxSubscription = RetrofitHelper.getCpMgrApiService()
                .photoAdd(description,body)
                .compose(new PageTransformer<HttpResult<String>>())
                .subscribe(new RxSubscriber<HttpResult<String>>(ProfileActivity.this) {
                    // 必须重写
                    @Override
                    public void onNext(HttpResult<String> contentBeen) {

                        Glide.with(mContext).load(file).into(ivLogo);
                    }


                    // 无需设置可以不用重写
                    // !!!!注意参数为ApiException 类型，要不要写在Throwable那个了
                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                    }

                    // 无需设置可以不用重写
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void onRequestStart() {
        super.onRequestStart();
        initProgressDialog();
        progressDialog.show();
    }

    @Override
    public void takeSuccess(TResult result) {
       ArrayList<TImage> imgs=result.getImages();
        for (int i=0;i<imgs.size();i++){
            TImage image=imgs.get(i);
            uploadPic(new File(image.getPath()));

        }

    }
    @Override
    public void takeFail(TResult result,String msg) {
    }
    @Override
    public void takeCancel() {
    }

    class PopMenu extends PopupWindow {


        @BindView(R.id.tvTakephoto)
        TextView tvTakephoto;
        @BindView(R.id.tvGallery)
        TextView tvGallery;
        @BindView(R.id.tvCancel)
        TextView tvCancel;

        public PopMenu() {
            super();
            init();
        }

        public PopMenu(int width, int height) {
            super(width, height);
            init();
        }

        private void init() {
            View contentView = View.inflate(mContext, R.layout.pop_photo, null);
            ButterKnife.bind(this, contentView);
            setContentView(contentView);
            setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
            this.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
            this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            //点击空白处时，隐藏掉pop窗口
            this.setFocusable(true);
            this.setBackgroundDrawable(new BitmapDrawable());
            //添加弹出、弹入的动画
            setAnimationStyle(R.style.Popupwindow);
            setOnDismissListener(new PopOnDismissListener());

        }

        @OnClick({R.id.tvTakephoto, R.id.tvGallery, R.id.tvCancel})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tvTakephoto://拍照权限？
//                    cameraTask();
//                    break;
                case R.id.tvGallery:
                    if(takePhotoHelper==null){
                        takePhotoHelper=new TakePhotoHelper();
                    }
                    takePhotoHelper.onClick(view,getTakePhoto());
                    dismiss();
                    break;
                case R.id.tvCancel:
                    dismiss();
                    break;
            }
        }
    }

    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     *
     * @author cg
     */
    class PopOnDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
//            backgroundAlpha(1f);
            setBackgroundAlpha(alpha,1,300);
        }
    }



    class TakePhotoHelper{
        public void onClick(View view,TakePhoto takePhoto) {
            File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
            if (!file.getParentFile().exists())file.getParentFile().mkdirs();
            Uri imageUri = Uri.fromFile(file);

            configCompress(takePhoto);
            switch (view.getId()){
                case R.id.tvGallery:
                    takePhoto.onPickFromGalleryWithCrop(imageUri,getCropOptions());
//                            takePhoto.onPickFromGallery();
                    break;
                case R.id.tvTakephoto:
                    takePhoto.onPickFromCaptureWithCrop(imageUri,getCropOptions());
//                    takePhoto.onPickFromCapture(imageUri);
                    break;
                default:
                    break;
            }
        }
        private void configCompress(TakePhoto takePhoto){

            int maxSize=1024000;
            int maxPixel=800;
            CompressConfig config= new CompressConfig.Builder().setMaxPixel(maxSize).setMaxPixel(maxPixel).create();
            takePhoto.onEnableCompress(config,true);
        }
        private CropOptions getCropOptions(){
            int height= 800;
            int width= 800;
            boolean withWonCrop=false;

            CropOptions.Builder builder=new CropOptions.Builder();
            builder.setAspectX(width).setAspectY(height);
//                builder.setOutputX(width).setOutputY(height);
            builder.setWithOwnCrop(withWonCrop);
            return builder.create();
        }
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
//    public void backgroundAlpha(float bgAlpha) {
//        WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
//        lp.alpha = bgAlpha; //0.0-1.0
//        mContext.getWindow().setAttributes(lp);
//    }
    float alpha=0.75f;
    private void setBackgroundAlpha(float from, float to, int duration) {
        final WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
        ValueAnimator animator = ValueAnimator.ofFloat(from, to);
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                lp.alpha = (float) animation.getAnimatedValue();
                mContext.getWindow().setAttributes(lp);
            }
        });
        animator.start();
    }
}

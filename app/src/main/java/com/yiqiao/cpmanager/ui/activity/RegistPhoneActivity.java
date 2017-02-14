package com.yiqiao.cpmanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.RegexUtils;
import com.blankj.utilcode.utils.SPUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.google.gson.Gson;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.app.Constants;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.entity.HttpResult;
import com.yiqiao.cpmanager.entity.LoginRequestVo;
import com.yiqiao.cpmanager.entity.LoginVo;
import com.yiqiao.cpmanager.entity.PhoneVerifyRequestVo;
import com.yiqiao.cpmanager.http.RetrofitHelper;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.http.exception.ErrorType;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.transformer.DefaultTransformer;
import com.yiqiao.cpmanager.transformer.SchedulerTransformer;
import com.yiqiao.cpmanager.util.SharedPreferenceUtil;
import com.yiqiao.cpmanager.util.SystemUtil;
import com.yiqiao.cpmanager.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;

/**
 * Created by Xu on 2016/11/23.
 * 注册，快捷登录，忘记密码，修改密码等界面
 */

public class RegistPhoneActivity extends BaseActivity {

    int type;
    public static final int TYPE_QUICK_LOGIN_PHONE = 1;
    public static final int TYPE_REGIST_PHONE = 0;
    public static final int TYPE_FIND_PWD = 2;
    public static final String TYPE_STR = "type";
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.btNext)
    Button btNext;

    @Override
    protected int getLayout() {
        return R.layout.activity_regist_phone;
    }

    @Override
    protected void initEventAndData() {
        type = getIntent().getIntExtra(TYPE_STR, TYPE_REGIST_PHONE);
        switch (type) {
            case TYPE_REGIST_PHONE:
                tvTitle.setText("注册");
                break;
            case TYPE_QUICK_LOGIN_PHONE:
                tvTitle.setText("手机号登录");
                break;
            case TYPE_FIND_PWD:
                tvTitle.setText("忘记密码");
                break;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ivBack, R.id.btNext})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressedSupport();
                break;
            case R.id.btNext:
                String phone=etPhone.getText().toString();
                if(StringUtils.isEmpty(phone)){
                    ToastUtil.shortShow("手机号不能为空");
                    return;
                }
                if(!RegexUtils.isMobileSimple(phone)){
                    ToastUtil.shortShow("请输入正确的手机号");
                    return;
                }
                //todo regist is different from quicklongin
                remoteVerifyPhone(phone);
                break;
        }
    }
    void  remoteVerifyPhone(final String phone){
        PhoneVerifyRequestVo loginRequestVo=new PhoneVerifyRequestVo();
        loginRequestVo.setPhone(phone);
        String sysCode="111";
        String timeStamp=String.valueOf(System.currentTimeMillis()/1000);
        String param=new Gson().toJson(loginRequestVo);
        String sign= SystemUtil.getSign(sysCode,timeStamp,param);


        Subscription rxSubscription = RetrofitHelper.getCpMgrApiService()
                .verifyPhone(sysCode,timeStamp,param,sign)
//                .compose(new DefaultTransformer<LoginVo>())
                .compose(SchedulerTransformer.<HttpResult<LoginVo>>getInstance())
                .subscribe(new RxSubscriber<HttpResult<LoginVo>>(mContext) {
                    // 必须重写
                    @Override
                    public void onNext(HttpResult<LoginVo> contentBeen) {
                        switch (type) {
                            case TYPE_FIND_PWD:
                            case TYPE_REGIST_PHONE:
                                //todo 服务器校验是否已注册了
                                if(contentBeen.getCode()== ErrorType.SUCCESS){
                                    ToastUtil.shortShow("用户已注册");
                                    return;
                                }
                                if(contentBeen.getCode()!=101001){//用户不存在 才能注册
                                    ToastUtil.shortShow(contentBeen.getMessage());
                                    return;
                                }
                                break;
                            case TYPE_QUICK_LOGIN_PHONE:
                                if(contentBeen.getCode()!= ErrorType.SUCCESS){
                                    ToastUtil.shortShow(contentBeen.getMessage());
                                    return;
                                }
                                break;

                        }

                            //todo save img,name etc user's information
                            Intent intent=getIntent();
                        intent.setClass(mContext,RegistCodeActivity.class);
                        intent.putExtra(RegistCodeActivity.PHONE,phone);
                        startActivity(intent);
                        finish();
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
}

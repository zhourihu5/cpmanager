package com.yiqiao.cpmanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.SPUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.google.gson.Gson;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.app.Constants;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.entity.CodeRequestVo;
import com.yiqiao.cpmanager.entity.CodeVerifyRequestVo;
import com.yiqiao.cpmanager.entity.LoginRequestVo;
import com.yiqiao.cpmanager.entity.LoginVo;
import com.yiqiao.cpmanager.entity.PhoneVerifyRequestVo;
import com.yiqiao.cpmanager.http.RetrofitHelper;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.transformer.DefaultTransformer;
import com.yiqiao.cpmanager.util.SharedPreferenceUtil;
import com.yiqiao.cpmanager.util.SystemUtil;
import com.yiqiao.cpmanager.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;

/**
 * Created by Xu on 2016/11/23.
 * 关于我们
 */

public class RegistCodeActivity extends BaseActivity {

    String phone;
    public static final String PHONE = "phone";
    public static final String VERI_CODE_KEY = "veriCode";
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.btLogin)
    Button btLogin;

    int type;
    @BindView(R.id.tvCountdown)
    TextView tvCountdown;

    @Override
    protected int getLayout() {
        return R.layout.activity_regist_code;
    }

    @Override
    protected void initEventAndData() {


        phone = getIntent().getStringExtra(PHONE);
        type = getIntent().getIntExtra(RegistPhoneActivity.TYPE_STR, RegistPhoneActivity.TYPE_REGIST_PHONE);
        switch (type) {
            case RegistPhoneActivity.TYPE_REGIST_PHONE:
                tvTitle.setText("注册");
                btLogin.setText("下一步");
                break;
            case RegistPhoneActivity.TYPE_QUICK_LOGIN_PHONE:
                tvTitle.setText("手机号登录");
                btLogin.setText("快捷登录");
                break;
            case RegistPhoneActivity.TYPE_FIND_PWD:
                tvTitle.setText("忘记密码");
                btLogin.setText("下一步");
                break;
        }
        onClick(tvCountdown);
        getCode();

    }
    private void getCode() {
        CodeRequestVo loginRequestVo=new CodeRequestVo();
        loginRequestVo.setPhone(phone);
        String sysCode="111";
        String timeStamp=String.valueOf(System.currentTimeMillis()/1000);
        String param=new Gson().toJson(loginRequestVo);
        String sign= SystemUtil.getSign(sysCode,timeStamp,param);


        Subscription rxSubscription = RetrofitHelper.getCpMgrApiService()
                .getCode(sysCode,timeStamp,param,sign)
                .compose(new DefaultTransformer<String>())
                .subscribe(new RxSubscriber<String>(mContext) {
                    // 必须重写
                    @Override
                    public void onNext(String contentBeen) {

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
    void  remoteVerifyCode(final String phone,String code){
        CodeVerifyRequestVo loginRequestVo=new CodeVerifyRequestVo();
        loginRequestVo.setPhone(phone);
        loginRequestVo.setCodeNum(code);
        String sysCode="111";
        String timeStamp=String.valueOf(System.currentTimeMillis()/1000);
        String param=new Gson().toJson(loginRequestVo);
        String sign= SystemUtil.getSign(sysCode,timeStamp,param);


        Subscription rxSubscription = RetrofitHelper.getCpMgrApiService()
                .verifyPhone(sysCode,timeStamp,param,sign)
                .compose(new DefaultTransformer<LoginVo>())
                .subscribe(new RxSubscriber<LoginVo>(mContext) {
                    // 必须重写
                    @Override
                    public void onNext(LoginVo contentBeen) {
                        if(contentBeen!=null){
                            SPUtils spUtils= SharedPreferenceUtil.getAppSp();
                            spUtils .putInt(Constants.USER_ID,contentBeen.getCustomerId());
                            spUtils.putString(Constants.USER_NAME,contentBeen.getUsername());
                            spUtils.putString(Constants.USER_PHONE,contentBeen.getPhone());
                        }
                        switch (type) {
                            case RegistPhoneActivity.TYPE_FIND_PWD:
                            case RegistPhoneActivity.TYPE_REGIST_PHONE:

                                Intent intent = getIntent();
                                intent.setClass(mContext, RegistPwdActivity.class);
                                intent.putExtra(RegistCodeActivity.VERI_CODE_KEY, etCode.getText().toString());
                                startActivity(intent);
                                finish();
                                break;
                            case RegistPhoneActivity.TYPE_QUICK_LOGIN_PHONE:
                                //TODO 快速登录
                                finish();
                                break;

                        }
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
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @OnClick({R.id.ivBack, R.id.tvCountdown, R.id.btLogin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressedSupport();
                break;
            case R.id.btLogin:
                //todo 验证码
                String code=etCode.getText().toString();
                if(StringUtils.isEmpty(code)){
                    ToastUtil.shortShow("请输入验证码");
                    return;
                }
                remoteVerifyCode(phone,code);
                break;
            case R.id.tvCountdown:
                view.setEnabled(false);
                timer.start();

                break;
        }
    }

    CountDownTimer timer = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            tvCountdown.setText(millisUntilFinished/1000 + "秒");
        }

        @Override
        public void onFinish() {
            tvCountdown.setEnabled(true);
            tvCountdown.setText("发送验证码");
        }
    };


}

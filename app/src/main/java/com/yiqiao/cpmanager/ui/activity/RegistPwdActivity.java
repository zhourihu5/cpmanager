package com.yiqiao.cpmanager.ui.activity;

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
import com.yiqiao.cpmanager.entity.LoginRequestVo;
import com.yiqiao.cpmanager.entity.LoginVo;
import com.yiqiao.cpmanager.entity.RegistRequestVo;
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

public class RegistPwdActivity extends BaseActivity {

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.btRegist)
    Button btRegist;
    @BindView(R.id.tvAgreement)
    TextView tvAgreement;
    @BindView(R.id.llAgreement)
    LinearLayout llAgreement;
    private String phone;
    private int type;


    String veriCode;


    @Override
    protected int getLayout() {
        return R.layout.activity_regist_pwd;
    }

    @Override
    protected void initEventAndData() {
        phone = getIntent().getStringExtra(RegistCodeActivity.PHONE);
        veriCode=getIntent().getStringExtra(RegistCodeActivity.VERI_CODE_KEY);
        type = getIntent().getIntExtra(RegistPhoneActivity.TYPE_STR, RegistPhoneActivity.TYPE_REGIST_PHONE);
        switch (type) {
            case RegistPhoneActivity.TYPE_REGIST_PHONE:
                tvTitle.setText("注册");
                btRegist.setText("注册");
                break;
            case RegistPhoneActivity.TYPE_QUICK_LOGIN_PHONE:
                tvTitle.setText("手机号登录");
                btRegist.setText("快捷登录");
                break;
            case RegistPhoneActivity.TYPE_FIND_PWD:
                tvTitle.setText("忘记密码");
                btRegist.setText("完成");
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ivBack, R.id.btRegist, R.id.tvAgreement})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressedSupport();
                break;
            case R.id.btRegist:
                switch (type) {
                    case RegistPhoneActivity.TYPE_REGIST_PHONE:
                        String pwd=etPwd.getText().toString();
                        if(verified(phone,pwd)){
                            regist(phone,pwd);
                        }
                        break;
                    case RegistPhoneActivity.TYPE_QUICK_LOGIN_PHONE:

                        break;
                    case RegistPhoneActivity.TYPE_FIND_PWD:

                        break;
                }
                break;
            case R.id.tvAgreement:
                toActivity(AgreementActivity.class);

                break;
        }
    }
    private boolean verified(String uname, String pwd) {
        if(!RegexUtils.isMobileSimple(uname)){
            ToastUtil.shortShow("请输入正确的手机号");
            return false;
        }
        if(StringUtils.isEmpty(pwd)){
            ToastUtil.shortShow("密码不能为空");
            return false;
        }
        if(pwd.length()<6){
            ToastUtil.shortShow("账号或密码有误，请重新输入");
            return false;
        }
        return true;
    }
    private void regist(String uname, String pwd) {
        RegistRequestVo loginRequestVo=new RegistRequestVo();
        loginRequestVo.setPhone(uname);
        loginRequestVo.setPassword(pwd);
        loginRequestVo.setCodeNum(veriCode);
        String sysCode="111";
        String timeStamp=String.valueOf(System.currentTimeMillis()/1000);
        String param=new Gson().toJson(loginRequestVo);
        String sign= SystemUtil.getSign(sysCode,timeStamp,param);


        Subscription rxSubscription = RetrofitHelper.getCpMgrApiService()
                .regist(sysCode,timeStamp,param,sign)
                .compose(new DefaultTransformer<LoginVo>())
                .subscribe(new RxSubscriber<LoginVo>(mContext) {
                    // 必须重写
                    @Override
                    public void onNext(LoginVo contentBeen) {
//                        SharedPreferenceUtil.setLoginState(true);
                        if(contentBeen!=null){
                            SPUtils spUtils= SharedPreferenceUtil.getAppSp();
                            spUtils .putInt(Constants.USER_ID,contentBeen.getCustomerId());
                            spUtils.putString(Constants.USER_NAME,contentBeen.getUsername());
                        }
                        //todo save img,name etc user's information

                        finish();
                    }


                    // 无需设置可以不用重写
                    // !!!!注意参数为ApiException 类型，要不要写在Throwable那个了
                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        SPUtils spUtils= SharedPreferenceUtil.getAppSp();
                        spUtils .putInt(Constants.USER_ID,0);
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

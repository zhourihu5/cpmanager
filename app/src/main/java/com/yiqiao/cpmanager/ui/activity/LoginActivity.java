package com.yiqiao.cpmanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.EncryptUtils;
import com.blankj.utilcode.utils.RegexUtils;
import com.blankj.utilcode.utils.SPUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.google.gson.Gson;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.app.Constants;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.entity.LoginRequestVo;
import com.yiqiao.cpmanager.entity.LoginVo;
import com.yiqiao.cpmanager.http.RetrofitHelper;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.transformer.DefaultTransformer;
import com.yiqiao.cpmanager.util.LogUtils;
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

public class LoginActivity extends BaseActivity {

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
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.tvFindPwd)
    TextView tvFindPwd;
    @BindView(R.id.btLogin)
    Button btLogin;
    @BindView(R.id.tvRegist)
    TextView tvRegist;
    @BindView(R.id.tvQuickLogin)
    TextView tvQuickLogin;
    @BindView(R.id.cbPwdEncode)
    CheckBox cbPwdEncode;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {
        cbPwdEncode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    //文本正常显示

                    etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                    Editable etable = etPwd.getText();

                    Selection.setSelection(etable, etable.length());

                }else{

                    //文本以密码形式显示

                    etPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                    //下面两行代码实现: 输入框光标一直在输入文本后面

                    Editable etable = etPwd.getText();

                    Selection.setSelection(etable, etable.length());
                }

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ivBack, R.id.tvFindPwd, R.id.btLogin, R.id.tvRegist, R.id.tvQuickLogin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressedSupport();
                break;
            case R.id.tvFindPwd:
                toActivity(FindPwdActivity.class);
                break;
            case R.id.btLogin:
                String uname = etPhone.getText().toString();
                String pwd = etPwd.getText().toString();
                if (verified(uname,pwd)) {
                    login(uname, pwd);
                }
                break;
            case R.id.tvRegist:
                toActivity(RegistPhoneActivity.class);
                break;
            case R.id.tvQuickLogin:
                Intent intent = new Intent(this, RegistPhoneActivity.class);
                intent.putExtra(RegistPhoneActivity.TYPE_STR, RegistPhoneActivity.TYPE_QUICK_LOGIN_PHONE);
                startActivity(intent);
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

    private void login(String uname, String pwd) {
        LoginRequestVo loginRequestVo=new LoginRequestVo(uname,pwd);
        String sysCode="111";
        String timeStamp=String.valueOf(System.currentTimeMillis()/1000);
        String param=new Gson().toJson(loginRequestVo);
        String sign= SystemUtil.getSign(sysCode,timeStamp,param);


        Subscription rxSubscription = RetrofitHelper.getCpMgrApiService()
                .login(sysCode,timeStamp,param,sign)
                .compose(new DefaultTransformer<LoginVo>())
                .subscribe(new RxSubscriber<LoginVo>(mContext) {
                    // 必须重写
                    @Override
                    public void onNext(LoginVo contentBeen) {
                        LogUtils.e(contentBeen.toString());
                        ToastUtil.shortShow(contentBeen.toString());
                        SharedPreferenceUtil.setLoginState(true);
                        SPUtils spUtils= SharedPreferenceUtil.getAppSp();
                        spUtils .putInt(Constants.USER_ID,contentBeen.getCustomerId());
                        spUtils.putString(Constants.USER_NAME,contentBeen.getUsername());
                        //todo save img,name etc user's information

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
}

package com.yiqiao.cpmanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.entity.LoginVo;
import com.yiqiao.cpmanager.http.RetrofitHelper;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.transformer.DefaultTransformer;
import com.yiqiao.cpmanager.util.LogUtils;
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
                    etPwd.setInputType(InputType.TYPE_CLASS_TEXT);

                }else {
                    etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
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
                if (verified()) {
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

    private boolean verified() {
        return true;
    }

    private void login(String uname, String pwd) {
        Subscription rxSubscription = RetrofitHelper.getCpMgrApiService()
                .login(uname, pwd)
                .compose(new DefaultTransformer<LoginVo>())
                .subscribe(new RxSubscriber<LoginVo>(mContext) {
                    // 必须重写
                    @Override
                    public void onNext(LoginVo contentBeen) {
                        LogUtils.e(contentBeen.toString());
                        ToastUtil.shortShow(contentBeen.toString());
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

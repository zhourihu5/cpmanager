package com.yiqiao.cpmanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {

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

                break;
            case R.id.tvRegist:
                toActivity(RegistPhoneActivity.class);
                break;
            case R.id.tvQuickLogin:
                Intent intent=new Intent(this,RegistPhoneActivity.class);
                intent.putExtra(RegistPhoneActivity.TYPE_STR,RegistPhoneActivity.TYPE_QUICK_LOGIN_PHONE);
                startActivity(intent);
                break;
        }
    }
}

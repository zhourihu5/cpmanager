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

import static android.R.attr.type;

/**
 * Created by Xu on 2016/11/23.
 * 关于我们
 */

public class RegistCodeActivity extends BaseActivity {

    String phone;
    public static final String PHONE = "phone";
    public static final String VERI_CODE_KEY="veriCode";
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ivBack, R.id.btLogin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressedSupport();
                break;
            case R.id.btLogin:
                switch (type) {
                    case RegistPhoneActivity.TYPE_REGIST_PHONE:
                        //todo 验证码

                        //TODO 下一步 设置密码

                        Intent intent=getIntent();
                        intent.setClass(mContext,RegistPwdActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case RegistPhoneActivity.TYPE_QUICK_LOGIN_PHONE:
                        //TODO 快速登录
                        break;

                }
                break;
        }
    }
}

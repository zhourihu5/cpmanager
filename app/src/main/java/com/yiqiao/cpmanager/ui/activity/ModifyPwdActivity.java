package com.yiqiao.cpmanager.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
 */

public class ModifyPwdActivity extends BaseActivity {

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.etPwd1)
    EditText etPwd1;
    @BindView(R.id.etPwd2)
    EditText etPwd2;
    @BindView(R.id.cbPwdEncode)
    CheckBox cbPwdEncode;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    @Override
    protected int getLayout() {
//        return R.layout.activity_modify_pwd1;
        return R.layout.activity_modify_pwd;
    }

    @Override
    protected void initEventAndData() {
        tvTitle.setText("修改密码");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ivBack, R.id.btnSubmit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressedSupport();
                break;
            case R.id.btnSubmit:

                break;
        }
    }
}

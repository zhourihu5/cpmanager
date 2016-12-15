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
import com.blankj.utilcode.utils.StringUtils;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Xu on 2016/11/23.
 * 注册，快捷登录，忘记密码，修改密码等界面
 */

public class RegistPhoneActivity extends BaseActivity {

    int type;
    public static final int TYPE_QUICK_LOGIN_PHONE = 1;
    public static final int TYPE_REGIST_PHONE = 0;
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
                if(RegexUtils.isMobileExact(phone)){
                    ToastUtil.shortShow("请输入正确的手机号");
                    return;
                }

                Intent intent=new Intent(this,RegistCodeActivity.class);
                intent.putExtra(RegistPhoneActivity.TYPE_STR,RegistPhoneActivity.TYPE_QUICK_LOGIN_PHONE);
                intent.putExtra(RegistCodeActivity.PHONE,phone);
                switch (type) {
                    case TYPE_REGIST_PHONE:
                        //todo 服务器校验是否已注册了


                        break;
                    case TYPE_QUICK_LOGIN_PHONE:
                        //todo

                        break;

                }
                startActivity(intent);
                break;
        }
    }
}

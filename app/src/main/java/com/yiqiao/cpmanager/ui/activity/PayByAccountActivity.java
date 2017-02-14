package com.yiqiao.cpmanager.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.entity.OrderVo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Xu on 2016/11/23.
 * 对公转账
 */

public class PayByAccountActivity extends BaseActivity {


    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvBankName)
    TextView tvBankName;
    @BindView(R.id.tvBankAccont)
    TextView tvBankAccont;
    @BindView(R.id.tvOrderNum)
    TextView tvOrderNum;
    @BindView(R.id.etCpName)
    EditText etCpName;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    OrderVo orderVo;
    @Override
    protected int getLayout() {
        return R.layout.activity_pay_by_account;
    }

    @Override
    protected void initEventAndData() {
        tvTitle.setText("对公转账");
        orderVo= (OrderVo) getIntent().getSerializableExtra(OrderVo.class.getCanonicalName());
        tvOrderNum.setText(orderVo.getOrdersNo());
    }


    @OnClick({R.id.ivBack, R.id.btnSubmit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressedSupport();
                break;
            case R.id.btnSubmit:
                //todo handle data
                break;
        }
    }
}

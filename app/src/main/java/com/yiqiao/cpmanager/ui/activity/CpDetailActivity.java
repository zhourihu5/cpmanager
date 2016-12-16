package com.yiqiao.cpmanager.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xu on 2016/11/23.
 * 企业明细页
 */

public class CpDetailActivity extends BaseActivity {

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.textView5)
    TextView textView5;

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
    }

    @Override
    protected int getLayout() {
//        return R.layout.activity_cp_detail1;
        return R.layout.activity_cp_detail;
    }

    @Override
    protected void initEventAndData() {
        ivBack.setBackgroundResource(R.drawable.back_white);
        tvTitle.setTextColor(Color.WHITE);
        tvTitle.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

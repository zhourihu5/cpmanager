package com.yiqiao.cpmanager.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.BarUtils;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.ui.adapter.TaskCenterAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Xu on 2016/11/23.
 */

public class CpNameCheckActivity extends BaseActivity {

    TaskCenterAdapter taskCenterAdapter;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.tvCheck)
    TextView tvCheck;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tvJump)
    TextView tvJump;

    @Override
    protected void setStatusBar() {
        BarUtils.setTranslucentForCoordinatorLayout(this, 0);
        ivBack.setImageResource(R.drawable.back_white);
        tvTitle.setText("公司核名");
        tvTitle.setTextColor(Color.WHITE);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_cp_name_check;
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

    @OnClick({R.id.ivBack, R.id.tvCheck, R.id.tvJump})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressedSupport();
                break;
            case R.id.tvCheck:
                break;
            case R.id.tvJump:
                break;
        }
    }
}

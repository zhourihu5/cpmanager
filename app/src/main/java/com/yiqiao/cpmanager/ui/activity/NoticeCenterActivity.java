package com.yiqiao.cpmanager.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.ui.adapter.ServiceNoticeAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Xu on 2016/11/23.
 */

public class NoticeCenterActivity extends BaseActivity {

    ServiceNoticeAdapter serviceNoticeAdapter;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.llOnline)
    LinearLayout llOnline;
    @BindView(R.id.llServiceNotice)
    LinearLayout llServiceNotice;
    @BindView(R.id.llActivityNotice)
    LinearLayout llActivityNotice;
    @BindView(R.id.llSystemNotice)
    LinearLayout llSystemNotice;

    @Override
    protected int getLayout() {
        return R.layout.activity_notice_center;
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

    @OnClick({R.id.ivBack, R.id.llOnline, R.id.llServiceNotice, R.id.llActivityNotice, R.id.llSystemNotice})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressedSupport();
                break;
            case R.id.llOnline://在线咨询  小能客服
//                toActivity();
                break;
            case R.id.llServiceNotice:
                toActivity(ServiceNoticeActivity.class);
                break;
            case R.id.llActivityNotice:
                toActivity(ActivityNoticeActivity.class);
                break;
            case R.id.llSystemNotice:
                toActivity(SystemNoticeActivity.class);
                break;
        }
    }
}

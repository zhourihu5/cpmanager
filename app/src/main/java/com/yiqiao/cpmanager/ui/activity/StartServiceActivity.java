package com.yiqiao.cpmanager.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.ui.adapter.SearchCpAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Xu on 2016/11/23.
 */

public class StartServiceActivity extends BaseActivity {

    SearchCpAdapter searchCpAdapter;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.tvSubmit)
    TextView tvSubmit;

    @Override
    protected int getLayout() {
        return R.layout.activity_start_service;
    }

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
        ivBack.setImageResource(R.drawable.back_white);
        tvTitle.setText("启动服务");
//        tvTitle.setTextColor(Color.WHITE);
        tvRight.setCompoundDrawablesWithIntrinsicBounds(R.drawable.customer_service, 0, 0, 0);
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

    @OnClick({R.id.ivBack, R.id.tvRight, R.id.tvSubmit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressedSupport();
                break;
            case R.id.tvRight:
//                toActivity();
                break;
            case R.id.tvSubmit:
//                RetrofitHelper.getCpMgrApiService().
                break;
        }
    }
}

package com.yiqiao.cpmanager.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.ui.adapter.ContentPagerAdapter;
import com.yiqiao.cpmanager.ui.fragment.CpServiceFragment;
import com.yiqiao.cpmanager.widget.UnScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Xu on 2016/11/23.
 * 公司服务
 */

public class CpServiceActivity extends BaseActivity {

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;

    @BindView(R.id.tvBusinessService)
    TextView tvBusinessService;
    @BindView(R.id.llBusinessService)
    LinearLayout llBusinessService;
    @BindView(R.id.tvTaxService)
    TextView tvTaxService;
    @BindView(R.id.llTaxService)
    LinearLayout llTaxService;
    @BindView(R.id.tvIpService)
    TextView tvIpService;
    @BindView(R.id.llIpService)
    LinearLayout llIpService;
    @BindView(R.id.tvGovermentService)
    TextView tvGovermentService;
    @BindView(R.id.llGovermentService)
    LinearLayout llGovermentService;
    @BindView(R.id.tvSocialInsurance)
    TextView tvSocialInsurance;
    @BindView(R.id.llSocialInsurance)
    LinearLayout llSocialInsurance;


    ContentPagerAdapter contentPagerAdapter;
    List<Fragment> fragmentList = new ArrayList<>();
    @BindView(R.id.viewPager)
    UnScrollViewPager viewPager;

    @Override
    protected int getLayout() {
        return R.layout.activity_cp_service;
    }

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
        tvTitle.setText("公司服务");

    }

    @Override
    protected void initEventAndData() {
        for (int i = 0; i < 5; i++) {
            Fragment fragment = new CpServiceFragment();
            fragmentList.add(fragment);
        }
        contentPagerAdapter=new ContentPagerAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(contentPagerAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ivBack, R.id.llBusinessService, R.id.llTaxService, R.id.llIpService, R.id.llGovermentService, R.id.llSocialInsurance})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressedSupport();
                break;
            case R.id.llBusinessService:
                unSelectAllTab();
                llBusinessService.setBackgroundColor(Color.WHITE);
                tvBusinessService.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.business_service_pressed, 0, 0);
                break;
            case R.id.llTaxService:
                unSelectAllTab();
                llTaxService.setBackgroundColor(Color.WHITE);
                tvTaxService.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tax_service_pressed, 0, 0);
                break;
            case R.id.llIpService:
                unSelectAllTab();
                llIpService.setBackgroundColor(Color.WHITE);
                tvIpService.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.intellectual_property_pressed, 0, 0);
                break;
            case R.id.llGovermentService:
                unSelectAllTab();
                llGovermentService.setBackgroundColor(Color.WHITE);
                tvGovermentService.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.goverment_service_pressed, 0, 0);
                break;
            case R.id.llSocialInsurance:
                unSelectAllTab();
                llSocialInsurance.setBackgroundColor(Color.WHITE);
                tvSocialInsurance.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.social_insurance_pressed, 0, 0);
                break;
        }
    }

    void unSelectAllTab() {
        int color = ContextCompat.getColor(mContext, R.color.gray_bg);
        llBusinessService.setBackgroundColor(color);
        llGovermentService.setBackgroundColor(color);
        llIpService.setBackgroundColor(color);
        llSocialInsurance.setBackgroundColor(color);
        llTaxService.setBackgroundColor(color);

        tvBusinessService.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.business_service, 0, 0);
        tvGovermentService.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.goverment_service, 0, 0);
        tvIpService.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.intellectual_property, 0, 0);
        tvSocialInsurance.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.social_insurance, 0, 0);
        tvTaxService.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tax_service, 0, 0);
    }
}

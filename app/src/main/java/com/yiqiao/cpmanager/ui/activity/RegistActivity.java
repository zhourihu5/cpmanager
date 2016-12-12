package com.yiqiao.cpmanager.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.ui.adapter.ContentPagerAdapter;
import com.yiqiao.cpmanager.ui.fragment.RegistCodeFragment;
import com.yiqiao.cpmanager.ui.fragment.RegistPhoneFragment;
import com.yiqiao.cpmanager.ui.fragment.RegistPwdFragment;
import com.yiqiao.cpmanager.widget.UnScrollViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xu on 2016/11/23.
 * 注册
 */

public class RegistActivity extends BaseActivity {

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.viewPager)
    UnScrollViewPager viewPager;

    RegistPhoneFragment registPhoneFragment;
    RegistCodeFragment registCodeFragment;
    RegistPwdFragment registPwdFragment;

    ContentPagerAdapter contentPagerAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_regist;
    }

    @Override
    protected void initEventAndData() {
        registPhoneFragment=new RegistPhoneFragment();
        registCodeFragment=new RegistCodeFragment();
        registPwdFragment=new RegistPwdFragment();
        ArrayList<Fragment>arrayList=new ArrayList<>();
        arrayList.add(registPhoneFragment);
        arrayList.add(registCodeFragment);
        arrayList.add(registPwdFragment);

        contentPagerAdapter=new ContentPagerAdapter(getSupportFragmentManager(),arrayList);
        viewPager.setAdapter(contentPagerAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

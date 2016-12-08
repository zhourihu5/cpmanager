package com.yiqiao.cpmanager.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.BarUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.ui.adapter.ContentPagerAdapter;
import com.yiqiao.cpmanager.ui.fragment.InvoicePreset1Fragment;
import com.yiqiao.cpmanager.ui.fragment.InvoicePreset2Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Xu on 2016/11/23.
 */

public class InvoicePresetActivity extends BaseActivity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tablayout)
    CommonTabLayout tablayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    ContentPagerAdapter contentPagerAdapter;
    List<Fragment> fragmentList = new ArrayList<>();
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.llBottom)
    LinearLayout llBottom;
    @BindView(R.id.tvBottom)
    TextView tvBottom;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private String[] mTitles = {"发票抬头", "收件人信息"};

    @Override
    protected void setStatusBar() {
//        super.setStatusBar();
        BarUtils.setTranslucentForCoordinatorLayout(this, 0);
        BarUtils.setTransparentForImageView(this, toolbar);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_invoice_preset;
    }

    @Override
    protected void initEventAndData() {


        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], 0, 0));
        }
        Fragment fragment = InvoicePreset1Fragment.getInstance();
        fragmentList.add(fragment);
        Fragment fragment2 = InvoicePreset2Fragment.getInstance();
        fragmentList.add(fragment2);
        tablayout.setTabData(mTabEntities);

//        tablayout.showMsg(0, 5);
//        tablayout.setMsgMargin(0, -5, 5);
//        tablayout.setMsgMargin(0, 5, 5);
        tablayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        contentPagerAdapter = new ContentPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(contentPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tablayout.setCurrentTab(position);
                switch (position) {
                    case 0:
                        tvBottom.setText("新增发票抬头");
                        break;
                    case 1:
                        tvBottom.setText("新增收件人");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ivBack, R.id.llBottom})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressedSupport();
                break;
            case R.id.llBottom:
                switch (viewPager.getCurrentItem()) {
                    case 0:
//                        tvBottom.setText("新增发票抬头");
                        toActivity(AddInvoiceHeadActivity.class);
                        break;
                    case 1:
//                        tvBottom.setText("新增收件人");
                        toActivity(AddReceiverActivity.class);
                        break;
                }
                break;
        }
    }

    class TabEntity implements CustomTabEntity {
        public String title;
        public int selectedIcon;
        public int unSelectedIcon;

        public TabEntity(String title, int selectedIcon, int unSelectedIcon) {
            this.title = title;
            this.selectedIcon = selectedIcon;
            this.unSelectedIcon = unSelectedIcon;
        }

        @Override
        public String getTabTitle() {
            return title;
        }

        @Override
        public int getTabSelectedIcon() {
            return selectedIcon;
        }

        @Override
        public int getTabUnselectedIcon() {
            return unSelectedIcon;
        }
    }

}

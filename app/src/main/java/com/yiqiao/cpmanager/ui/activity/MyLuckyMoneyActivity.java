package com.yiqiao.cpmanager.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
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
import com.yiqiao.cpmanager.ui.fragment.MyLuckyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xu on 2016/11/23.
 */

public class MyLuckyMoneyActivity extends BaseActivity {

    MyLuckyFragment myLuckyFragment;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.tablayout)
    CommonTabLayout tablayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    ContentPagerAdapter contentPagerAdapter;
    List<Fragment> fragmentList = new ArrayList<>();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.sTabLayout)
    TabLayout sTabLayout;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private String[] mTitles = {"全部", "获取", "支出"};

    @Override
    protected int getLayout() {
        return R.layout.activity_my_lucky_money;
    }

    @Override
    protected void setStatusBar() {
//        super.setStatusBar();
        BarUtils.setTranslucentForCoordinatorLayout(this, 0);
        BarUtils.setTransparentForImageView(this, toolbar);
        ivBack.setImageResource(R.drawable.back_white);
        tvTitle.setText("我的红包");
        tvTitle.setTextColor(Color.WHITE);
        tvRight.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initEventAndData() {

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], 0, 0));
            fragmentList.add(new MyLuckyFragment());
        }
        tablayout.setTabData(mTabEntities);

//        tablayout.showMsg(0, 5);
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

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        sTabLayout.addTab(sTabLayout.newTab().setText(mTitles[0]));
        sTabLayout.addTab(sTabLayout.newTab().setText(mTitles[1]));
        sTabLayout.addTab(sTabLayout.newTab().setText(mTitles[2]));
        sTabLayout.setupWithViewPager(viewPager);
//        sTabLayout.setTabsFromPagerAdapter(contentPagerAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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

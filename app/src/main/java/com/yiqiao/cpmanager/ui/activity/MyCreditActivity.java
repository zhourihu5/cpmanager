package com.yiqiao.cpmanager.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.BarUtils;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.ui.adapter.ContentPagerAdapter;
import com.yiqiao.cpmanager.ui.fragment.MyCreditFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xu on 2016/11/23.
 */

public class MyCreditActivity extends BaseActivity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    //    @BindView(R.id.tablayout)
//    CommonTabLayout tablayout;

    ContentPagerAdapter contentPagerAdapter;
    List<Fragment> fragmentList = new ArrayList<>();
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.llToolbar)
    RelativeLayout llToolbar;
    @BindView(R.id.tvAvailableNum)
    TextView tvAvailableNum;
    @BindView(R.id.tvTotalNum)
    TextView tvTotalNum;
    @BindView(R.id.tvFrosenNum)
    TextView tvFrosenNum;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private String[] mTitles = {"待支付", "已付款", "已取消"};

    @Override
    protected void setStatusBar() {
//        super.setStatusBar();
        BarUtils.setTranslucentForCoordinatorLayout(this, 0);
        BarUtils.setTransparentForImageView(this, toolbar);
        tvTitle.setText("我的积分");
        tvTitle.setTextColor(Color.WHITE);
        tvRight.setText("积分规则");
        tvRight.setTextColor(Color.WHITE);
        ivBack.setImageResource(R.drawable.back_white);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_my_credit;
    }

    @Override
    protected void initEventAndData() {


        Fragment fragment = MyCreditFragment.getInstance();
        fragmentList.add(fragment);
        contentPagerAdapter = new ContentPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(contentPagerAdapter);

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

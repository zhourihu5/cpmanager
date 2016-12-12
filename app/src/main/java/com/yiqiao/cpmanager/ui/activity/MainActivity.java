package com.yiqiao.cpmanager.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.utils.BarUtils;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.app.App;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.ui.adapter.ContentPagerAdapter;
import com.yiqiao.cpmanager.ui.fragment.HomeFragment;
import com.yiqiao.cpmanager.ui.fragment.ManagerFragment;
import com.yiqiao.cpmanager.ui.fragment.MineFragment;
import com.yiqiao.cpmanager.widget.UnScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by codeest on 16/8/9.
 */

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.vpContent)
    UnScrollViewPager vpContent;
    @BindView(R.id.navigationBottom)
    BottomNavigationView navigationBottom;

    ContentPagerAdapter contentPagerAdapter;
    List<Fragment>fragmentList=new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void setStatusBar() {
//        StatusBarUtil.setTransparent(this);
//        initStatusBar();
        BarUtils.setTranslucentForCoordinatorLayout(this, 0);
    }

    @Override
    protected void initEventAndData() {
        navigationBottom.setOnNavigationItemSelectedListener(this);//3-5个tab
        fragmentList.clear();
        HomeFragment homeFragment=new HomeFragment();
        ManagerFragment managerFragment=new ManagerFragment();
        MineFragment mineFragment=new MineFragment();

        fragmentList.add(homeFragment);
        fragmentList.add(managerFragment);
        fragmentList.add(mineFragment);

        contentPagerAdapter=new ContentPagerAdapter(getSupportFragmentManager(),fragmentList);
        vpContent.setAdapter(contentPagerAdapter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private void showExitDialog() {

        new MaterialDialog.Builder(this)
                .title("提示")
                .content("确定退出应用吗")
                .positiveText("确定")
                .negativeText("取消")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        App.getInstance().exitApp();
                    }
                })
//                .onNegative(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                    }
//                })
                .show();

    }

    @Override
    public void onBackPressedSupport() {
        showExitDialog();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                vpContent.setCurrentItem(0);
                BarUtils.StatusBarDarkMode(mContext);
                break;
            case R.id.menu_manager:
                vpContent.setCurrentItem(1);
                BarUtils.StatusBarLightMode(mContext);
                break;
            case R.id.menu_mine:
                if(isLogin()){
                    vpContent.setCurrentItem(2);
                    BarUtils.StatusBarDarkMode(mContext);
                    return true;
                }else {
                    return false;
                }
            default:
                return false;
        }
        return true;
    }


}

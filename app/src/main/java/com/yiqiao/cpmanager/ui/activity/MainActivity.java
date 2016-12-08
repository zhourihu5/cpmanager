package com.yiqiao.cpmanager.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;

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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定退出GeekNews吗");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                App.getInstance().exitApp();
            }
        });
        builder.show();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                vpContent.setCurrentItem(0);
                break;
            case R.id.menu_manager:
                vpContent.setCurrentItem(1);
                break;
            case R.id.menu_mine:
                vpContent.setCurrentItem(2);

                break;
            default:
                return false;
        }
        return true;
    }


}

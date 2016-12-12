package com.yiqiao.cpmanager.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.entity.OrderVo;
import com.yiqiao.cpmanager.ui.adapter.TaskCenterAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Xu on 2016/11/23.
 */

public class TaskCenterActivity extends BaseActivity {

    TaskCenterAdapter adapter;
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
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    @Override
    protected int getLayout() {
        return R.layout.activity_task_center;
    }

    @Override
    protected void initEventAndData() {

        tvRight.setCompoundDrawablesWithIntrinsicBounds(R.drawable.back,0,0,0);

        recycleView.setLayoutManager(new LinearLayoutManager(mContext));
        ArrayList<OrderVo>arrayList=new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            arrayList.add(new OrderVo());
        }
        adapter=new TaskCenterAdapter(mContext,arrayList);
        recycleView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ivBack, R.id.tvRight})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressedSupport();
                break;
            case R.id.tvRight:

                break;
        }
    }
}

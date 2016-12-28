package com.yiqiao.cpmanager.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.entity.OrderVo;
import com.yiqiao.cpmanager.ui.adapter.ServiceDetailAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xu on 2016/11/23.
 * 服务明细
 */

public class ServiceDetailActivity extends BaseActivity {

    ServiceDetailAdapter serviceDetailAdapter;
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
        return R.layout.activity_service_detail;
    }

    @Override
    protected void initEventAndData() {

        recycleView.setLayoutManager(new LinearLayoutManager(mContext));
        ArrayList<OrderVo>arrayList=new ArrayList<>();
        for(int i=0;i<10;i++){
            arrayList.add(new OrderVo());
        }
        serviceDetailAdapter=new ServiceDetailAdapter(mContext,arrayList);
        recycleView.setAdapter(serviceDetailAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

package com.yiqiao.cpmanager.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.oushangfeng.pinnedsectionitemdecoration.PinnedHeaderItemDecoration;
import com.oushangfeng.pinnedsectionitemdecoration.callback.OnHeaderClickListener;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.entity.CpServiceVo;
import com.yiqiao.cpmanager.ui.adapter.CpServiceAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    private CpServiceAdapter mAdapter;

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
        ArrayList<CpServiceVo>arrayList= new ArrayList<>();
        for(int i=0;i<20;i++){
            CpServiceVo cpServiceVo=new CpServiceVo();
            cpServiceVo.setItemType(i%5==0?CpServiceAdapter.TYPE_HEADER:CpServiceAdapter.TYPE_DATA);
            arrayList.add(cpServiceVo);
        }
        mAdapter=new CpServiceAdapter(arrayList);
        recycleView.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        recycleView.setAdapter(mAdapter);
        recycleView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                switch (mAdapter.getItemViewType(i)) {
                    case CpServiceAdapter.TYPE_DATA:
                        toActivity(SearchSpuActivity.class);
                        break;
                    case CpServiceAdapter.TYPE_HEADER:

                        break;
                }
            }
        });

//        OnHeaderClickListener headerClickListener = new OnHeaderClickListener() {
//            @Override
//            public void onHeaderClick(View view, int id, int position) {
//            }
//
//            @Override
//            public void onHeaderLongClick(View view, int id, int position) {
//            }
//
//            @Override
//            public void onHeaderDoubleClick(View view, int id, int position) {
//            }
//        };
//        recycleView.addItemDecoration(new PinnedHeaderItemDecoration.Builder(CpServiceAdapter.TYPE_HEADER).setDividerId(R.drawable.divider).enableDivider(true)
//                .setHeaderClickListener(headerClickListener).create());
        mAdapter.onAttachedToRecyclerView(recycleView);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

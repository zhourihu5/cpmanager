package com.yiqiao.cpmanager.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseFragment;
import com.yiqiao.cpmanager.entity.CpServiceVo;
import com.yiqiao.cpmanager.ui.activity.SearchSpuActivity;
import com.yiqiao.cpmanager.ui.adapter.CpServiceAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by codeest on 2016/8/11.
 * 公司服务
 */
public class CpServiceFragment extends BaseFragment {

    private CpServiceAdapter mAdapter;

    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @Override
    protected int getLayoutId() {
        return R.layout.frag_cp_service;
    }

    @Override
    protected void initEventAndData() {
        ArrayList<CpServiceVo> arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CpServiceVo cpServiceVo = new CpServiceVo();
            cpServiceVo.setItemType(i % 5 == 0 ? CpServiceAdapter.TYPE_HEADER : CpServiceAdapter.TYPE_DATA);
            arrayList.add(cpServiceVo);
        }
        mAdapter = new CpServiceAdapter(arrayList);
        recycleView.setLayoutManager(new GridLayoutManager(mActivity, 3, GridLayoutManager.VERTICAL, false));
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


}

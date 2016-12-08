package com.yiqiao.cpmanager.ui.adapter;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oushangfeng.pinnedsectionitemdecoration.utils.FullSpanUtil;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.CpServiceVo;

import java.util.List;

/**
 * Created by Oubowu on 2016/8/3 20:40.
 * 继承BaseMultiItemQuickAdapter的一个适配器基类
 */
public class CpServiceAdapter extends BaseMultiItemQuickAdapter<CpServiceVo, BaseViewHolder> {

    public static final int TYPE_HEADER = 1;
    public static final int TYPE_DATA = 2;

    public CpServiceAdapter(List<CpServiceVo> data) {
        super(data);
        addItemType(CpServiceAdapter.TYPE_HEADER, R.layout.item_cpservice_header);
        addItemType(CpServiceAdapter.TYPE_DATA, R.layout.item_cpservice_data);
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        FullSpanUtil.onAttachedToRecyclerView(recyclerView, this, TYPE_HEADER);
    }

    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        FullSpanUtil.onViewAttachedToWindow(holder, this, TYPE_HEADER);
    }

    @Override
    protected void convert(BaseViewHolder holder, CpServiceVo t) {
        switch (holder.getItemViewType()) {
            case CpServiceAdapter.TYPE_HEADER:
                holder.setText(R.id.tvTitle, "2级分类");
                break;
            case CpServiceAdapter.TYPE_DATA:
                holder.setText(R.id.tvTitle, "3级分类");
                break;
            default:
                holder.setText(R.id.tvTitle, "3级分类");
        }
    }

}

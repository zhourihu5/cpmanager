package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.OrderVo;

import butterknife.ButterKnife;


/**
 * Description: 推荐
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class DoInvoice2HeadAdapter extends RecyclerArrayAdapter<OrderVo> {

    public DoInvoice2HeadAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }
    class RecommendViewHolder extends BaseViewHolder<OrderVo> {

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_do_invoice_head);
            ButterKnife.bind(this, parent);
        }

        @Override
        public void setData(OrderVo data) {

        }

    }
}

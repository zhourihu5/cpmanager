package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.OrderVo;

import java.util.ArrayList;

import butterknife.ButterKnife;


/**
 * Description: 推荐
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class InvoicePreset2Adapter extends RecyclerArrayAdapter<OrderVo> {

    private int type;

    public InvoicePreset2Adapter(Context context, ArrayList<OrderVo> orderVos) {
        super(context,orderVos);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    public void setType(int type) {
        this.type = type;
    }

    class RecommendViewHolder extends BaseViewHolder<OrderVo> {

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_invoice_preset);
            ButterKnife.bind(this, parent);
        }

        @Override
        public void setData(OrderVo data) {

        }

    }
}

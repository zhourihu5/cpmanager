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
public class ServiceDetailAdapter extends RecyclerArrayAdapter<OrderVo> {

    public ServiceDetailAdapter(Context context, ArrayList<OrderVo> arrayList) {
        super(context,arrayList);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }
    class RecommendViewHolder extends BaseViewHolder<OrderVo> {

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_service_detail);
            ButterKnife.bind(this, parent);
        }

        @Override
        public void setData(OrderVo data) {

        }

    }
}

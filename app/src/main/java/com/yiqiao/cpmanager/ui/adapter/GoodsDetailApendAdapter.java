package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.OrderVo;
import com.yiqiao.cpmanager.ui.activity.OrderDetailActivity;

import java.util.List;

import butterknife.ButterKnife;


/**
 * Description: 附加产品
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class GoodsDetailApendAdapter extends RecyclerArrayAdapter<OrderVo> {


    public GoodsDetailApendAdapter(Context context, List<OrderVo> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<OrderVo> {


        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_goods_detail_append);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(OrderVo data) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), OrderDetailActivity.class);
                    getContext().startActivity(intent);
                }
            });
        }

    }
}

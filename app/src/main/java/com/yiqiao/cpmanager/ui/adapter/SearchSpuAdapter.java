package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.OrderVo;
import com.yiqiao.cpmanager.ui.activity.GoodsDetailActivity;

import java.util.ArrayList;

import butterknife.ButterKnife;


/**
 * Description: 推荐
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class SearchSpuAdapter extends RecyclerArrayAdapter<OrderVo> {

    public SearchSpuAdapter(Context context, ArrayList<OrderVo> orderVos) {
        super(context,orderVos);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }
    class RecommendViewHolder extends BaseViewHolder<OrderVo> {

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_search_spu);
            ButterKnife.bind(this, parent);
        }

        @Override
        public void setData(OrderVo data) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
                    getContext().startActivity(intent);
                }
            });
        }

    }
}

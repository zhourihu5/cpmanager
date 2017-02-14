package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.SkuDetailVo;
import com.yiqiao.cpmanager.ui.activity.OrderDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description: 推荐
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class GoodsDetailDonateAdapter extends RecyclerArrayAdapter<SkuDetailVo.GiveawayBean> {


    public GoodsDetailDonateAdapter(Context context, List<SkuDetailVo.GiveawayBean> list) {
        super(context, list==null?new ArrayList<SkuDetailVo.GiveawayBean>():list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<SkuDetailVo.GiveawayBean> {


        @BindView(R.id.tvName)
        TextView tvName;

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_goods_detail_donate);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(SkuDetailVo.GiveawayBean data) {
            tvName.setText(data.getName());
        }

    }
}

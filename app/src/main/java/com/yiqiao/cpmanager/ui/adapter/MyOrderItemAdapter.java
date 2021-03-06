package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.OrderVo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description: 推荐
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class MyOrderItemAdapter extends RecyclerArrayAdapter<OrderVo.OrderListBean.ServiceListBean> {

    public MyOrderItemAdapter(Context context, List<OrderVo.OrderListBean.ServiceListBean> arrayList) {
        super(context, arrayList==null?new ArrayList<OrderVo.OrderListBean.ServiceListBean>():arrayList);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<OrderVo.OrderListBean.ServiceListBean> {

        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.tvNum)
        TextView tvNum;

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_order_item);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(OrderVo.OrderListBean.ServiceListBean data) {
            tvName.setText(data.getGoodsName());
            tvPrice.setText(String.format("%s元",data.getGoodsAmount()));
            tvNum.setText(String.format("x%s",data.getGoodsSku()));//todo ?
        }

    }
}

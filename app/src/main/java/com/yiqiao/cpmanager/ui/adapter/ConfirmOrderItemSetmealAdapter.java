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
public class ConfirmOrderItemSetmealAdapter extends RecyclerArrayAdapter<OrderVo.OrderListBean> {

    public ConfirmOrderItemSetmealAdapter(Context context, List<OrderVo.OrderListBean> arrayList) {
        super(context, arrayList==null?new ArrayList<OrderVo.OrderListBean>():arrayList);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<OrderVo.OrderListBean> {

        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.tvNum)
        TextView tvNum;

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_confirm_order_setmeal);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(OrderVo.OrderListBean data) {
            tvName.setText(data.getProductName());
            tvPrice.setText(data.getFinalAmount());
            tvNum.setText(String.format("x%s",data.getNum()));//todo ?
        }

    }
}

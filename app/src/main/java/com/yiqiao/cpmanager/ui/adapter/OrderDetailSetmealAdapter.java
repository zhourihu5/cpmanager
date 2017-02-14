package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.OrderVo.OrderListBean;
import com.yiqiao.cpmanager.entity.OrderVo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description:
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class OrderDetailSetmealAdapter extends RecyclerArrayAdapter<OrderVo.OrderListBean> {

    public OrderDetailSetmealAdapter(Context context, ArrayList<OrderVo.OrderListBean> arrayList) {
        super(context, arrayList == null ? new ArrayList<OrderVo.OrderListBean>() : arrayList);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<OrderVo.OrderListBean> {


        @BindView(R.id.tvItemName)
        TextView tvItemName;
        @BindView(R.id.tvItemPrice)
        TextView tvItemPrice;
        @BindView(R.id.tvItemNum)
        TextView tvItemNum;
        @BindView(R.id.llItemGoodsInfo)
        LinearLayout llItemGoodsInfo;
        @BindView(R.id.tvCpType)
        TextView tvCpType;
        @BindView(R.id.tvAddress)
        TextView tvAddress;
        @BindView(R.id.tvRegistAddr)
        TextView tvRegistAddr;

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_order_detail_setmeal);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(OrderVo.OrderListBean data) {
            llItemGoodsInfo.setVisibility(View.VISIBLE);
            tvItemName.setText(data.getProductName());
            tvItemPrice.setText(String.format("%s元",data.getFinalAmount()));
            tvItemNum.setText(String.format("x%s",data.getNum()));//todo ?


//            tvCpType.setText(String.format("公司类型：%s",data.get));
//            tvCpType.setText(String.format("地    址：%s",data.get));
//            tvCpType.setText(String.format("注 册 地：%s",data.get));
        }

    }
}

package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.OrderVo;

import java.util.List;

import butterknife.ButterKnife;


/**
 * Description: 推荐
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class MyCouponAdapter extends RecyclerArrayAdapter<OrderVo> {

    private int type;//对应的是不同的

    public MyCouponAdapter(Context context, List<OrderVo> objects) {
        super(context, objects);
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
            super(parent, R.layout.item_coupon);
            ButterKnife.bind(this, parent);
        }

        @Override
        public void setData(OrderVo data) {

        }

    }
}

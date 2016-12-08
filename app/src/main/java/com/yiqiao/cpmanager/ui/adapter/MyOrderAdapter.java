package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.OrderVo;
import com.yiqiao.cpmanager.ui.activity.OrderDetailActivity;
import com.yiqiao.cpmanager.widget.FullyHeightRecycleview;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description: 推荐
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class MyOrderAdapter extends RecyclerArrayAdapter<OrderVo> {

    private int type;
    public static final int TYPE_UNPAID=0;
    public static final int TYPE_PAID=1;
    public static final int TYPE_CANCELED=2;
    public void setType(int type) {
        this.type = type;
    }

    public MyOrderAdapter(Context context, List<OrderVo> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<OrderVo> {

        @BindView(R.id.recycleView)
        FullyHeightRecycleview recycleView;
        @BindView(R.id.llPriceLeft)
        LinearLayout llPriceLeft;
        @BindView(R.id.btCancel)
        Button btCancel;
        @BindView(R.id.btPay)
        Button btPay;
        @BindView(R.id.btRepay)
        Button btRepay;
        @BindView(R.id.llPriceRight)
        LinearLayout llPriceRight;

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_order);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(OrderVo data) {
            switch (type){
                case MyOrderAdapter.TYPE_UNPAID:
                    llPriceRight.setVisibility(View.GONE);
                    btRepay.setVisibility(View.GONE);

                    break;
                case MyOrderAdapter.TYPE_PAID:
                    btPay.setVisibility(View.GONE);
                    btCancel.setVisibility(View.GONE);
                    btRepay.setVisibility(View.GONE);
                    llPriceLeft.setVisibility(View.GONE);
                    break;
                case MyOrderAdapter.TYPE_CANCELED:
                    llPriceRight.setVisibility(View.GONE);
                    btPay.setVisibility(View.GONE);
                    btCancel.setVisibility(View.GONE);
                    break;

            }

//            recycleView.setLayoutManager(new FullyLinearLayoutManager(getContext()));
            recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
            ArrayList<OrderVo> arrayList = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                arrayList.add(new OrderVo());
            }
            MyOrderItemAdapter myOrderItemAdapter = new MyOrderItemAdapter(getContext(), arrayList);
            recycleView.setAdapter(myOrderItemAdapter);

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

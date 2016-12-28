package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.OrderVo;
import com.yiqiao.cpmanager.ui.activity.GoodsDetailActivity;
import com.yiqiao.cpmanager.widget.ColorTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description: 推荐
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class SearchSpuAdapter extends RecyclerArrayAdapter<OrderVo> {
    String keyWord;

    public SearchSpuAdapter(Context context, ArrayList<OrderVo> orderVos) {
        super(context, orderVos);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<OrderVo> {

        @BindView(R.id.tvName)
        ColorTextView tvName;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.tvDesc)
        TextView tvDesc;
        @BindView(R.id.tvPriceOld)
        TextView tvPriceOld;

        public RecommendViewHolder(ViewGroup parent) {
//            super(parent, R.layout.item_search_spu1);
            super(parent, R.layout.item_search_spu);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(OrderVo data) {
//            tvName.setText();
            tvName.findAndSetStrColor(keyWord, "#ff5b5c");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
//                    intent.putExtra()
                    getContext().startActivity(intent);
                }
            });
        }

    }
}

package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.component.ImageLoader;
import com.yiqiao.cpmanager.entity.RecommendVo;
import com.yiqiao.cpmanager.entity.RecommendVo;
import com.yiqiao.cpmanager.ui.activity.GoodsDetailActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description:
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class MgrRecommendAdapter extends RecyclerArrayAdapter<RecommendVo> {


    public MgrRecommendAdapter(Context context, ArrayList<RecommendVo> arrayList) {
        super(context, arrayList);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<RecommendVo> {

        @BindView(R.id.ivBg)
        ImageView ivBg;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvRemark)
        TextView tvRemark;
        @BindView(R.id.tvMoney)
        TextView tvMoney;

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_mgr_recommend);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(final RecommendVo data) {
            tvName.setText(data.getTitle());
            tvRemark.setText(data.getRemark());
            tvMoney.setText(data.getPrice());
            ImageLoader.load(getContext(),data.getImageHref(),ivBg);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getContext(), GoodsDetailActivity.class);
                    intent.putExtra(GoodsDetailActivity.KEY_TYPE,GoodsDetailActivity.TYPE_GOODS);
                    intent.putExtra(GoodsDetailActivity.KEY_ID,data.getHref());
                    getContext().startActivity(intent);
                }
            });
        }

    }
}

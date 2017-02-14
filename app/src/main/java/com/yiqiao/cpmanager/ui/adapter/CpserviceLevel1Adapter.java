package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.ServiceTypeLevel1Vo;
import com.yiqiao.cpmanager.entity.ServiceTypeLevel1Vo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description:
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class CpserviceLevel1Adapter extends RecyclerArrayAdapter<ServiceTypeLevel1Vo> {

    int selected = 0;

    public void setSelected(int selected) {
//        int preSelected=this.selected;
        this.selected = selected;
        notifyDataSetChanged();
//        notifyItemChanged(preSelected);
//        notifyItemChanged(this.selected);
    }

    public CpserviceLevel1Adapter(Context context, ArrayList<ServiceTypeLevel1Vo> arrayList) {
        super(context, arrayList);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<ServiceTypeLevel1Vo> {

        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.ivLogo)
        ImageView ivLogo;

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_cpservice_level1);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(ServiceTypeLevel1Vo data) {
            tvName.setText(data.getName());
            if (getDataPosition() == selected) {
                itemView.setBackgroundColor(Color.WHITE);
            } else {
                itemView.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.gray_bg));
            }
        }

    }
}

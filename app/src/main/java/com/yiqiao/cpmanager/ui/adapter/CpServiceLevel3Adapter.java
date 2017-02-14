package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.ServiceTypeLevel2Vo;
import com.yiqiao.cpmanager.entity.ServiceTypeLevel2Vo.ProductTypeThreeBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description:
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class CpServiceLevel3Adapter extends RecyclerArrayAdapter<ServiceTypeLevel2Vo.ProductTypeThreeBean> {

    int selected = -1;

    public void setSelected(int selected) {
//        int preSelected=this.selected;
        this.selected = selected;
        notifyDataSetChanged();
//        notifyItemChanged(preSelected);
//        notifyItemChanged(this.selected);
    }

    public CpServiceLevel3Adapter(Context context, List<ProductTypeThreeBean> arrayList) {
        super(context, arrayList==null?new ArrayList<ProductTypeThreeBean>():arrayList);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<ProductTypeThreeBean> {


        @BindView(R.id.tvTitle)
        TextView tvTitle;

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_cpservice_data);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(ProductTypeThreeBean data) {
            tvTitle.setText(data.getSortName());
            if (getDataPosition() == selected) {
                tvTitle.setTextColor(ContextCompat.getColor(getContext(), R.color.blue_bt_repay_bg));
            } else {
                tvTitle.setTextColor(ContextCompat.getColor(getContext(), R.color.text_general));
            }
        }

    }
}

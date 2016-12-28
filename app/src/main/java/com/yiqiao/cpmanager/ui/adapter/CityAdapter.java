package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.CityVo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description:
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class CityAdapter extends RecyclerArrayAdapter<CityVo> {

    int selected=-1;

    public void setSelected(int selected) {
//        int preSelected=this.selected;
        this.selected = selected;
        notifyDataSetChanged();
//        notifyItemChanged(preSelected);
//        notifyItemChanged(this.selected);
    }

    public CityAdapter(Context context, ArrayList<CityVo> arrayList) {
        super(context, arrayList);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<CityVo> {

        @BindView(R.id.tvName)
        TextView tvName;

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_city);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(CityVo data) {
            tvName.setText(data.getName());
            if(getDataPosition()==selected){
                tvName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.location_blue,0,0,0);
                tvName.setTextColor(ContextCompat.getColor(getContext(),R.color.blue_bt_repay_bg));
            }else {
                tvName.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                tvName.setTextColor(ContextCompat.getColor(getContext(),R.color.text_black_title));
            }
        }

    }
}

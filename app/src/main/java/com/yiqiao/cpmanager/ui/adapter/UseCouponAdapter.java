package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.CouponVo;
import com.yiqiao.cpmanager.util.DateUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description: 推荐
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class UseCouponAdapter extends RecyclerArrayAdapter<CouponVo.RecordListBean> {
    int selected=-1;

    public void setSelected(int selected) {
        this.selected = selected;
        notifyDataSetChanged();
    }

    public int getSelected() {
        return selected;
    }

    public UseCouponAdapter(Context context, ArrayList<CouponVo.RecordListBean> recordListBeen) {
        super(context,recordListBeen);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<CouponVo.RecordListBean> {

        @BindView(R.id.ivSelect)
        ImageView ivSelect;
        @BindView(R.id.tvNum)
        TextView tvNum;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvDate)
        TextView tvDate;

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_use_coupon);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(CouponVo.RecordListBean data) {
            tvNum.setText(String.format("￥ %s", data.getAmount()));
            tvDate.setText(DateUtil.formartTimeStamp(data.getEndDate(), "yyyy-MM-dd"));
            tvName.setText(data.getGoodsTypesInfo());//todo 对应哪个字段？
            if(selected==getAdapterPosition()){
                ivSelect.setImageResource(R.drawable.choose_down);
            }else {
                ivSelect.setImageResource(R.drawable.choose_nor);
            }
        }

    }
}

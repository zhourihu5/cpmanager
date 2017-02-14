package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.CouponVo;
import com.yiqiao.cpmanager.util.DateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description: 推荐
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class MyCouponAdapter extends RecyclerArrayAdapter<CouponVo.RecordListBean> {

    private int type;//对应的是不同的

    public MyCouponAdapter(Context context, List<CouponVo.RecordListBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    public void setType(int type) {
        this.type = type;
    }

    class RecommendViewHolder extends BaseViewHolder<CouponVo.RecordListBean> {

        @BindView(R.id.tvNum)
        TextView tvNum;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvDateStatus)
        TextView tvDateStatus;
        @BindView(R.id.llBg)
        LinearLayout llBg;

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_coupon);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(CouponVo.RecordListBean data) {
            tvNum.setText(String.format("￥ %s", data.getAmount()));
            tvDate.setText(DateUtil.formartTimeStamp(data.getEndDate(), "yyyy-MM-dd"));
            tvName.setText(data.getGoodsTypesInfo());//todo 对应哪个字段？

            if("1".equals(data.getIsToOverEndTime())){//是否即将过期，5天以内即将过期
                tvDateStatus.setText("即将过期");
                tvDateStatus.setTextColor(ContextCompat.getColor(getContext(),R.color.red_my));
            }
            if(type==0){
                llBg.setBackgroundResource(R.drawable.coupon_red_bg);
            }else if(type==1){
                llBg.setBackgroundResource(R.drawable.coupon_gray_bg);
                tvDateStatus.setText("已使用");
                tvDateStatus.setTextColor(ContextCompat.getColor(getContext(),R.color.text_general));
            }else {
                llBg.setBackgroundResource(R.drawable.coupon_gray_bg);
                tvDateStatus.setText("已过期");
                tvDateStatus.setTextColor(ContextCompat.getColor(getContext(),R.color.text_general));
            }
        }

    }
}

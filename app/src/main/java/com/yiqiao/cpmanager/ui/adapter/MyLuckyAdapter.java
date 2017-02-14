package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.LuckyMoneyVo;
import com.yiqiao.cpmanager.util.DateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description: 推荐
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class MyLuckyAdapter extends RecyclerArrayAdapter<LuckyMoneyVo.RecordListBean> {


    public MyLuckyAdapter(Context context, List<LuckyMoneyVo.RecordListBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<LuckyMoneyVo.RecordListBean> {

        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvMoney)
        TextView tvMoney;

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_my_lucky);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(LuckyMoneyVo.RecordListBean data) {
            tvName.setText(data.getActivityName());
            String money=null;
            int color=0xffff5b5c;
            if(data.getRedType()==1){
                //收支情况 1领取 2使用
                color=0xffff5b5c;
                money=String.format("+%s",data.getRedAmount());
            }else {
                color=0xff57b52c;
                money=String.format("-%s",data.getRedAmount());
            }
            tvMoney.setText(money);
            tvMoney.setTextColor(color);
            tvDate.setText(DateUtil.formartTimeStamp(data.getCreateTime()));
        }

    }
}

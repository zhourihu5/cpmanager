package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.utils.StringUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.ChargeBackVo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description: 推荐
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class MyChargeBackAdapter extends RecyclerArrayAdapter<ChargeBackVo> {
    public MyChargeBackAdapter(Context context, List<ChargeBackVo> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<ChargeBackVo> {

        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvOrderStatus)
        TextView tvOrderStatus;
        @BindView(R.id.tvServeStatus)
        TextView tvServeStatus;
        @BindView(R.id.tvSite)
        TextView tvSite;
        @BindView(R.id.tvLinkman)
        TextView tvLinkman;
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvBuyAmount)
        TextView tvBuyAmount;
        @BindView(R.id.tvChargeBackNum)
        TextView tvChargeBackNum;

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_my_charge_back);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(ChargeBackVo data) {
            tvName.setText(data.getSkuName());
            tvChargeBackNum.setText(String.format("%s元",data.getReturnsAmount()));
            String temp=data.getBuyAmount();
            if(!StringUtils.isEmpty(temp)){
                temp=String.format("实付金额：%s元",data.getBuyAmount());
            }
            tvBuyAmount.setText(temp);

            temp=data.getApplyTime();
            if(!StringUtils.isEmpty(temp)){
                temp=String.format("申请时间：%s",data.getApplyTime());
            }
            tvDate.setText(temp);

            temp=data.getDeptName();
            if(!StringUtils.isEmpty(temp)){
                temp=String.format("服务网点：%s",data.getDeptName());
            }
            tvSite.setText(temp);

            temp=data.getContacts();
            if(!StringUtils.isEmpty(data.getContacts())&&!StringUtils.isEmpty(data.getPhone())){
                temp=String.format("联系人：%s %s",data.getContacts(),data.getPhone());
            }
            else if(!StringUtils.isEmpty(data.getContacts())&&StringUtils.isEmpty(data.getPhone())){
                temp=String.format("联系人：%s",data.getContacts());
            }
            else if(!StringUtils.isEmpty(data.getPhone())&&StringUtils.isEmpty(data.getContacts())){
                temp=String.format("联系人：%s",data.getPhone());
            }
            tvLinkman.setText(temp);

            String dealState=data.getDealState();
            if(dealState==null){
                tvOrderStatus.setVisibility(View.GONE);
            }else {
                tvOrderStatus.setVisibility(View.VISIBLE);
                switch (data.getDealState()){//"dealState": 退单状态 1待提交 2未退款 3已退款 4已取消 5待确认

                    case "3":
                        tvOrderStatus.setText("退款成功");
                        tvOrderStatus.setTextColor(Color.parseColor("#ff7c00"));
                        break;
                    case "4":
                        tvOrderStatus.setText("退款驳回");
                        tvOrderStatus.setTextColor(Color.parseColor("#828a92"));
                        break;
                    default:
                        tvOrderStatus.setText("申请中");
                        tvOrderStatus.setTextColor(Color.parseColor("#0eb98f"));
                        break;


                }
            }
            if("0".equals(data.getReturnsType())){
                tvServeStatus.setText("未服务");
            }else {
                tvServeStatus.setText("已服务");
            }

        }

    }
}

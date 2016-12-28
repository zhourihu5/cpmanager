package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.ServiceVo;
import com.yiqiao.cpmanager.ui.activity.ServiceDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description: 推荐
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class ManagerAdapter extends RecyclerArrayAdapter<ServiceVo> {

    public ManagerAdapter(Context context, List<ServiceVo> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<ServiceVo> {

        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvStatus)
        TextView tvStatus;
        @BindView(R.id.tvSite)
        TextView tvSite;
        @BindView(R.id.tvStep1)
        TextView tvStep1;
        @BindView(R.id.tvStep2)
        TextView tvStep2;
        @BindView(R.id.tvStep3)
        TextView tvStep3;
        @BindView(R.id.tvLinkman)
        TextView tvLinkman;
        @BindView(R.id.tvPhone)
        TextView tvPhone;
        @BindView(R.id.tvStart)
        TextView tvStart;

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_manager_service);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(ServiceVo data) {
            tvName.setText(data.getServiceName());
            tvSite.setText(data.getOrgName());
            tvLinkman.setText(data.getContactName());
            tvPhone.setText(String.format("%s",data.getContactPhone()));

            switch (data.getIsEnd()){//是否结束	默认0  结束1
                case 0:

                    break;
                default:

                    break;
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getContext().startActivity(new Intent(getContext(), ServiceDetailActivity.class));
                }
            });
        }

    }
}

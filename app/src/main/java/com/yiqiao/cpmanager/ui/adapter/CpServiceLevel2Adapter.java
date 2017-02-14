package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.ServiceTypeLevel2Vo;
import com.yiqiao.cpmanager.entity.CpServiceVo;
import com.yiqiao.cpmanager.entity.ServiceTypeLevel2Vo;
import com.yiqiao.cpmanager.ui.activity.SearchSpuActivity;
import com.yiqiao.cpmanager.widget.FullyHeightRecycleview;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description:
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class CpServiceLevel2Adapter extends RecyclerArrayAdapter<ServiceTypeLevel2Vo> {

    int selected = -1;
    int childSelected=-1;
    String pid;

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setSelected(int selected) {
//        int preSelected=this.selected;
        this.selected = selected;
        notifyDataSetChanged();
//        notifyItemChanged(preSelected);
//        notifyItemChanged(this.selected);
    }

    public CpServiceLevel2Adapter(Context context, ArrayList<ServiceTypeLevel2Vo> arrayList) {
        super(context, arrayList);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<ServiceTypeLevel2Vo> {


        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.recycleView)
        FullyHeightRecycleview recycleView;

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_cpservice);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(final ServiceTypeLevel2Vo data) {
            tvTitle.setText(data.getSortName());
            recycleView.setLayoutManager(new GridLayoutManager(getContext(),3));
            final CpServiceLevel3Adapter serviceLevel3Adapter=new CpServiceLevel3Adapter(getContext(),data.getProductTypeThree());
            recycleView.setAdapter(serviceLevel3Adapter);
            if(selected==getAdapterPosition()){
                serviceLevel3Adapter.setSelected(childSelected);
            }else {
                serviceLevel3Adapter.setSelected(-1);
            }
            serviceLevel3Adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                   ServiceTypeLevel2Vo.ProductTypeThreeBean productTypeThreeBean= serviceLevel3Adapter.getItem(position);
                    selected=getAdapterPosition();
                    childSelected=position;
                    notifyDataSetChanged();
                    Intent intent=new Intent(getContext(), SearchSpuActivity.class);
                    intent.putExtra(SearchSpuActivity.LEVEL3_ID,productTypeThreeBean.getId());
                    intent.putExtra(SearchSpuActivity.LEVEL2_ID,data.getId());
                    intent.putExtra(SearchSpuActivity.LEVEL1_ID,pid);
                    getContext().startActivity(intent);
                }
            });
//            if (getDataPosition() == selected) {
//                tvName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.location_blue, 0, 0, 0);
//                tvName.setTextColor(ContextCompat.getColor(getContext(), R.color.blue_bt_repay_bg));
//            } else {
//                tvName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                tvName.setTextColor(ContextCompat.getColor(getContext(), R.color.text_black_title));
//            }
        }

    }
}

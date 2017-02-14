package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.SkuDetailVo;
import com.yiqiao.cpmanager.ui.activity.OrderDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description: 附加产品
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class GoodsDetailApendAdapter extends RecyclerArrayAdapter<SkuDetailVo.ServiceProductsBean> {

    SelectedListner selectedListner;

    public void setSelectedListner(SelectedListner selectedListner) {
        this.selectedListner = selectedListner;
    }

    public GoodsDetailApendAdapter(Context context, List<SkuDetailVo.ServiceProductsBean> list) {
        super(context, list==null?new ArrayList<SkuDetailVo.ServiceProductsBean>():list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<SkuDetailVo.ServiceProductsBean> {


        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvPrice)
        TextView tvPrice;

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_goods_detail_append);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(final SkuDetailVo.ServiceProductsBean data) {
            tvName.setText(data.getName());
            tvPrice.setText(String.format("%s元",data.getServiceSalesPrice()));
            if("1".equals(data.getIsMust())||data.isSelected()){
                tvName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.choose_down,0,0,0);
            }else {
                tvName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.choose_nor,0,0,0);
            }
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(!"1".equals(data.getIsMust())){
//                        boolean selected=data.isSelected();
//                        data.setSelected(!selected);
//                        notifyDataSetChanged();
//                        if(selectedListner!=null){
//                            selectedListner.onItemSelected(data);
//                        }
//                    }
//                }
//            });
        }

    }
    public interface SelectedListner{
        public void onItemSelected(SkuDetailVo.ServiceProductsBean data);
    }
}

package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.StoreVo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description: 推荐
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class StoreChooseAdapter extends RecyclerArrayAdapter<StoreVo.PageBean.RecordListBean> {


    private int selected;

    public void setSelected(int selected) {
        this.selected = selected;
        notifyDataSetChanged();
    }

    public StoreChooseAdapter(Context context, List<StoreVo.PageBean.RecordListBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    public int getSelected() {
        return selected;
    }

    class RecommendViewHolder extends BaseViewHolder<StoreVo.PageBean.RecordListBean> {


        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvAddress)
        TextView tvAddress;
        @BindView(R.id.ivSelect)
        ImageView ivSelect;

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_store_choose);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(StoreVo.PageBean.RecordListBean data) {
            tvName.setText(data.getDeptName());
//            tvLinkman.setText(String.format("联系人：%s",data.getContacts()));
//            tvPhone.setText(String.format("电 话：%s",data.getPhone()));
            tvAddress.setText(String.format("地 址：%s", data.getDeptAddress()));
            if (getAdapterPosition() == selected) {
                ivSelect.setImageResource(R.drawable.choose_down);
            }else {
                ivSelect.setImageResource(R.drawable.choose_nor);
            }
        }

    }
}

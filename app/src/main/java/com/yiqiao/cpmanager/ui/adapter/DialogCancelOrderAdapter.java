package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.CancleReasonVo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description:
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class DialogCancelOrderAdapter extends RecyclerArrayAdapter<CancleReasonVo> {

    int selected = 0;

    public void setSelected(int selected) {
//        int preSelected=this.selected;
        this.selected = selected;
        notifyDataSetChanged();
//        notifyItemChanged(preSelected);
//        notifyItemChanged(this.selected);
    }

    public CancleReasonVo getSelectedReason() {
        return getItem(selected);
    }

    public DialogCancelOrderAdapter(Context context, ArrayList<CancleReasonVo> arrayList) {
        super(context, arrayList);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<CancleReasonVo> {

        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.ivLine)
        ImageView ivLine;
        @BindView(R.id.ivSelect)
        ImageView ivSelect;

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_dialog_cancel);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(CancleReasonVo data) {
            tvName.setText(data.getName());
            if (getDataPosition() == 0) {
                ivLine.setVisibility(View.INVISIBLE);
            }else {
                ivLine.setVisibility(View.VISIBLE);
            }
            if (getDataPosition() == selected) {
               ivSelect.setVisibility(View.VISIBLE);
                tvName.setTextColor(ContextCompat.getColor(getContext(),R.color.blue_bt_repay_bg));
            } else {
                ivSelect.setVisibility(View.GONE);
                tvName.setTextColor(ContextCompat.getColor(getContext(),R.color.text_general));
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selected=getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }

    }
}

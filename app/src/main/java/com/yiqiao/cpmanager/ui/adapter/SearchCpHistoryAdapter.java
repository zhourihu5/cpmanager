package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.OrderVo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description: 推荐
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class SearchCpHistoryAdapter extends RecyclerArrayAdapter<OrderVo> {

    public SearchCpHistoryAdapter(Context context, List<OrderVo> OrderVos) {
        super(context, OrderVos);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<OrderVo> {

        @BindView(R.id.tvName)
        TextView tvName;

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_search_cp_history);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(final OrderVo data) {
//            tvName.setText(data.getName());
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent=new Intent(getContext(),SearchTradeMarkActivity.class);
//                    intent.putExtra(SearchTradeMarkActivity.KEY_WORD,data.getName());
//                    getContext().startActivity(intent);
//                }
//            });
        }

    }
}

package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.db.SearchTradeMarkHistoryVo;
import com.yiqiao.cpmanager.ui.activity.SearchTradeMarkActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description: 推荐
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class SearchTrademarkHistoryAdapter extends RecyclerArrayAdapter<SearchTradeMarkHistoryVo> {

    public SearchTrademarkHistoryAdapter(Context context, List<SearchTradeMarkHistoryVo> SearchTradeMarkVos) {
        super(context, SearchTradeMarkVos);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<SearchTradeMarkHistoryVo> {

        @BindView(R.id.tvName)
        TextView tvName;

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_search_cp_history);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(final SearchTradeMarkHistoryVo data) {
            tvName.setText(data.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getContext(),SearchTradeMarkActivity.class);
                    intent.putExtra(SearchTradeMarkActivity.KEY_WORD,data.getName());
                    getContext().startActivity(intent);
                }
            });
        }

    }
}

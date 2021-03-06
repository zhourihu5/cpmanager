package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.TrademarkVo;

import java.util.ArrayList;

import butterknife.ButterKnife;


/**
 * Description: 推荐
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class SearchTradeMarkAdapter extends RecyclerArrayAdapter<TrademarkVo> {

    public SearchTradeMarkAdapter(Context context, ArrayList<TrademarkVo> TrademarkVos) {
        super(context,TrademarkVos);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }
    class RecommendViewHolder extends BaseViewHolder<TrademarkVo> {

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_search_trade_mark);
            ButterKnife.bind(this, parent);
        }

        @Override
        public void setData(TrademarkVo data) {

        }

    }
}

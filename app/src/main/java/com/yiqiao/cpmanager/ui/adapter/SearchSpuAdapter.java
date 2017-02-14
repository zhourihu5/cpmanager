package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.StringUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.component.ImageLoader;
import com.yiqiao.cpmanager.entity.SkuVo;
import com.yiqiao.cpmanager.entity.SkuVo;
import com.yiqiao.cpmanager.ui.activity.GoodsDetailActivity;
import com.yiqiao.cpmanager.widget.ColorTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description: 推荐
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class SearchSpuAdapter extends RecyclerArrayAdapter<SkuVo> {
    String keyWord;

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
        notifyDataSetChanged();
    }

    public SearchSpuAdapter(Context context, ArrayList<SkuVo> SkuVos) {
        super(context, SkuVos);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<SkuVo> {

        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.tvDesc)
        TextView tvDesc;
        @BindView(R.id.tvPriceOld)
        TextView tvPriceOld;
        @BindView(R.id.ivLogo)
        ImageView ivLogo;

        public RecommendViewHolder(ViewGroup parent) {
//            super(parent, R.layout.item_search_spu1);
            super(parent, R.layout.item_search_spu);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(final SkuVo data) {
//            tvName.setText();
            String name=data.getName();
            String mCurrentText=name;
            if(!StringUtils.isEmpty(name)&&!StringUtils.isEmpty(keyWord)){
                mCurrentText=name.replace(keyWord,color("#fc2442",keyWord));
            }
            tvName.setText(Html.fromHtml(mCurrentText));
            tvPrice.setText(data.getPrice());
            tvPriceOld.setText(data.getOriginPrice());
            tvPriceOld.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
            tvDesc.setText(data.getDescription());
            if(StringUtils.isEmpty(data.getImgUrl())){
                ivLogo.setVisibility(View.GONE);
            }else {
                ImageLoader.load(getContext(),data.getImgUrl(),ivLogo,R.drawable.img_default_spulist);
                ivLogo.setVisibility(View.VISIBLE);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
                    intent.putExtra(GoodsDetailActivity.KEY_ID,data.getTid());
                    intent.putExtra(GoodsDetailActivity.KEY_TYPE,data.getType());
                    getContext().startActivity(intent);
                }
            });
        }

    }
    public String color(String colorCode, String str) {
        return "<font color=\"" + colorCode + "\">" + str + "</font>";
    }
}

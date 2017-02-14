package com.yiqiao.cpmanager.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean;
import com.yiqiao.cpmanager.entity.SkuDetailVo;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description: 推荐
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class GoodsDetailAttrAdapter extends RecyclerArrayAdapter<SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean> {
    SelecteListner selecteListner;

    public void setSelecteListner(SelecteListner selecteListner) {
        this.selecteListner = selecteListner;
    }

    public GoodsDetailAttrAdapter(Context context, List<SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean> list) {
        super(context, list==null?new ArrayList<SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean>():list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean> {

        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.flServiceType)
        TagFlowLayout flServiceType;

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_goods_detail_attribute);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(final SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean data) {
            tvName.setText(data.getName());
            final MyTagAdapter  typeTagAdapter = new MyTagAdapter(data.getAttributeValuePoList());
            flServiceType.setAdapter(typeTagAdapter);
            typeTagAdapter.setSelectedPosition(data.getCheckedItem());
            flServiceType.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean.AttributeValuePoListBean attributeValuePoListBean= typeTagAdapter.getItem(position);
                    String id= attributeValuePoListBean.getId();
                    if(!data.getCheckedItem().equals(id)) {
                        data.setCheckedItem(id);
                        //todo sort in fragment
//                        List<SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean>listBeen=new ArrayList<AttribbuteInfoVoListBean>();
//                        listBeen.addAll(getAllData());
//                        Collections.sort(listBeen, new Comparator<AttribbuteInfoVoListBean>() {
//                            @Override
//                            public int compare(SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean o1, SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean o2) {
//                                return Integer.valueOf(o2.getId())-Integer.valueOf(o1.getId());
//                            }
//                        });
//
//                        StringBuilder sb=new StringBuilder();
//                        for(SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean bean:listBeen ){
//                            sb.append(bean.getCheckedItem()).append(",");
//                        }
//                      String selectedIds= sb.deleteCharAt(sb.lastIndexOf(",")).toString();
//                        if(selecteListner!=null){
//                            selecteListner.select(selectedIds);
//                        }
                        if(selecteListner!=null){
                            selecteListner.select(id);
                        }
                        typeTagAdapter.setSelectedPosition(id);

                    }
                    return true;
                }
            });
        }

    }
    public interface SelecteListner{
        public void select(String selectedIds);
    }

    class MyTagAdapter extends TagAdapter<SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean.AttributeValuePoListBean> {
        String selectedPosition = "";

        public void setSelectedPosition(String selectedPosition) {
            this.selectedPosition = selectedPosition;
            notifyDataChanged();
        }

        public MyTagAdapter(List<SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean.AttributeValuePoListBean> datas) {
            super(datas);
        }

        @Override
        public View getView(FlowLayout parent, int position, SkuDetailVo.SkuInfoVoBean.AttribbuteInfoVoListBean.AttributeValuePoListBean s) {
            View view = View.inflate(getContext(), R.layout.item_goods_detail_tag, null);
            ImageView ivTag = (ImageView) view.findViewById(R.id.ivSelect);
            TextView tvTag = (TextView) view.findViewById(R.id.tvTag);
            tvTag.setText(s.getAttrValueName());
            if (s.getId().equals(selectedPosition)) {
                ivTag.setVisibility(View.VISIBLE);
                tvTag.setSelected(true);
            } else {
                ivTag.setVisibility(View.GONE);
                tvTag.setSelected(false);
            }
            return view;
        }
    }
}

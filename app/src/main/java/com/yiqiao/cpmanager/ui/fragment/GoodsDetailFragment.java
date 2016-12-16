package com.yiqiao.cpmanager.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseFragment;
import com.yiqiao.cpmanager.entity.OrderVo;
import com.yiqiao.cpmanager.ui.activity.ConfirmOrderActivity;
import com.yiqiao.cpmanager.ui.adapter.GoodsDetailApendAdapter;
import com.yiqiao.cpmanager.ui.adapter.GoodsDetailDonateAdapter;
import com.yiqiao.cpmanager.widget.FullyHeightRecycleview;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by codeest on 2016/8/11.
 * 商品详情页
 */
public class GoodsDetailFragment extends BaseFragment {

    MyTagAdapter typeTagAdapter;
    MyTagAdapter addressAdapter;
    MyTagAdapter dateAdapter;
    GoodsDetailApendAdapter apendAdapter;
    GoodsDetailDonateAdapter donateAdapter;

    @BindView(R.id.tvPay)
    TextView tvPay;
    @BindView(R.id.flServiceType)
    TagFlowLayout flServiceType;
    @BindView(R.id.flAddress)
    TagFlowLayout flAddress;
    @BindView(R.id.flDate)
    TagFlowLayout flDate;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.llPriceLeft)
    LinearLayout llPriceLeft;
    @BindView(R.id.rvDonate)
    FullyHeightRecycleview rvDonate;
    @BindView(R.id.rvAppend)
    FullyHeightRecycleview rvAppend;


    @Override
    protected int getLayoutId() {
        return R.layout.frag_goods_detail;
    }

    @Override
    protected void initEventAndData() {
        String[] arr = {"有限公司", "有限合伙企业", "股份有限公司", "自贸区内资公司", "分公司", "集团公司",};
        List<String> tags = new ArrayList<>();
        for (String str : arr) {
            tags.add(str);
        }
        typeTagAdapter = new MyTagAdapter(tags);
        flServiceType.setAdapter(typeTagAdapter);
        flServiceType.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                typeTagAdapter.setSelectedPosition(position);
                return true;
            }
        });

        String[] arr2 = {"自有地址", "使用推荐地址",};
        List<String> tags2 = new ArrayList<>();
        for (String str : arr2) {
            tags2.add(str);
        }
        addressAdapter = new MyTagAdapter(tags2);
        flAddress.setAdapter(addressAdapter);
        flAddress.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                addressAdapter.setSelectedPosition(position);
                return true;
            }
        });

        String[] arr3 = {"1个月", "3个月", "6个月", "12个月",};
        List<String> tags3 = new ArrayList<>();
        for (String str : arr3) {
            tags3.add(str);
        }
        dateAdapter = new MyTagAdapter(tags3);
        flDate.setAdapter(dateAdapter);
        flDate.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                dateAdapter.setSelectedPosition(position);
                return true;
            }
        });


        rvAppend.setLayoutManager(new LinearLayoutManager(mContext));
        ArrayList<OrderVo>arrayList=new ArrayList<>();
        for(int i=0;i<10;i++){
            arrayList.add(new OrderVo());
        }
        apendAdapter=new GoodsDetailApendAdapter(mContext,arrayList);
        rvAppend.setAdapter(apendAdapter);

        rvDonate.setLayoutManager(new LinearLayoutManager(mContext));
        ArrayList<OrderVo>arrayList2=new ArrayList<>();
        for(int i=0;i<2;i++){
            arrayList2.add(new OrderVo());
        }
        donateAdapter=new GoodsDetailDonateAdapter(mContext,arrayList2);
        rvDonate.setAdapter(donateAdapter);



    }


    public static GoodsDetailFragment getInstance() {
        return new GoodsDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.tvPay)
    public void onClick() {
        toActivity(ConfirmOrderActivity.class);
    }


    class MyTagAdapter extends TagAdapter<String> {
        int selectedPosition = 0;

        public void setSelectedPosition(int selectedPosition) {
            this.selectedPosition = selectedPosition;
            notifyDataChanged();
        }

        public MyTagAdapter(List<String> datas) {
            super(datas);
        }

        @Override
        public View getView(FlowLayout parent, int position, String s) {
            View view = View.inflate(mContext, R.layout.item_goods_detail_tag, null);
            ImageView ivTag = (ImageView) view.findViewById(R.id.ivSelect);
            TextView tvTag = (TextView) view.findViewById(R.id.tvTag);
            tvTag.setText(s);
            if (position == selectedPosition) {
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

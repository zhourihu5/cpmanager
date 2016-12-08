package com.yiqiao.cpmanager.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseFragment;
import com.yiqiao.cpmanager.ui.activity.ConfirmOrderActivity;
import com.yiqiao.cpmanager.ui.activity.OrderDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by codeest on 2016/8/11.
 * 商品详情页
 */
public class GoodsDetailFragment extends BaseFragment {


    @BindView(R.id.tvPay)
    TextView tvPay;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_goods_detail;
    }

    @Override
    protected void initEventAndData() {
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
}

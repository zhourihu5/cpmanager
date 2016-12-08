package com.yiqiao.cpmanager.ui.fragment;

import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseFragment;

/**
 * Created by codeest on 2016/8/11.
 * 服务门店
 */
public class GoodsStoreFragment extends BaseFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.frag_goods_detail;
    }

    @Override
    protected void initEventAndData() {
    }


    public static GoodsStoreFragment getInstance() {
        return new GoodsStoreFragment();
    }
}

package com.yiqiao.cpmanager.ui.fragment;

import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseFragment;

/**
 * Created by codeest on 2016/8/11.
 * 服务保障
 */
public class GoodsSafeguardFragment extends BaseFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.frag_goods_safeguard;
    }

    @Override
    protected void initEventAndData() {
    }


    public static GoodsSafeguardFragment getInstance() {
        return new GoodsSafeguardFragment();
    }
}

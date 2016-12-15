package com.yiqiao.cpmanager.ui.activity;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.app.Constants;
import com.yiqiao.cpmanager.base.BaseActivity;

/**
 * Created by Xu on 2016/11/23.
 * 确认订单
 */

public class ConfirmOrderActivity extends BaseActivity{
    private IWXAPI api;
    @Override
    protected int getLayout() {
        return R.layout.activity_confirm_order;
    }

    @Override
    protected void initEventAndData() {
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
    }

}

package com.yiqiao.cpmanager.ui.activity;

import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.ui.adapter.ActivityNoticeAdapter;
import com.yiqiao.cpmanager.ui.adapter.ServiceDetailAdapter;

/**
 * Created by Xu on 2016/11/23.
 * 服务明细
 */

public class ServiceDetailActivity extends BaseActivity{

   ServiceDetailAdapter serviceDetailAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_service_detail;
    }

    @Override
    protected void initEventAndData() {

    }

}

package com.yiqiao.cpmanager.ui.activity;

import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.ui.adapter.ServiceNoticeAdapter;

/**
 * Created by Xu on 2016/11/23.
 * 系统消息
 */

public class SystemNoticeActivity extends BaseActivity{

    ServiceNoticeAdapter serviceNoticeAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_charge_back;
    }

    @Override
    protected void initEventAndData() {

    }

}

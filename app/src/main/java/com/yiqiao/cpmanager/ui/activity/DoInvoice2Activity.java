package com.yiqiao.cpmanager.ui.activity;

import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.ui.adapter.DoInvoice2AddressAdapter;
import com.yiqiao.cpmanager.ui.adapter.DoInvoice2HeadAdapter;

/**
 * Created by Xu on 2016/11/23.
 * 我要开票
 */

public class DoInvoice2Activity extends BaseActivity{

    DoInvoice2AddressAdapter doInvoice2Adapter;
    DoInvoice2HeadAdapter doInvoice2HeadAdapter;
    @Override
    protected int getLayout() {
        return R.layout.activity_do_invoice2;
    }

    @Override
    protected void initEventAndData() {

    }

}

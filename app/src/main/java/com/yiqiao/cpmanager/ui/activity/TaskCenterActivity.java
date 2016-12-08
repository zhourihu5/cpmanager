package com.yiqiao.cpmanager.ui.activity;

import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.ui.adapter.SearchCpAdapter;
import com.yiqiao.cpmanager.ui.adapter.TaskCenterAdapter;

/**
 * Created by Xu on 2016/11/23.
 */

public class TaskCenterActivity extends BaseActivity{

    TaskCenterAdapter taskCenterAdapter;
    @Override
    protected int getLayout() {
        return R.layout.activity_task_center;
    }

    @Override
    protected void initEventAndData() {

    }

}

package com.yiqiao.cpmanager.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.utils.RegexUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseFragment;
import com.yiqiao.cpmanager.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by codeest on 2016/8/11.
 */
public class RegistPhoneFragment extends BaseFragment {


    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.btNext)
    Button btNext;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_regist_phone;
    }

    @Override
    protected void initEventAndData() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.btNext)
    public void onClick() {
        String phone=etPhone.getText().toString();
        if(StringUtils.isEmpty(phone)){
            ToastUtil.shortShow("手机号不能为空");
            return;
        }
        if(RegexUtils.isMobileExact(phone)){
            ToastUtil.shortShow("请输入正确的手机号");
            return;
        }
    }
}

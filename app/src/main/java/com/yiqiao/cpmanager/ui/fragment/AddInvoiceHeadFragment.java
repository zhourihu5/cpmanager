package com.yiqiao.cpmanager.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

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
public class AddInvoiceHeadFragment extends BaseFragment {


    int typye;
    @BindView(R.id.etCpName)
    EditText etCpName;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.etAddress)
    EditText etAddress;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etBank)
    EditText etBank;
    @BindView(R.id.etCard)
    EditText etCard;
    @BindView(R.id.llSpcecial)
    LinearLayout llSpcecial;
    @BindView(R.id.btAdd)
    Button btAdd;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_add_invoice_head;
    }

    @Override
    protected void initEventAndData() {
        switch (typye){
            case 0:
                llSpcecial.setVisibility(View.GONE);
                break;
            case 1:

                break;
        }
    }


    public static AddInvoiceHeadFragment getInstance(int i) {
        AddInvoiceHeadFragment addInvoiceHeadFragment = new AddInvoiceHeadFragment();
        addInvoiceHeadFragment.typye = i;
        return addInvoiceHeadFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.btAdd)
    public void onClick() {
        if(verified()){
            //todo 添加发票抬头

        }
    }

    private boolean verified() {
        //todo 校验规则
        if(StringUtils.isEmpty(etCpName.getText().toString())){
            ToastUtil.shortShow("单位名称不能为空");
            return false;
        }
        return true;
    }
}

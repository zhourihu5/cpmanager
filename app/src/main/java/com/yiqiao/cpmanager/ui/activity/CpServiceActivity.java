package com.yiqiao.cpmanager.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.entity.CpServiceVo;
import com.yiqiao.cpmanager.entity.ServiceTypeLevel1Vo;
import com.yiqiao.cpmanager.http.RetrofitHelper;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.transformer.DefaultTransformer;
import com.yiqiao.cpmanager.ui.adapter.ContentPagerAdapter;
import com.yiqiao.cpmanager.ui.adapter.CpServiceAdapter;
import com.yiqiao.cpmanager.ui.adapter.CpserviceLevel1Adapter;
import com.yiqiao.cpmanager.ui.fragment.CpServiceFragment;
import com.yiqiao.cpmanager.util.SystemUtil;
import com.yiqiao.cpmanager.widget.UnScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;

/**
 * Created by Xu on 2016/11/23.
 * 公司服务
 */

public class CpServiceActivity extends BaseActivity {

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;

    @BindView(R.id.tvBusinessService)
    TextView tvBusinessService;
    @BindView(R.id.llBusinessService)
    LinearLayout llBusinessService;
    @BindView(R.id.tvTaxService)
    TextView tvTaxService;
    @BindView(R.id.llTaxService)
    LinearLayout llTaxService;
    @BindView(R.id.tvIpService)
    TextView tvIpService;
    @BindView(R.id.llIpService)
    LinearLayout llIpService;
    @BindView(R.id.tvGovermentService)
    TextView tvGovermentService;
    @BindView(R.id.llGovermentService)
    LinearLayout llGovermentService;
    @BindView(R.id.tvSocialInsurance)
    TextView tvSocialInsurance;
    @BindView(R.id.llSocialInsurance)
    LinearLayout llSocialInsurance;


    ContentPagerAdapter contentPagerAdapter;

    CpserviceLevel1Adapter cpserviceLevel1Adapter;
    @BindView(R.id.viewPager)
    UnScrollViewPager viewPager;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    @Override
    protected int getLayout() {
        return R.layout.activity_cp_service;
    }

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
        tvTitle.setText("公司服务");

    }

    @Override
    protected void initEventAndData() {
        recycleView.setLayoutManager(new LinearLayoutManager(mContext));
        cpserviceLevel1Adapter=new CpserviceLevel1Adapter(mContext,new ArrayList<ServiceTypeLevel1Vo>());
        recycleView.setAdapter(cpserviceLevel1Adapter);
        loadData();
    }
    void loadData(){
        String sysCode="111";
        String timeStamp=String.valueOf(System.currentTimeMillis()/1000);
        String param="";
        String sign= SystemUtil.getSign(sysCode,timeStamp,param);


        Subscription rxSubscription = RetrofitHelper.getCpMgrApiService()
                .getFirstLevel(sysCode,timeStamp,param,sign)
                .compose(new DefaultTransformer<List<ServiceTypeLevel1Vo>>())
                .subscribe(new RxSubscriber<List<ServiceTypeLevel1Vo>>(mContext) {
                    // 必须重写
                    @Override
                    public void onNext(List<ServiceTypeLevel1Vo> contentBeen) {
                        //todo save img,name etc user's information
                        cpserviceLevel1Adapter.clear();
                        cpserviceLevel1Adapter.addAll(contentBeen);
                        if(contentBeen!=null){
                            viewPager.setOffscreenPageLimit(contentBeen.size());
                            List<Fragment> fragmentList = new ArrayList<>();
                            for (ServiceTypeLevel1Vo serviceTypeLevel1Vo:contentBeen) {
                                Fragment fragment = CpServiceFragment.getInstance(serviceTypeLevel1Vo.getTypeId()+"");
                                fragmentList.add(fragment);
                            }
                            contentPagerAdapter = new ContentPagerAdapter(getSupportFragmentManager(), fragmentList);
                            viewPager.setAdapter(contentPagerAdapter);
                            cpserviceLevel1Adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    cpserviceLevel1Adapter.setSelected(position);
                                    viewPager.setCurrentItem(position);
                                }
                            });
                        }
                    }


                    // 无需设置可以不用重写
                    // !!!!注意参数为ApiException 类型，要不要写在Throwable那个了
                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                    }

                    // 无需设置可以不用重写
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void onRequestStart() {
        super.onRequestStart();
        initProgressDialog();
        progressDialog.show();
    }

    @OnClick({R.id.ivBack, R.id.llBusinessService, R.id.llTaxService, R.id.llIpService, R.id.llGovermentService, R.id.llSocialInsurance})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressedSupport();
                break;
            case R.id.llBusinessService:
                unSelectAllTab();
                llBusinessService.setBackgroundColor(Color.WHITE);
                tvBusinessService.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.business_service_pressed, 0, 0);
                break;
            case R.id.llTaxService:
                unSelectAllTab();
                llTaxService.setBackgroundColor(Color.WHITE);
                tvTaxService.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tax_service_pressed, 0, 0);
                break;
            case R.id.llIpService:
                unSelectAllTab();
                llIpService.setBackgroundColor(Color.WHITE);
                tvIpService.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.intellectual_property_pressed, 0, 0);
                break;
            case R.id.llGovermentService:
                unSelectAllTab();
                llGovermentService.setBackgroundColor(Color.WHITE);
                tvGovermentService.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.goverment_service_pressed, 0, 0);
                break;
            case R.id.llSocialInsurance:
                unSelectAllTab();
                llSocialInsurance.setBackgroundColor(Color.WHITE);
                tvSocialInsurance.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.social_insurance_pressed, 0, 0);
                break;
        }
    }

    void unSelectAllTab() {
        int color = ContextCompat.getColor(mContext, R.color.gray_bg);
        llBusinessService.setBackgroundColor(color);
        llGovermentService.setBackgroundColor(color);
        llIpService.setBackgroundColor(color);
        llSocialInsurance.setBackgroundColor(color);
        llTaxService.setBackgroundColor(color);

        tvBusinessService.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.business_service, 0, 0);
        tvGovermentService.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.goverment_service, 0, 0);
        tvIpService.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.intellectual_property, 0, 0);
        tvSocialInsurance.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.social_insurance, 0, 0);
        tvTaxService.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tax_service, 0, 0);
    }
}

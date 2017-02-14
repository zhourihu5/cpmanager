package com.yiqiao.cpmanager.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allenliu.badgeview.BadgeFactory;
import com.allenliu.badgeview.BadgeView;
import com.blankj.utilcode.utils.AppUtils;
import com.blankj.utilcode.utils.BarUtils;
import com.blankj.utilcode.utils.SPUtils;
import com.blankj.utilcode.utils.SizeUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.google.gson.Gson;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.app.App;
import com.yiqiao.cpmanager.app.Constants;
import com.yiqiao.cpmanager.base.BaseFragment;
import com.yiqiao.cpmanager.component.ImageLoader;
import com.yiqiao.cpmanager.entity.HttpResult;
import com.yiqiao.cpmanager.entity.LuckyMoneyRequestVo;
import com.yiqiao.cpmanager.entity.LuckyMoneyVo;
import com.yiqiao.cpmanager.entity.MineIndexRequestVo;
import com.yiqiao.cpmanager.entity.UserIndexInfo;
import com.yiqiao.cpmanager.http.RetrofitHelper;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.transformer.PageTransformer;
import com.yiqiao.cpmanager.ui.activity.AboutActivity;
import com.yiqiao.cpmanager.ui.activity.FeedBackActivity;
import com.yiqiao.cpmanager.ui.activity.MyChargeBackActivity;
import com.yiqiao.cpmanager.ui.activity.MyCouponActivity;
import com.yiqiao.cpmanager.ui.activity.MyCreditActivity;
import com.yiqiao.cpmanager.ui.activity.MyInvoiceActivity;
import com.yiqiao.cpmanager.ui.activity.MyLuckyMoneyActivity;
import com.yiqiao.cpmanager.ui.activity.MyOrderActivity;
import com.yiqiao.cpmanager.ui.activity.NoticeCenterActivity;
import com.yiqiao.cpmanager.ui.activity.ProfileActivity;
import com.yiqiao.cpmanager.ui.activity.SettingActivity;
import com.yiqiao.cpmanager.ui.activity.TaskCenterActivity;
import com.yiqiao.cpmanager.util.LogUtils;
import com.yiqiao.cpmanager.util.SharedPreferenceUtil;
import com.yiqiao.cpmanager.util.SystemUtil;
import com.yiqiao.cpmanager.widget.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;

/**
 * Created by codeest on 2016/8/11.
 */
public class MineFragment extends BaseFragment {


    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ivHead)
    CircleImageView ivHead;
    @BindView(R.id.tvUname)
    TextView tvUname;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tvMylucky)
    TextView tvMylucky;
    @BindView(R.id.llRedLucky)
    LinearLayout llRedLucky;
    @BindView(R.id.tvCoupon)
    TextView tvCoupon;
    @BindView(R.id.llCoupon)
    LinearLayout llCoupon;
    @BindView(R.id.tvCredit)
    TextView tvCredit;
    @BindView(R.id.llCredit)
    LinearLayout llCredit;
    @BindView(R.id.llMyOrder)
    LinearLayout llMyOrder;
    @BindView(R.id.llMyChargeBack)
    LinearLayout llMyChargeBack;
    @BindView(R.id.llTaskCenter)
    LinearLayout llTaskCenter;
    @BindView(R.id.tvHotline)
    TextView tvHotline;
    @BindView(R.id.llHotline)
    LinearLayout llHotline;
    @BindView(R.id.llFeedback)
    LinearLayout llFeedback;
    @BindView(R.id.llRate)
    LinearLayout llRate;
    @BindView(R.id.llAboutUs)
    LinearLayout llAboutUs;
    @BindView(R.id.tvOrderNum)
    TextView tvOrderNum;
    private BadgeView bdInvoice;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_mine;
    }

    @Override
    protected void initEventAndData() {

        ivBack.setImageResource(R.drawable.my_set);
        tvTitle.setText("我的");
        tvTitle.setTextColor(Color.WHITE);
        tvRight.setCompoundDrawablesWithIntrinsicBounds(R.drawable.my_news, 0, 0, 0);
        ViewGroup.LayoutParams layoutParams = tvRight.getLayoutParams();
        layoutParams.width = SizeUtils.dp2px(mActivity, 21);
        layoutParams.height = SizeUtils.dp2px(mActivity, 21);
        tvRight.setLayoutParams(layoutParams);
        BarUtils.setTransparentForImageView(mActivity, toolbar);
        String phone=SharedPreferenceUtil.getAppSp().getString(Constants.USER_PHONE);
        tvUname.setText(phone);
        tvOrderNum.setVisibility(View.GONE);//TODO 这个版本不显示这个
//        BadgeFactory.create(mActivity)
//                .setTextColor(Color.WHITE)
//                .setWidthAndHeight(15, 15)
//                .setBadgeBackground(Color.RED)
//                .setTextSize(10)
//                .setBadgeGravity(Gravity.RIGHT | Gravity.TOP)
//                .setBadgeCount(20)
//                .setShape(BadgeView.SHAPE_CIRCLE)
//                .setMargin(0, 7, 7, 0)//必须指定宽高
//                .bind(ivMyOrder);
//        bdInvoice = BadgeFactory.create(mActivity)
//                .setTextColor(Color.WHITE)
//                .setWidthAndHeight(15, 15)
//                .setBadgeBackground(Color.RED)
//                .setTextSize(10)
//                .setBadgeGravity(Gravity.RIGHT | Gravity.TOP)
//                .setBadgeCount(20)
//                .setShape(BadgeView.SHAPE_CIRCLE)
//                .setMargin(0, 7, 7, 0)//必须指定宽高
//                .bind(ivInvoice);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.llMyOrder, R.id.llMyChargeBack
//            , R.id.llMyInvoice
            , R.id.llRedLucky, R.id.llCoupon, R.id.llCredit, R.id.llTaskCenter, R.id.llHotline, R.id.llFeedback, R.id.llRate, R.id.llAboutUs
            , R.id.ivHead, R.id.tvUname
//            , R.id.ivInvoice
            , R.id.ivBack
            , R.id.tvRight
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llMyOrder:
                toActivity(MyOrderActivity.class);
                break;
            case R.id.llMyChargeBack:
                toActivity(MyChargeBackActivity.class);
                break;
            case R.id.llMyInvoice:
                toActivity(MyInvoiceActivity.class);
                break;
            case R.id.llRedLucky:
                toActivity(MyLuckyMoneyActivity.class);
                break;
            case R.id.llCoupon:
                toActivity(MyCouponActivity.class);
                break;
            case R.id.llCredit:
                toActivity(MyCreditActivity.class);
                break;
            case R.id.llTaskCenter:
                toActivity(TaskCenterActivity.class);
                break;
            case R.id.llHotline://拨号界面
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+telphone));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.llFeedback:
                toActivity(FeedBackActivity.class);
                break;
            case R.id.llRate://todo 去市场评价
                String mAddress = "market://details?id=" + AppUtils.getAppPackageName(App.getInstance());
                Intent marketIntent = new Intent("android.intent.action.VIEW");
                marketIntent.setData(Uri.parse(mAddress));
                startActivity(marketIntent);
                break;
            case R.id.llAboutUs://todo 关于我们
                toActivity(AboutActivity.class);
                break;
            case R.id.ivHead:
            case R.id.tvUname:
                toActivity(ProfileActivity.class);
                break;
            case R.id.ivBack:
                toActivity(SettingActivity.class);
                break;
            case R.id.tvRight:
                toActivity(NoticeCenterActivity.class);
                break;
            case R.id.ivInvoice:
                if (bdInvoice != null) {
                    bdInvoice.unbind();//需要修改为移除后恢复到没有badgeview时的状态
                }
                break;
        }
    }
    @Override
    protected void lazyLoadData() {
        super.lazyLoadData();
//        adapter.resumeMore();
        String phone=SharedPreferenceUtil.getAppSp().getString(Constants.USER_PHONE);
        tvUname.setText(phone);
        loadData();
    }
    String telphone="4006865658";
    private void loadData() {
        LogUtils.e("loadData");
        String customId= SharedPreferenceUtil.getAppSp().getInt(Constants.USER_ID)+"";
//        String commentState="0";//"commentState": "是否评价"  1、是 0 、否
//        customId="54142";//todo test
        MineIndexRequestVo orderListRequestVo=new MineIndexRequestVo();
        orderListRequestVo.setUserId(customId);
        String sysCode="111";
        String timeStamp=String.valueOf(System.currentTimeMillis());
        String param=new Gson().toJson(orderListRequestVo);
        String sign= SystemUtil.getSign(sysCode,timeStamp,param);

        Subscription rxSubscription = RetrofitHelper.getCpMgrApiService()
                .getUserIndexInfo(sysCode,timeStamp,param,sign)
                .compose(new PageTransformer<HttpResult<UserIndexInfo>>())
                .subscribe(new RxSubscriber<HttpResult<UserIndexInfo>>(MineFragment.this) {
                    // 必须重写
                    @Override
                    public void onNext(HttpResult<UserIndexInfo> contentBeen) {
                        UserIndexInfo userIndexInfo=contentBeen.getData();
                        LogUtils.e("userIndexInfo=="+userIndexInfo);
                        if(userIndexInfo!=null){
                            tvMylucky.setText(String.format("%s元红包",userIndexInfo.getRedPackageToal()));
                            tvCoupon.setText(String.format("%s张优惠券",userIndexInfo.getCouponTotall()));
                            ImageLoader.load(mActivity,userIndexInfo.getUserPicUrl(),ivHead,R.drawable.img_head);
                            SPUtils spUtils= SharedPreferenceUtil.getAppSp();
                            spUtils.putString(Constants.USER_IMG,userIndexInfo.getUserPicUrl());
                            telphone=userIndexInfo.getTelphone();
                            if (StringUtils.isEmpty(telphone)) {
                                telphone="4006865658";
                            }else {
                                tvHotline.setText(telphone);
                            }

                            try {
                                int msgTotal=Integer.valueOf(userIndexInfo.getMessageTotal());
                                if(msgTotal>0){
                                    BadgeFactory.create(mActivity)
                                            .setTextColor(Color.WHITE)
                                            .setWidthAndHeight(15, 15)
                                            .setBadgeBackground(Color.RED)
                                            .setTextSize(10)
                                            .setBadgeGravity(Gravity.RIGHT | Gravity.TOP)
                                            .setBadgeCount(msgTotal)
                                            .setShape(BadgeView.SHAPE_CIRCLE)
                                            .setMargin(0, 7, 7, 0)//必须指定宽高
                                            .bind(tvRight);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }


                    // 无需设置可以不用重写
                    // !!!!注意参数为ApiException 类型，要不要写在Throwable那个了
                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        isFirst=true;
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
}

package com.yiqiao.cpmanager.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.blankj.utilcode.utils.SizeUtils;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.app.App;
import com.yiqiao.cpmanager.base.BaseFragment;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.http.RetrofitHelper;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.transformer.DefaultTransformer;
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

import java.util.List;

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
    @BindView(R.id.ivHead)
    ImageView ivHead;
    @BindView(R.id.tvUname)
    TextView tvUname;
    @BindView(R.id.llMyOrder)
    LinearLayout llMyOrder;
    @BindView(R.id.llMyChargeBack)
    LinearLayout llMyChargeBack;
    @BindView(R.id.llMyInvoice)
    LinearLayout llMyInvoice;
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
    @BindView(R.id.ivMyOrder)
    ImageView ivMyOrder;
    @BindView(R.id.ivInvoice)
    ImageView ivInvoice;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.imageView2)
    ImageView imageView2;
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
        layoutParams.width = SizeUtils.dp2px(mActivity, 21 );
        layoutParams.height = SizeUtils.dp2px(mActivity, 21 );
        tvRight.setLayoutParams(layoutParams);
        BarUtils.setTransparentForImageView(mActivity,toolbar);

        BadgeFactory.create(mActivity)
                .setTextColor(Color.WHITE)
                .setWidthAndHeight(15, 15)
                .setBadgeBackground(Color.RED)
                .setTextSize(10)
                .setBadgeGravity(Gravity.RIGHT | Gravity.TOP)
                .setBadgeCount(20)
                .setShape(BadgeView.SHAPE_CIRCLE)
                .setMargin(0, 7, 7, 0)//必须指定宽高
                .bind(tvRight);

        BadgeFactory.create(mActivity)
                .setTextColor(Color.WHITE)
                .setWidthAndHeight(15, 15)
                .setBadgeBackground(Color.RED)
                .setTextSize(10)
                .setBadgeGravity(Gravity.RIGHT | Gravity.TOP)
                .setBadgeCount(20)
                .setShape(BadgeView.SHAPE_CIRCLE)
                .setMargin(0, 7, 7, 0)//必须指定宽高
                .bind(ivMyOrder);
        bdInvoice = BadgeFactory.create(mActivity)
                .setTextColor(Color.WHITE)
                .setWidthAndHeight(15, 15)
                .setBadgeBackground(Color.RED)
                .setTextSize(10)
                .setBadgeGravity(Gravity.RIGHT | Gravity.TOP)
                .setBadgeCount(20)
                .setShape(BadgeView.SHAPE_CIRCLE)
                .setMargin(0, 7, 7, 0)//必须指定宽高
                .bind(ivInvoice);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.llMyOrder, R.id.llMyChargeBack, R.id.llMyInvoice, R.id.llRedLucky, R.id.llCoupon, R.id.llCredit, R.id.llTaskCenter, R.id.llHotline, R.id.llFeedback, R.id.llRate, R.id.llAboutUs
            , R.id.ivHead, R.id.tvUname,R.id.ivInvoice
            ,R.id.ivBack
            ,R.id.tvRight
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
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:4006865686"));
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
                if(bdInvoice!=null){
                    bdInvoice.unbind();//需要修改为移除后恢复到没有badgeview时的状态
                }
                break;
        }
    }

}

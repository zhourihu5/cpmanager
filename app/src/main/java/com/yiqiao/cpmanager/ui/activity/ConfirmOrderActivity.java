package com.yiqiao.cpmanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.StringUtils;
import com.google.gson.Gson;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.app.Constants;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.entity.CouponInfoRequestVo;
import com.yiqiao.cpmanager.entity.CouponInfoVo;
import com.yiqiao.cpmanager.entity.CouponVo;
import com.yiqiao.cpmanager.entity.OrderDetailRequestVo;
import com.yiqiao.cpmanager.entity.OrderVo;
import com.yiqiao.cpmanager.entity.StoreVo;
import com.yiqiao.cpmanager.http.RetrofitHelper;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.transformer.DefaultTransformer;
import com.yiqiao.cpmanager.ui.adapter.ConfirmOrderItemSetmealAdapter;
import com.yiqiao.cpmanager.ui.adapter.MyOrderItemAdapter;
import com.yiqiao.cpmanager.util.SharedPreferenceUtil;
import com.yiqiao.cpmanager.util.SystemUtil;
import com.yiqiao.cpmanager.widget.FullyHeightRecycleview;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ezy.ui.layout.LoadingLayout;
import rx.Subscription;

/**
 * Created by Xu on 2016/11/23.
 * 确认订单
 */

public class ConfirmOrderActivity extends BaseActivity {


    private static final int REQUEST_USE_COUPON = 1;
    private static final int REQUEST_STORE_CHOOSE = 2;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvUnit)
    TextView tvUnit;
    @BindView(R.id.tvLuckyDown)
    TextView tvLuckyDown;
    @BindView(R.id.tvCoupon)
    TextView tvCoupon;
    @BindView(R.id.tvCredit)
    TextView tvCredit;
    @BindView(R.id.rvDonate)
    FullyHeightRecycleview rvDonate;
    @BindView(R.id.rvSetmeal)
    FullyHeightRecycleview rvSetmeal;
    @BindView(R.id.llSetmeal)
    LinearLayout llSetmeal;
    @BindView(R.id.tvCity)
    TextView tvCity;
    @BindView(R.id.llCity)
    LinearLayout llCity;
    @BindView(R.id.tvStore)
    TextView tvStore;
    @BindView(R.id.llStore)
    LinearLayout llStore;
    @BindView(R.id.tvCouponUsed)
    TextView tvCouponUsed;
    @BindView(R.id.llCoupon)
    LinearLayout llCoupon;
    @BindView(R.id.tvLuckyMoneyUsed)
    TextView tvLuckyMoneyUsed;
    @BindView(R.id.llRedLucky)
    LinearLayout llRedLucky;
    @BindView(R.id.ivSelectedWechatPay)
    ImageView ivSelectedWechatPay;
    @BindView(R.id.llWechatPay)
    LinearLayout llWechatPay;
    @BindView(R.id.ivSelectedAliPay)
    ImageView ivSelectedAliPay;
    @BindView(R.id.llAlipay)
    LinearLayout llAlipay;
    @BindView(R.id.llPayByAccount)
    LinearLayout llPayByAccount;
    @BindView(R.id.etRemark)
    EditText etRemark;
    @BindView(R.id.tvPay)
    TextView tvPay;
    @BindView(R.id.llBottom)
    LinearLayout llBottom;
    @BindView(R.id.loading)
    LoadingLayout loading;
    @BindView(R.id.tvLuckyMoney)
    TextView tvLuckyMoney;
    @BindView(R.id.tvTotalNum)
    TextView tvTotalNum;
    private IWXAPI api;

    String orderNo;
    OrderVo orderVo;
    String  skuId;
    @Override
    protected int getLayout() {
        return R.layout.activity_confirm_order;
    }

    @Override
    protected void initEventAndData() {
        tvTitle.setText("确认订单");
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        orderNo = getIntent().getStringExtra(OrderDetailActivity.ORDER_NO);
        orderVo = (OrderVo) getIntent().getSerializableExtra(OrderVo.class.getCanonicalName());
        if (orderVo == null) {
            getOrder();
        } else {
            setData(orderVo);
        }
    }

    private void getOrder() {
//        String customId = SharedPreferenceUtil.getAppSp().getInt(Constants.USER_ID) + "";
        OrderDetailRequestVo orderListRequestVo = new OrderDetailRequestVo();
//        orderListRequestVo.setOrderId(orderId);
        orderListRequestVo.setOrderCd(orderNo);
        String sysCode = "111";
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String param = new Gson().toJson(orderListRequestVo);
        String sign = SystemUtil.getSign(sysCode, timeStamp, param);

        Subscription rxSubscription =
                RetrofitHelper
//                        .getXtApiService()//TODO 不要出现这样的错误
                        .getCpMgrApiService()//TODO 不要出现这样的错误
                        .getOrderInfoById(sysCode, timeStamp, param, sign)
                        .compose(new DefaultTransformer<OrderVo>())
                        .subscribe(new RxSubscriber<OrderVo>(mContext) {
                                       // 必须重写
                                       @Override
                                       public void onNext(OrderVo data) {
                                           orderVo=data;
                                           setData(data);

                                       }

                                       // 无需设置可以不用重写
                                       // !!!!注意参数为ApiException 类型，要不要写在Throwable那个了
                                       @Override
                                       protected void onError(ApiException ex) {
                                           super.onError(ex);
                                           loading.showError();
                                       }

                                       // 无需设置可以不用重写
                                       @Override
                                       public void onCompleted() {
                                           super.onCompleted();
                                       }
                                   }

                        );

        addSubscrebe(rxSubscription);
    }
    private void getCouponInfo() {// 获取
        String customId = SharedPreferenceUtil.getAppSp().getInt(Constants.USER_ID) + "";
        CouponInfoRequestVo orderListRequestVo = new CouponInfoRequestVo();
        orderListRequestVo.setCustomerId(customId);
        orderListRequestVo.setSkuId(skuId);
        String sysCode = "111";
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String param = new Gson().toJson(orderListRequestVo);
        String sign = SystemUtil.getSign(sysCode, timeStamp, param);

        Subscription rxSubscription =
                RetrofitHelper
                        .getCpMgrApiService()
                        .getCouponBySku(sysCode, timeStamp, param, sign)
                        .compose(new DefaultTransformer<CouponInfoVo>())
                        .subscribe(new RxSubscriber<CouponInfoVo>(mContext) {
                                       // 必须重写
                                       @Override
                                       public void onNext(CouponInfoVo data) {
                                            //todo
                                       }

                                       // 无需设置可以不用重写
                                       // !!!!注意参数为ApiException 类型，要不要写在Throwable那个了
                                       @Override
                                       protected void onError(ApiException ex) {
                                           super.onError(ex);
                                           loading.showError();
                                       }

                                       // 无需设置可以不用重写
                                       @Override
                                       public void onCompleted() {
                                           super.onCompleted();
                                       }
                                   }

                        );

        addSubscrebe(rxSubscription);
    }

    private void setData(OrderVo data) {
        loading.showContent();
        rvDonate.setLayoutManager(new LinearLayoutManager(mContext));
        rvSetmeal.setLayoutManager(new LinearLayoutManager(mContext));

        RecyclerView.Adapter myOrderItemAdapter = null;

        List<OrderVo.OrderListBean> orderList = data.getOrderList();
        if (orderList != null && orderList.size() > 0) {
            OrderVo.OrderListBean orderListBean = orderList.get(0);
            ArrayList<OrderVo.OrderListBean> childOrderList = new ArrayList<>();
            for (OrderVo.OrderListBean bean : orderList) {
                if (bean.getOrdersNo().startsWith("Z")) {//主订单
                    orderListBean = bean;
                } else {
                    childOrderList.add(bean);
                }
            }
            if (childOrderList.size() == orderList.size()) {//没有主订单
                if (orderListBean.getServiceList() != null) {
                    myOrderItemAdapter = new MyOrderItemAdapter(mContext, orderListBean.getServiceList());
                }
                rvDonate.setAdapter(myOrderItemAdapter);
                llSetmeal.setVisibility(View.GONE);
            } else {//套餐类型
                myOrderItemAdapter = new ConfirmOrderItemSetmealAdapter(mContext, childOrderList);
                rvSetmeal.setAdapter(myOrderItemAdapter);
            }
//            tvOrderNum.setText(String.format("订单号：%s",orderListBean.getOrdersNo()));
//            tvDate.setText(DateUtil.formartTimeStamp(orderListBean.getCreateTime()));
            tvName.setText(orderListBean.getProductName());
            tvPrice.setText(String.valueOf(orderListBean.getProductPrice()));
            if (!StringUtils.isEmpty(orderListBean.getProductUnit())) {
                tvUnit.setText(orderListBean.getProductUnit());
            } else {
                tvUnit.setText("元");
            }
            tvNum.setText(String.format("x%s", orderListBean.getNum()));
//            tvStore.setText(String.format("服务网点：%s", orderListBean.getDeptName()));
//            tvDepAddress.setText(String.format("地  址：%s", orderListBean.getDeptAddress()));
//            tvLinkman.setText(String.format("联系人：%s", orderListBean.getDeptManager()));
//            tvPhone.setText(String.format("电  话：%s", orderListBean.getDeptPhone()));
//            tvTotalNum.setText(String.valueOf(orderListBean.getTotalAmount()));
//            if (StringUtils.isEmpty(orderListBean.getProductType())) {
//                tvProductType.setVisibility(View.GONE);
//            } else {
//                tvProductType.setVisibility(View.VISIBLE);
//                switch (orderListBean.getProductType()) {// 商品类型(0普通商品,1买赠商品,2套餐)
//                    case "1"://比较的是hashcode
//                        tvProductType.setText("赠送服务");
//                        break;
//                    case "2":
//                        tvProductType.setText("套餐内容");
//                        break;
//                    default:
//                        tvProductType.setText("服务产品");
//                        break;
//                }
//            }
            try {
                if (!StringUtils.isEmpty(orderListBean.getActivityAmount())&&Float.valueOf(orderListBean.getActivityAmount()) > 0) {
                    tvLuckyDown.setText(String.format("使用红包可省：%s元", orderListBean.getActivityAmount()));
                    tvLuckyDown.setVisibility(View.VISIBLE);
                } else {
                    tvLuckyDown.setText("");
                    tvLuckyDown.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                tvLuckyDown.setText(String.format("使用红包可省：%s元", orderListBean.getActivityAmount()));
                tvLuckyDown.setVisibility(View.VISIBLE);
                e.printStackTrace();
            }
            try {
                if (!StringUtils.isEmpty(orderListBean.getRedbagAmount())&&Float.valueOf(orderListBean.getRedbagAmount()) > 0) {
                    tvLuckyMoney.setText(String.format("红包：%s", orderListBean.getRedbagAmount()));
                    tvLuckyMoney.setVisibility(View.VISIBLE);
                } else {
                    tvLuckyMoney.setText("");
                    tvLuckyMoney.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                tvLuckyMoney.setText(String.format("红包：%s", orderListBean.getRedbagAmount()));
                tvLuckyMoney.setVisibility(View.VISIBLE);
                e.printStackTrace();
            }
            try {
                if (!StringUtils.isEmpty(orderListBean.getCouponAmount())&&Float.valueOf(orderListBean.getCouponAmount()) > 0) {
                    tvCoupon.setText(String.format("优惠券：%s", orderListBean.getCouponAmount()));
                    tvCoupon.setVisibility(View.VISIBLE);
                    tvCouponUsed.setText(String.format("%s元", orderListBean.getCouponAmount()));
                } else {
                    tvCoupon.setText("");
                    tvCoupon.setVisibility(View.GONE);
                    tvCouponUsed.setText("");
                }
            } catch (Exception e) {
                tvCoupon.setText(String.format("优惠券：%s", orderListBean.getCouponAmount()));
                tvCoupon.setVisibility(View.VISIBLE);
                tvCouponUsed.setVisibility(View.VISIBLE);
                e.printStackTrace();
            }
            try {
                if (!StringUtils.isEmpty(orderListBean.getIntergral())&&Float.valueOf(orderListBean.getIntergral()) > 0) {
                    tvCredit.setText(String.format("积分：%s", orderListBean.getIntergral()));
                    tvCredit.setVisibility(View.VISIBLE);
                } else {
                    tvCredit.setText("");
                    tvCredit.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                tvCredit.setText(String.format("积分：%s", orderListBean.getIntergral()));
                tvCredit.setVisibility(View.VISIBLE);
                e.printStackTrace();
            }
            tvStore.setText(orderListBean.getDeptName());
            OrderVo.OrderListBean.SnapshotBean snapshotBean=  orderListBean.getSnapshot();
            if(snapshotBean!=null){
                llCity.setVisibility(View.VISIBLE);
                if(StringUtils.isEmpty(snapshotBean.getAreaLevelOne())){
                   llCity.setVisibility(View.GONE);
                }
                if(StringUtils.isEmpty(snapshotBean.getAreaLevelTwo())){
                    tvCity.setText(String.format("%s",snapshotBean.getAreaLevelOne()));
                }
                else if(StringUtils.isEmpty(snapshotBean.getAreaLevelThree())){
                    tvCity.setText(String.format("%s-%s",snapshotBean.getAreaLevelOne(),snapshotBean.getAreaLevelTwo()));
                }
                else {
                    tvCity.setText(String.format("%s-%s-%s",snapshotBean.getAreaLevelOne(),snapshotBean.getAreaLevelTwo(),snapshotBean.getAreaLevelTwo()));
                }

            }
//            tvCity.setText();//todo which area?


            tvLuckyMoneyUsed.setText(String.format("%s元", orderListBean.getRedbagAmount()));//TODO 可用金额
            tvTotalNum.setText(orderListBean.getFinalAmount());
            skuId=orderListBean.getSkuId();

            //支付方式  1.现金-默认、2.支票、3.转账、
//            4.刷卡、5.支付宝、6.微信
            ivSelectedAliPay.setImageResource(R.drawable.choose_nor);
            ivSelectedWechatPay.setImageResource(R.drawable.choose_nor);
            if ("5".equals(orderListBean.getPayWay())) {
                ivSelectedAliPay.setImageResource(R.drawable.choose_down);
            } else if ("6".equals(orderListBean.getPayWay())) {
                ivSelectedWechatPay.setImageResource(R.drawable.choose_down);
            }
        }
        getCouponInfo();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ivBack, R.id.llCity, R.id.llCoupon, R.id.llRedLucky, R.id.llStore, R.id.llWechatPay, R.id.llAlipay, R.id.llPayByAccount, R.id.tvPay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressedSupport();
                break;
            case R.id.llCity:
                break;
            case R.id.llCoupon:
                Intent intent=new Intent(mContext,CouponChooseActivity.class);
                intent.putExtra(CouponChooseActivity.SKU_ID,skuId);
               startActivityForResult(intent,REQUEST_USE_COUPON);
                break;
            case R.id.llStore:
                intent=new Intent(mContext,StoreChooseActivity.class);
//                intent.putExtra(GoodsDetailActivity.KEY_ID,skuId);
                startActivityForResult(intent,REQUEST_STORE_CHOOSE);
                break;
            case R.id.llRedLucky:

                break;
            case R.id.llWechatPay:
                ivSelectedAliPay.setImageResource(R.drawable.choose_nor);
                ivSelectedWechatPay.setImageResource(R.drawable.choose_down);
                break;
            case R.id.llAlipay:
                ivSelectedAliPay.setImageResource(R.drawable.choose_down);
                ivSelectedWechatPay.setImageResource(R.drawable.choose_nor);
                break;
            case R.id.llPayByAccount:
                intent=new Intent(mContext,PayByAccountActivity.class);
                intent.putExtra(OrderVo.class.getCanonicalName(),orderVo);
                startActivity(intent);
                break;
            case R.id.tvPay:
                break;
        }
    }

    CouponVo.RecordListBean couponVo;
    StoreVo.PageBean.RecordListBean storeVo;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_STORE_CHOOSE:
                if(resultCode==RESULT_OK){
                    storeVo= (StoreVo.PageBean.RecordListBean) data.getSerializableExtra(StoreVo.PageBean.RecordListBean.class.getCanonicalName());
                    tvStore.setText(storeVo.getDeptName());
                }
                break;
            case REQUEST_USE_COUPON:
                if(resultCode==RESULT_OK){
                    couponVo= (CouponVo.RecordListBean) data.getSerializableExtra( CouponVo.RecordListBean.class.getCanonicalName());
                    tvCouponUsed.setText(couponVo.getAmount());
                }
                break;
        }
    }
}

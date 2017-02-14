package com.yiqiao.cpmanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.StringUtils;
import com.google.gson.Gson;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.entity.OrderDetailRequestVo;
import com.yiqiao.cpmanager.entity.OrderVo;
import com.yiqiao.cpmanager.http.RetrofitHelper;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.transformer.DefaultTransformer;
import com.yiqiao.cpmanager.ui.adapter.MyOrderItemAdapter;
import com.yiqiao.cpmanager.ui.adapter.OrderDetailSetmealAdapter;
import com.yiqiao.cpmanager.util.DateUtil;
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
 * 分为套餐订单和非套餐订单
 */

public class OrderDetailActivity extends BaseActivity {

    public static final String ORDER_ID = "orderId";
    String orderId;
    public static final String ORDER_NO = "orderNo";
    String orderNo;
    OrderVo orderVo;

    OrderDetailSetmealAdapter orderDetailSetmealAdapter;
    MyOrderItemAdapter myOrderItemAdapter;

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.tvOrderNum)
    TextView tvOrderNum;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.tvSetMealName)
    TextView tvSetMealName;
    @BindView(R.id.rvSetmeal)
    FullyHeightRecycleview rvSetmeal;
    @BindView(R.id.llSetmeal)
    LinearLayout llSetmeal;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvUnit)
    TextView tvUnit;
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.tvItemName)
    TextView tvItemName;
    @BindView(R.id.tvItemPrice)
    TextView tvItemPrice;
    @BindView(R.id.tvItemNum)
    TextView tvItemNum;
    @BindView(R.id.llItemGoodsInfo)
    LinearLayout llItemGoodsInfo;
    @BindView(R.id.tvCpType)
    TextView tvCpType;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvRegistAddr)
    TextView tvRegistAddr;
    @BindView(R.id.recycleView)
    FullyHeightRecycleview recycleView;
    @BindView(R.id.llGoodsInfo)
    LinearLayout llGoodsInfo;
    @BindView(R.id.tvTotalNum)
    TextView tvTotalNum;
    @BindView(R.id.tvCancel)
    TextView tvCancel;
    @BindView(R.id.tvPay)
    TextView tvPay;
    @BindView(R.id.tvChargeBack)
    TextView tvChargeBack;
    @BindView(R.id.tvRepay)
    TextView tvRepay;
    @BindView(R.id.llBottom)
    LinearLayout llBottom;
    @BindView(R.id.loading)
    LoadingLayout loading;
    @BindView(R.id.tvProductType)
    TextView tvProductType;
    @BindView(R.id.tvStore)
    TextView tvStore;
    @BindView(R.id.tvLinkman)
    TextView tvLinkman;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvDepAddress)
    TextView tvDepAddress;
    @BindView(R.id.tvLuckyDown)
    TextView tvLuckyDown;
    @BindView(R.id.tvCoupon)
    TextView tvCoupon;
    @BindView(R.id.tvLuckyMoney)
    TextView tvLuckyMoney;
    @BindView(R.id.tvPriceOld)
    TextView tvPriceOld;
    @BindView(R.id.tvPreferentialAmount)
    TextView tvPreferentialAmount;

    String skuId;
    String type="1";//1 商品 2套餐
    @Override
    protected int getLayout() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
        tvTitle.setText("订单详情");
    }

    @Override
    protected void initEventAndData() {
        orderId = getIntent().getStringExtra(ORDER_ID);
        orderNo = getIntent().getStringExtra(ORDER_NO);
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
        orderListRequestVo.setOrderId(orderId);
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

    private void setData(OrderVo data) {
        loading.showContent();
        recycleView.setLayoutManager(new LinearLayoutManager(mContext));
        rvSetmeal.setLayoutManager(new LinearLayoutManager(mContext));

        RecyclerView.Adapter myOrderItemAdapter = null;

        List<OrderVo.OrderListBean> orderList = data.getOrderList();
        if (orderList != null && orderList.size() > 0) {
            OrderVo.OrderListBean orderListBean = orderList.get(0);
            ArrayList<OrderVo.OrderListBean> childOrderList = new ArrayList<>();
            for (OrderVo.OrderListBean bean : orderList) {
                if (bean.getOrdersNo().startsWith("Z")) {//主订单
                    orderListBean = bean;
                    type="2";
                } else {
                    childOrderList.add(bean);
                }
            }
            if (childOrderList.size() == orderList.size()) {//没有主订单
                if (orderListBean.getServiceList() != null) {
                    myOrderItemAdapter = new MyOrderItemAdapter(mContext, orderListBean.getServiceList());
                }
                recycleView.setAdapter(myOrderItemAdapter);
                llSetmeal.setVisibility(View.GONE);
                if (StringUtils.isEmpty(orderListBean.getProductType())) {
                    tvProductType.setVisibility(View.GONE);
                } else {
                    tvProductType.setVisibility(View.VISIBLE);
                    switch (orderListBean.getProductType()) {// 商品类型(0普通商品,1买赠商品,2套餐)
                        case "1"://比较的是hashcode
                            tvProductType.setText("赠送服务");
                            break;
                        case "2":
                            tvProductType.setText("套餐内容");
                            break;
                        default:
                            tvProductType.setText("服务产品");
                            break;
                    }
                }
            } else {//套餐类型
                myOrderItemAdapter = new OrderDetailSetmealAdapter(mContext, childOrderList);
                rvSetmeal.setAdapter(myOrderItemAdapter);
                llGoodsInfo.setVisibility(View.GONE);
            }
            tvOrderNum.setText(String.format("订单号：%s", orderListBean.getOrdersNo()));
            tvDate.setText(DateUtil.formartTimeStamp(orderListBean.getCreateTime()));
            tvName.setText(orderListBean.getProductName());
            tvPrice.setText(String.valueOf(orderListBean.getProductPrice()));
            if (!StringUtils.isEmpty(orderListBean.getProductUnit())) {
                tvUnit.setText(orderListBean.getProductUnit());
            } else {
                tvUnit.setText("元");
            }
            tvNum.setText(String.format("x%s", orderListBean.getNum()));
            tvStore.setText(String.format("服务网点：%s", orderListBean.getDeptName()));
            tvDepAddress.setText(String.format("地  址：%s", orderListBean.getDeptAddress()));
            tvLinkman.setText(String.format("联系人：%s", orderListBean.getDeptManager()));
            tvPhone.setText(String.format("电  话：%s", orderListBean.getDeptPhone()));
            tvTotalNum.setText(String.valueOf(orderListBean.getTotalAmount()));

            //  //订单状态  1待提交、2待收款、3已部分收款、
//                4已完成(包含已全部收款)、5已取消、
//                7申请退单、8、已退款
            tvPay.setVisibility(View.GONE);
            tvCancel.setVisibility(View.GONE);
            tvRepay.setVisibility(View.GONE);
            tvChargeBack.setVisibility(View.GONE);
            try {
                if (!StringUtils.isEmpty(orderListBean.getActivityAmount())&&Float.valueOf(orderListBean.getActivityAmount()) > 0) {
                    tvLuckyDown.setText(String.format("满减：%s", orderListBean.getActivityAmount()));
                    tvLuckyDown.setVisibility(View.VISIBLE);
                } else {
                    tvLuckyDown.setText("");
                    tvLuckyDown.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                tvLuckyDown.setText(String.format("满减：%s", orderListBean.getActivityAmount()));
                tvLuckyDown.setVisibility(View.VISIBLE);
                e.printStackTrace();
            }
            try {
                if (!StringUtils.isEmpty(orderListBean.getCouponAmount())&&Float.valueOf(orderListBean.getCouponAmount()) > 0) {
                    tvCoupon.setText(String.format("优惠券：%s", orderListBean.getCouponAmount()));
                    tvCoupon.setVisibility(View.VISIBLE);
                } else {
                    tvCoupon.setText("");
                    tvCoupon.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                tvCoupon.setText(String.format("优惠券：%s", orderListBean.getCouponAmount()));
                tvCoupon.setVisibility(View.VISIBLE);
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
                if (!StringUtils.isEmpty(orderListBean.getPreferentialAmount())&&Float.valueOf(orderListBean.getPreferentialAmount()) > 0) {
                    tvPreferentialAmount.setText(String.format("节省：%s", orderListBean.getPreferentialAmount()));
                    tvPreferentialAmount.setVisibility(View.VISIBLE);
//                    tvPriceOld.setText(String.format("原价：%s", orderListBean.getTotalAmount()));//?todo which
//                    tvPriceOld.setVisibility(View.VISIBLE);
                } else {
                    tvPreferentialAmount.setText("");
                    tvPreferentialAmount.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                tvPreferentialAmount.setText(String.format("节省：%s", orderListBean.getPreferentialAmount()));
                tvPreferentialAmount.setVisibility(View.VISIBLE);

                e.printStackTrace();
            }
            skuId=orderListBean.getSkuId();

            if ("2".equals(orderListBean.getOrdersState())) {//todo
                tvPay.setVisibility(View.VISIBLE);
            } else if ("4".equals(orderListBean.getOrdersState())) {//todo
                tvChargeBack.setVisibility(View.VISIBLE);
            } else if ("5".equals(orderListBean.getOrdersState())) {//todo
                tvRepay.setVisibility(View.VISIBLE);
            } else if ("1".equals(orderListBean.getOrdersState())) {
                tvPay.setVisibility(View.VISIBLE);
            } else if ("3".equals(orderListBean.getOrdersState())) {

            } else if ("7".equals(orderListBean.getOrdersState())) {
                tvRepay.setVisibility(View.GONE);
            } else if ("8".equals(orderListBean.getOrdersState())) {
                tvRepay.setVisibility(View.GONE);
            }
        }
    }

    @OnClick({R.id.ivBack, R.id.tvCancel, R.id.tvPay, R.id.tvChargeBack, R.id.tvRepay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressedSupport();
                break;
            case R.id.tvCancel:

                break;
            case R.id.tvRepay:
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                intent.putExtra(GoodsDetailActivity.KEY_ID, skuId);
                intent.putExtra(GoodsDetailActivity.KEY_TYPE, type);//1,商品，2，套餐
                startActivity(intent);
                break;
            case R.id.tvPay:
                intent = getIntent();
                intent.setClass(mContext, ConfirmOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.tvChargeBack://todo 申请退款

                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}


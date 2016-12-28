package com.yiqiao.cpmanager.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.app.Constants;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.entity.OrderDetailRequestVo;
import com.yiqiao.cpmanager.entity.OrderDetailVo;
import com.yiqiao.cpmanager.http.RetrofitHelper;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.transformer.DefaultTransformer;
import com.yiqiao.cpmanager.util.DateUtil;
import com.yiqiao.cpmanager.util.SharedPreferenceUtil;
import com.yiqiao.cpmanager.util.SystemUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import ezy.ui.layout.LoadingLayout;
import rx.Subscription;

/**
 * Created by Xu on 2016/11/23.
 * 分为套餐订单和非套餐订单
 */

public class OrderDetailActivity extends BaseActivity {

    public static final String ORDER_ID = "orderId";
    int orderId;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
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
    @BindView(R.id.tvOrderNum)
    TextView tvOrderNum;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvUnit)
    TextView tvUnit;
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.tvCpType)
    TextView tvCpType;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvRegistAddr)
    TextView tvRegistAddr;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.tvTotalNum)
    TextView tvTotalNum;

    @Override
    protected int getLayout() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void initEventAndData() {
        orderId = getIntent().getIntExtra(ORDER_ID, 0);
        getOrder();
    }

    private void getOrder() {
        String customId = SharedPreferenceUtil.getAppSp().getInt(Constants.USER_ID) + "";
        String commentState = "0";//"commentState": "是否评价"  1、是 0 、否
        OrderDetailRequestVo orderListRequestVo = new OrderDetailRequestVo(orderId + "");
        String sysCode = "111";
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String param = new Gson().toJson(orderListRequestVo);
        String sign = SystemUtil.getSign(sysCode, timeStamp, param);

        Subscription rxSubscription = RetrofitHelper.getXtApiService()//TODO 不要出现这样的错误
                .getOrderInfoById(sysCode, timeStamp, param, sign)
                .compose(new DefaultTransformer<OrderDetailVo>())
                .subscribe(new RxSubscriber<OrderDetailVo>(mContext) {
                    // 必须重写
                    @Override
                    public void onNext(OrderDetailVo orderDetailVo) {
                        loading.showContent();
                        OrderDetailVo.OrderVoBean contentBeen = orderDetailVo.getOrderVo();
                        tvOrderNum.setText(String.format("订单号：%s", contentBeen.getOrdersNo()));
                        tvDate.setText(DateUtil.formartTimeStamp(contentBeen.getCreateTime()));
                        tvName.setText(contentBeen.getProductName());
                        tvPrice.setText(String.valueOf(contentBeen.getProductPrice()));
                        tvNum.setText(String.format("x%s", contentBeen.getNum()));
//                        tvCpType.setText(String.format("公司类型：%s",contentBeen.get));//TODO 对应哪个字段？？
                        tvAddress.setText(String.format("地  址：%s", contentBeen.getAddress()));//TODO 对应哪个字段？？
                        tvTotalNum.setText(String.format("%s",contentBeen.getTotalAmount()));
                        //TODO 对应哪个字段？？
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
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

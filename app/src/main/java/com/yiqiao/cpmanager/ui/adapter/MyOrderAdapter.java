package com.yiqiao.cpmanager.ui.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.StringUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.entity.CancleReasonVo;
import com.yiqiao.cpmanager.entity.OrderVo;
import com.yiqiao.cpmanager.ui.activity.ConfirmOrderActivity;
import com.yiqiao.cpmanager.ui.activity.GoodsDetailActivity;
import com.yiqiao.cpmanager.ui.activity.OrderDetailActivity;
import com.yiqiao.cpmanager.widget.FullyHeightRecycleview;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description: 推荐
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class MyOrderAdapter extends RecyclerArrayAdapter<OrderVo> {

    private int type;
    public static final int TYPE_UNPAID = 0;
    public static final int TYPE_PAID = 1;
    public static final int TYPE_CANCELED = 2;
    CancelOrderListner cancelOrderListner;

    public void setCancelOrderListner(CancelOrderListner cancelOrderListner) {
        this.cancelOrderListner = cancelOrderListner;
    }

    public void setType(int type) {
        this.type = type;
    }

    public MyOrderAdapter(Context context, List<OrderVo> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<OrderVo> {


        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.tvUnit)
        TextView tvUnit;
        @BindView(R.id.tvNum)
        TextView tvNum;
        @BindView(R.id.recycleView)
        FullyHeightRecycleview recycleView;
        @BindView(R.id.tvStore)
        TextView tvStore;
        @BindView(R.id.tvTotalNum)
        TextView tvTotalNum;
        @BindView(R.id.llPriceLeft)
        LinearLayout llPriceLeft;
        @BindView(R.id.btCancel)
        TextView btCancel;
        @BindView(R.id.btPay)
        TextView btPay;
        @BindView(R.id.btRepay)
        TextView btRepay;
        @BindView(R.id.llPriceRight)
        LinearLayout llPriceRight;
        @BindView(R.id.tvProductType)
        TextView tvProductType;

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_order);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(final OrderVo data) {
            switch (type) {
                case MyOrderAdapter.TYPE_UNPAID:
                    llPriceRight.setVisibility(View.GONE);
                    btRepay.setVisibility(View.GONE);

                    break;
                case MyOrderAdapter.TYPE_PAID:
                    btPay.setVisibility(View.GONE);
                    btCancel.setVisibility(View.GONE);
                    btRepay.setVisibility(View.GONE);
                    llPriceLeft.setVisibility(View.GONE);
                    break;
                case MyOrderAdapter.TYPE_CANCELED:
                    llPriceRight.setVisibility(View.GONE);
                    btPay.setVisibility(View.GONE);
                    btCancel.setVisibility(View.GONE);
                    break;

            }
            recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
            RecyclerView.Adapter myOrderItemAdapter = null;
            String type="1";
            List<OrderVo.OrderListBean>orderList=  data.getOrderList();
            if(orderList!=null&&orderList.size()>0){
                OrderVo.OrderListBean orderListBean=   orderList.get(0);
                ArrayList<OrderVo.OrderListBean>childOrderList=new ArrayList<>();
                for(OrderVo.OrderListBean bean:orderList){
                    if(bean.getOrdersNo().startsWith("Z")){//主订单
                        orderListBean=bean;
                        type="2";
                    }else {
                        childOrderList.add(bean);
                    }
                }
                if(childOrderList.size()==orderList.size()){//没有主订单
                    if(orderListBean.getServiceList()!=null){
                        myOrderItemAdapter = new MyOrderItemAdapter(getContext(), orderListBean.getServiceList());
                    }
                    if(StringUtils.isEmpty(orderListBean.getProductType())){
                        tvProductType.setText("服务产品");
                    }else {
                        tvProductType.setVisibility(View.VISIBLE);
                        switch (orderListBean.getProductType()) {// 商品类型(0普通商品,1买赠商品,2套餐)
//                            case "1"://比较的是hashcode
//                                tvProductType.setText("赠送服务");
//                                break;
                            case "2":
                                tvProductType.setText("套餐内容");
                                break;
                            default:
                                tvProductType.setText("服务产品");
                                break;
                        }
                    }
                }else {//套餐类型
                    myOrderItemAdapter=new MyOrderItemSetmealAdapter(getContext(),childOrderList);
                    tvProductType.setText("赠送服务");
                }
                recycleView.setAdapter(myOrderItemAdapter);


                tvName.setText(orderListBean.getProductName());
                tvPrice.setText(String.valueOf(orderListBean.getProductPrice()));
                if(!StringUtils.isEmpty(orderListBean.getProductUnit())){
                    tvUnit.setText(orderListBean.getProductUnit());
                }else {
                    tvUnit.setText("元");
                }
                tvNum.setText(String.format("x%s", orderListBean.getNum()));
                tvStore.setText(orderListBean.getDeptName());
                tvTotalNum.setText(String.valueOf(orderListBean.getTotalAmount()));

                View.OnClickListener onClickListener= new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), OrderDetailActivity.class);
                        intent.putExtra(OrderDetailActivity.ORDER_ID, data.getOrderId());
                        intent.putExtra(OrderDetailActivity.ORDER_NO, data.getOrdersNo());
                        intent.putExtra(OrderVo.class.getCanonicalName(),data);
                        getContext().startActivity(intent);
                    }
                };
                itemView.setOnClickListener(onClickListener);
                recycleView.setOnClickListener(onClickListener);
                btCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showdialog(data);
                    }
                });

                btPay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), ConfirmOrderActivity.class);
                        intent.putExtra(OrderDetailActivity.ORDER_ID, data.getOrderId());
                        intent.putExtra(OrderDetailActivity.ORDER_NO, data.getOrdersNo());
                        intent.putExtra(OrderVo.class.getCanonicalName(),data);
                        getContext().startActivity(intent);
                    }
                });
                final OrderVo.OrderListBean finalOrderListBean = orderListBean;
                final String finalType = type;
                btRepay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
                        intent.putExtra(GoodsDetailActivity.KEY_ID, finalOrderListBean.getSkuId());
                        intent.putExtra(GoodsDetailActivity.KEY_TYPE, finalType);//1,商品，2，套餐
                        getContext().startActivity(intent);
                    }
                });
            }

        }

    }

    void showdialog(final OrderVo data) {
        View layout = View.inflate(getContext(), R.layout.dialog_order_cancel, null);
        TextView tvLeft = (TextView) layout.findViewById(R.id.tvLeft);
        TextView tvRight = (TextView) layout.findViewById(R.id.tvRight);
        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        String[] strs = {"不想买了", "价格太贵", "价格波动", "无法支付订单", "重复下单", "其他原因",};
        ArrayList<CancleReasonVo> list = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            CancleReasonVo cancleReasonVo = new CancleReasonVo();
            cancleReasonVo.setName(strs[i]);
            list.add(cancleReasonVo);
        }
        final DialogCancelOrderAdapter dialogCancelOrderAdapter = new DialogCancelOrderAdapter(getContext(), list);
        recyclerView.setAdapter(dialogCancelOrderAdapter);


        final Dialog dialog = new AlertDialog.Builder(getContext(), R.style.dialog).create();
        dialog.show();

        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.gravity = Gravity.CENTER;
//       lp.width = SizeUtils.dp2px(getContext(),300);//宽高可设置具体大小
//       lp.width = ViewGroup.LayoutParams.MATCH_PARENT;//宽高可设置具体大小
//       lp.height = ViewGroup.LayoutParams.MATCH_PARENT;//宽高可设置具体大小
//       lp.alpha=0.8f;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setContentView(layout);
        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                CancleReasonVo cancleReasonVo = dialogCancelOrderAdapter.getSelectedReason();
                if(cancelOrderListner!=null){
                    cancelOrderListner.cancelOrder( data.getOrderId(),cancleReasonVo.getName());
                }

            }
        });
    }
    public interface CancelOrderListner{
        public void cancelOrder(String orderId,String cancelReason);
    }

}

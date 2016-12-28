package com.yiqiao.cpmanager.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.google.gson.Gson;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.app.Constants;
import com.yiqiao.cpmanager.base.BaseFragment;
import com.yiqiao.cpmanager.entity.HttpResult;
import com.yiqiao.cpmanager.entity.OrderListRequestVo;
import com.yiqiao.cpmanager.entity.OrderVo;
import com.yiqiao.cpmanager.http.RetrofitHelper;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.transformer.PageTransformer;
import com.yiqiao.cpmanager.ui.activity.MyOrderActivity;
import com.yiqiao.cpmanager.ui.adapter.MyOrderAdapter;
import com.yiqiao.cpmanager.util.LogUtils;
import com.yiqiao.cpmanager.util.SharedPreferenceUtil;
import com.yiqiao.cpmanager.util.SystemUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscription;

/**
 * Created by codeest on 2016/8/11.
 * 企业管家购买服务
 */
public class MyOrderFragment extends BaseFragment  implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{


    public static final int PAGE_SIZE = 10;
    MyOrderAdapter adapter;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    private int page;

    private int type;

    public static final String ORDER_TYPE="orderType";
    String  orderState;// "订单状态",// 1待提交、2待收款、3已部分收款、4已完成(包含已全部收款)、5已取消、7申请退单、8、已退款
    @Override
    protected int getLayoutId() {
        return R.layout.frag_my_order;
    }

    public static MyOrderFragment getInstance(int type){
        MyOrderFragment myOrderFragment=new MyOrderFragment();
        myOrderFragment.type=type;
        switch (type){
            case 0:
                myOrderFragment .orderState="2";
                break;
            case 1:
                myOrderFragment .orderState="4";
                break;
            case 2:
                myOrderFragment .orderState="5";
                break;
        }
        return myOrderFragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtils.e("orderState"+orderState);
    }

    @Override
    protected void initEventAndData() {

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
//        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY,Util.dip2px(this,0.5f), Util.dip2px(this,72),0);
//        itemDecoration.setDrawLastItem(false);
//        recyclerView.addItemDecoration(itemDecoration);
         View errorView= recyclerView.getErrorView();
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.showProgress();
                loadData();
            }
        });
        adapter=new MyOrderAdapter(mActivity,new ArrayList<OrderVo>());
        adapter.setType(type);
        recyclerView.setAdapterWithProgress(adapter);
        adapter.setMore(R.layout.view_more_footer, this);
        adapter.setNoMore(R.layout.view_nomore_footer, new RecyclerArrayAdapter.OnNoMoreListener() {
            @Override
            public void onNoMoreShow() {
//                adapter.resumeMore();
            }

            @Override
            public void onNoMoreClick() {
//                adapter.resumeMore();
            }
        });

//        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(int position) {
//                adapter.remove(position);
//                return true;
//            }
//        });
        adapter.setError(R.layout.view_error_footer, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
            }
        });

        recyclerView.setRefreshListener(this);
//        onRefresh();
    }


    @Override
    public void onRefresh() {
        LogUtils.e("onRefresh");
        page = 0;
        loadData();


    }

    @Override
    protected void lazyLoadData() {
        super.lazyLoadData();
//        adapter.resumeMore();
        loadData();
    }

    private void loadData() {
        LogUtils.e("loadData");
        String customId=SharedPreferenceUtil.getAppSp().getInt(Constants.USER_ID)+"";
//        String commentState="0";//"commentState": "是否评价"  1、是 0 、否
        customId="54142";//todo test
        OrderListRequestVo orderListRequestVo=new OrderListRequestVo(customId,orderState);
        orderListRequestVo.setPage(page);
        orderListRequestVo.setPageSize(PAGE_SIZE);
        String sysCode="111";
        String timeStamp=String.valueOf(System.currentTimeMillis());
        String param=new Gson().toJson(orderListRequestVo);
        String sign= SystemUtil.getSign(sysCode,timeStamp,param);

        Subscription rxSubscription = RetrofitHelper.getXtApiService()
                .findOrderListByCustomer(sysCode,timeStamp,param,sign)
                .compose(new PageTransformer<HttpResult<List<OrderVo>>>())
                .subscribe(new RxSubscriber<HttpResult<List<OrderVo>>>(MyOrderFragment.this) {
                    // 必须重写
                    @Override
                    public void onNext(HttpResult<List<OrderVo>> contentBeen) {
                        recyclerView.showRecycler();
                        recyclerView.setRefreshing(false);
                        //todo save img,name etc user's information
                        if(page==0){
                            adapter.clear();
                        }
                        adapter.addAll(contentBeen.getData());
                        if(contentBeen.getData().size()<PAGE_SIZE){
                            adapter.stopMore();
                        }else {
//                            adapter.pauseMore();
                        }
                        if(type==0){
                            MyOrderActivity myOrderActivity= (MyOrderActivity) mActivity;
                            myOrderActivity.showUnpaidNum(contentBeen.getTotal());
                        }
                        page++;
                    }


                    // 无需设置可以不用重写
                    // !!!!注意参数为ApiException 类型，要不要写在Throwable那个了
                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        if(adapter.getCount()<=0){
                            recyclerView.showError();
                            isFirst=true;
                        }else {
                            recyclerView.showRecycler();
                            recyclerView.setRefreshing(false);
                            adapter.pauseMore();
                        }
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
    public void onLoadMore() {
        LogUtils.e("onLoadMore");
        loadData();
    }

    @Override
    public void onRequestStart() {
//        super.onRequestStart();
    }

    @Override
    public void onRequestCompleted() {
//        super.onRequestCompleted();
    }

    @Override
    public void onNetUnAvailable() {
        super.onNetUnAvailable();
//        if(adapter.getCount()<=0){
//            recyclerView.showError();
//        }else {
//
//        }
    }
}

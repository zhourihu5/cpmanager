package com.yiqiao.cpmanager.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.app.Constants;
import com.yiqiao.cpmanager.base.BaseFragment;
import com.yiqiao.cpmanager.entity.CouponRequestVo;
import com.yiqiao.cpmanager.entity.CouponVo;
import com.yiqiao.cpmanager.entity.HttpResult;
import com.yiqiao.cpmanager.entity.OrderVo;
import com.yiqiao.cpmanager.http.RetrofitHelper;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.transformer.PageTransformer;
import com.yiqiao.cpmanager.ui.adapter.MyCouponAdapter;
import com.yiqiao.cpmanager.util.LogUtils;
import com.yiqiao.cpmanager.util.SharedPreferenceUtil;
import com.yiqiao.cpmanager.util.SystemUtil;

import java.util.ArrayList;

import butterknife.BindView;
import rx.Subscription;

/**
 * Created by codeest on 2016/8/11.
 * 企业管家购买服务
 */
public class MyCouponFragment extends BaseFragment  implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{


    private static final int PAGE_SIZE = 10;
    MyCouponAdapter adapter;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    private int page=1;

    private int type;

    public static final String ORDER_TYPE="orderType";

    @Override
    protected int getLayoutId() {
        return R.layout.frag_my_order;
    }

    public static MyCouponFragment getInstance(int type){
        MyCouponFragment myOrderFragment=new MyCouponFragment();
        myOrderFragment.type=type;
        return myOrderFragment;
    }
    @Override
    protected void initEventAndData() {

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
//        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY,Util.dip2px(this,0.5f), Util.dip2px(this,72),0);
//        itemDecoration.setDrawLastItem(false);
//        recyclerView.addItemDecoration(itemDecoration);
        View emptyView= recyclerView.getEmptyView();
        TextView tvEmpty= (TextView) emptyView.findViewById(R.id.tvEmtpy);
        tvEmpty.setText("还没有优惠券~");
        ImageView ivEmpty= (ImageView) emptyView.findViewById(R.id.ivEmpty);
        ivEmpty.setImageResource(R.drawable.empty_coupon);
        recyclerView.getErrorView().findViewById(R.id.retry_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.showProgress();
                loadData();
            }
        });
        adapter=new MyCouponAdapter(mActivity,new ArrayList<CouponVo.RecordListBean>());
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
    }

    @Override
    public void onRefresh() {
        LogUtils.e("onRefresh");
        page = 1;
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
        String customId= SharedPreferenceUtil.getAppSp().getInt(Constants.USER_ID)+"";
//        String commentState="0";//"commentState": "是否评价"  1、是 0 、否
//        customId="54142";//todo test
        CouponRequestVo orderListRequestVo=new CouponRequestVo();
        orderListRequestVo.setCustomerId(customId);
        String useStatus=String.valueOf(type);
        orderListRequestVo.setUseStatus(useStatus);
        orderListRequestVo.setPageNum(page);
        orderListRequestVo.setPageSize(PAGE_SIZE);
        String sysCode="111";
        String timeStamp=String.valueOf(System.currentTimeMillis());
        String param=new Gson().toJson(orderListRequestVo);
        String sign= SystemUtil.getSign(sysCode,timeStamp,param);

        Subscription rxSubscription = RetrofitHelper.getCpMgrApiService()
                .getUserCouponDetail(sysCode,timeStamp,param,sign)
                .compose(new PageTransformer<HttpResult<CouponVo>>())
                .subscribe(new RxSubscriber<HttpResult<CouponVo>>(MyCouponFragment.this) {
                    // 必须重写
                    @Override
                    public void onNext(HttpResult<CouponVo> contentBeen) {
                        recyclerView.setRefreshing(false);
                        //todo save img,name etc user's information
                        if(page==1){
                            adapter.clear();
                        }
                        CouponVo couponVo=contentBeen.getData();

                        adapter.addAll(couponVo.getRecordList());
                        if(couponVo.getRecordList()==null||couponVo.getRecordList().size()<PAGE_SIZE){
                            adapter.stopMore();
                        }else {
//                            adapter.pauseMore();
                        }
                        if(adapter.getCount()>0){
                            recyclerView.showRecycler();
                        }else {
                            recyclerView.showEmpty();
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

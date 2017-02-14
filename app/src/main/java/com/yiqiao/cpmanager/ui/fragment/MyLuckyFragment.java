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
import com.yiqiao.cpmanager.entity.LuckyMoneyRequestVo;
import com.yiqiao.cpmanager.entity.LuckyMoneyVo;
import com.yiqiao.cpmanager.entity.OrderVo;
import com.yiqiao.cpmanager.http.RetrofitHelper;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.transformer.PageTransformer;
import com.yiqiao.cpmanager.ui.activity.MyLuckyMoneyActivity;
import com.yiqiao.cpmanager.ui.activity.MyOrderActivity;
import com.yiqiao.cpmanager.ui.adapter.MyLuckyAdapter;
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
public class MyLuckyFragment extends BaseFragment implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{


    private static final int PAGE_SIZE = 10;
    MyLuckyAdapter adapter;

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    private int page=1;
    private int type;
    private String redtype;

    public static MyLuckyFragment getInstance(int type){
        MyLuckyFragment myOrderFragment=new MyLuckyFragment();
        myOrderFragment.type=type;
        switch (type){
            case 0:
                myOrderFragment .redtype=null;
                break;
            case 1:
                myOrderFragment .redtype="1";
                break;
            case 2:
                myOrderFragment .redtype="2";
                break;
        }
        return myOrderFragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.frag_my_order;
    }

    @Override
    protected void initEventAndData() {

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
//        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY,Util.dip2px(this,0.5f), Util.dip2px(this,72),0);
//        itemDecoration.setDrawLastItem(false);
//        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.getErrorView().findViewById(R.id.retry_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.showProgress();
                loadData();
            }
        });

        recyclerView.setAdapterWithProgress(adapter=new MyLuckyAdapter(mActivity,new ArrayList<LuckyMoneyVo.RecordListBean>()));
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

//        recyclerView.setRefreshListener(this);
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
        LuckyMoneyRequestVo orderListRequestVo=new LuckyMoneyRequestVo();
        orderListRequestVo.setUserId(customId);
        orderListRequestVo.setRedType(redtype);
        orderListRequestVo.setPageNum(page);
        orderListRequestVo.setPageSize(PAGE_SIZE);
        String sysCode="111";
        String timeStamp=String.valueOf(System.currentTimeMillis());
        String param=new Gson().toJson(orderListRequestVo);
        String sign= SystemUtil.getSign(sysCode,timeStamp,param);

        Subscription rxSubscription = RetrofitHelper.getCpMgrApiService()
                .getUserRedDetail(sysCode,timeStamp,param,sign)
                .compose(new PageTransformer<HttpResult<LuckyMoneyVo>>())
                .subscribe(new RxSubscriber<HttpResult<LuckyMoneyVo>>(MyLuckyFragment.this) {
                    // 必须重写
                    @Override
                    public void onNext(HttpResult<LuckyMoneyVo> contentBeen) {
                        recyclerView.showRecycler();
                        recyclerView.setRefreshing(false);
                        //todo save img,name etc user's information
                        if(page==1){
                            adapter.clear();
                        }
                        LuckyMoneyVo couponVo= contentBeen.getData();
                        adapter.addAll(couponVo.getRecordList());
                        if(couponVo.getRecordList()==null||couponVo.getRecordList().size()<PAGE_SIZE){
                            adapter.stopMore();
                        }else {
                        }
                        if(adapter.getCount()>0){
                            recyclerView.showRecycler();
                        }else {
                            recyclerView.showEmpty();
                        }
                        if(type==0){
                            MyLuckyMoneyActivity myLuckyMoneyActivity= (MyLuckyMoneyActivity) mActivity;
                            myLuckyMoneyActivity.setLuckyNum(String.format("%s",couponVo.getRedPackageBalance()));

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

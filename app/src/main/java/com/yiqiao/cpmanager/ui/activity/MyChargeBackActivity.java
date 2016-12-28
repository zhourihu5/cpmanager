package com.yiqiao.cpmanager.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.app.Constants;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.entity.ChargeBackVo;
import com.yiqiao.cpmanager.entity.ChargebackListRequestVo;
import com.yiqiao.cpmanager.entity.HttpResult;
import com.yiqiao.cpmanager.entity.OrderDetailRequestVo;
import com.yiqiao.cpmanager.entity.OrderDetailVo;
import com.yiqiao.cpmanager.entity.OrderListRequestVo;
import com.yiqiao.cpmanager.entity.OrderVo;
import com.yiqiao.cpmanager.http.RetrofitHelper;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.transformer.DefaultTransformer;
import com.yiqiao.cpmanager.transformer.PageTransformer;
import com.yiqiao.cpmanager.ui.adapter.MyChargeBackAdapter;
import com.yiqiao.cpmanager.ui.fragment.MyOrderFragment;
import com.yiqiao.cpmanager.util.DateUtil;
import com.yiqiao.cpmanager.util.LogUtils;
import com.yiqiao.cpmanager.util.NetworkUtil;
import com.yiqiao.cpmanager.util.SharedPreferenceUtil;
import com.yiqiao.cpmanager.util.SystemUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

/**
 * Created by Xu on 2016/11/23.
 */

public class MyChargeBackActivity extends BaseActivity implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private static final int PAGE_SIZE = 10;
    MyChargeBackAdapter adapter;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    private int page=1;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_charge_back;
    }

    @Override
    protected void initEventAndData() {
        tvTitle.setText("我的退款");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY,Util.dip2px(this,0.5f), Util.dip2px(this,72),0);
//        itemDecoration.setDrawLastItem(false);
//        recyclerView.addItemDecoration(itemDecoration);

        adapter = new MyChargeBackAdapter(this, new ArrayList<ChargeBackVo>());
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
        onRefresh();
    }

    @Override
    public void onRefresh() {
        page = 1;
        loadData();
    }
    private void loadData() {
        LogUtils.e("loadData");
        String customId=SharedPreferenceUtil.getAppSp().getInt(Constants.USER_ID)+"";
        customId="54142";//todo test
//        String commentState="0";//"commentState": "是否评价"  1、是 0 、否
        ChargebackListRequestVo orderListRequestVo=new ChargebackListRequestVo(customId);
        orderListRequestVo.setPage(page);
        orderListRequestVo.setPageSize(PAGE_SIZE);
        String sysCode="111";
        String timeStamp=String.valueOf(System.currentTimeMillis());
        String param=new Gson().toJson(orderListRequestVo);
        String sign= SystemUtil.getSign(sysCode,timeStamp,param);

        Subscription rxSubscription = RetrofitHelper.getXtApiService()
                .getChargebackList(sysCode,timeStamp,param,sign)
                .compose(new PageTransformer<HttpResult<List<ChargeBackVo>>>())
                .subscribe(new RxSubscriber<HttpResult<List<ChargeBackVo>>>(mContext) {
                    // 必须重写
                    @Override
                    public void onNext(HttpResult<List<ChargeBackVo>> contentBeen) {
                        recyclerView.showRecycler();
                        recyclerView.setRefreshing(false);
                        //todo save img,name etc user's information
                        if(page==1){
                            adapter.clear();
                        }
                        adapter.addAll(contentBeen.getData());
                        if(contentBeen.getData().size()<PAGE_SIZE){
                            adapter.stopMore();
                        }else {
//                            adapter.pauseMore();
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
        loadData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

package com.yiqiao.cpmanager.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.db.RealmHelper;
import com.yiqiao.cpmanager.db.SearchTradeMarkHistoryVo;
import com.yiqiao.cpmanager.entity.HttpResult;
import com.yiqiao.cpmanager.entity.TrademarkListRequestVo;
import com.yiqiao.cpmanager.entity.TrademarkVo;
import com.yiqiao.cpmanager.http.RetrofitHelper;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.transformer.PageTransformer;
import com.yiqiao.cpmanager.ui.adapter.SearchTradeMarkAdapter;
import com.yiqiao.cpmanager.util.LogUtils;
import com.yiqiao.cpmanager.util.SystemUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;

/**
 * Created by Xu on 2016/11/23.
 */

public class SearchTradeMarkActivity extends BaseActivity implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private static final int PAGE_SIZE = 10;
    SearchTradeMarkAdapter adapter;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.appbar)
    LinearLayout appbar;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @BindView(R.id.ivDelete)
    ImageView ivDelete;
    private int page;

    public static final String KEY_WORD="keyWord";
    String keyWord;
    @Override
    protected int getLayout() {
        return R.layout.activity_search_cp;
    }

    @Override
    protected void initEventAndData() {

        keyWord =getIntent().getStringExtra(KEY_WORD);
        etSearch.setText(keyWord);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY,Util.dip2px(this,0.5f), Util.dip2px(this,72),0);
//        itemDecoration.setDrawLastItem(false);
//        recyclerView.addItemDecoration(itemDecoration);

        adapter = new SearchTradeMarkAdapter(this, new ArrayList<TrademarkVo>());
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

    private void loadData() {
        LogUtils.e("loadData");
        SearchTradeMarkHistoryVo searchTradeMarkVo=new SearchTradeMarkHistoryVo();
        searchTradeMarkVo.setName(keyWord);
        searchTradeMarkVo.setTime(System.currentTimeMillis());
        RealmHelper.getInstance().save(searchTradeMarkVo);

        TrademarkListRequestVo orderListRequestVo = new TrademarkListRequestVo(keyWord);
        orderListRequestVo.setPage(page);
        orderListRequestVo.setPageSize(PAGE_SIZE);
        String sysCode = "111";
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String param = new Gson().toJson(orderListRequestVo);
        String sign = SystemUtil.getSign(sysCode, timeStamp, param);

        Subscription rxSubscription = RetrofitHelper.getXtApiService()
                .getTrademarkList(sysCode, timeStamp, param, sign)
                .compose(new PageTransformer<HttpResult<List<TrademarkVo>>>())
                .subscribe(new RxSubscriber<HttpResult<List<TrademarkVo>>>(mContext) {
                    // 必须重写
                    @Override
                    public void onNext(HttpResult<List<TrademarkVo>> contentBeen) {
                        recyclerView.setRefreshing(false);
                        if(adapter.getCount()<=0){
                            recyclerView.showEmpty();
                        }else {
                            recyclerView.showRecycler();
                        }
                        //todo save img,name etc user's information
                        if (page == 1) {
                            adapter.clear();
                        }
                        adapter.addAll(contentBeen.getData());
                        if (contentBeen.getData().size() < PAGE_SIZE) {
                            adapter.stopMore();
                        } else {
//                            adapter.pauseMore();
                        }
                        page++;
                    }


                    // 无需设置可以不用重写
                    // !!!!注意参数为ApiException 类型，要不要写在Throwable那个了
                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        if (adapter.getCount() <= 0) {
                            recyclerView.showError();
                        } else {
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
    public void onRefresh() {
        page = 0;
        loadData();
//        recyclerView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                adapter.clear();
//                //刷新
//                if (!NetworkUtil.isNetworkAvailable(SearchTradeMarkActivity.this)) {
//                    adapter.pauseMore();
//                    recyclerView.showError();
//                    return;
//                }
//                ArrayList<OrderVo> arrayList = new ArrayList<OrderVo>();
//                for (int i = 0; i < 20; i++) {
//                    OrderVo orderVo = new OrderVo();
//                    arrayList.add(orderVo);
//                }
//                adapter.addAll(arrayList);
//                page = 1;
//            }
//        }, 2000);
    }

    @Override
    public void onLoadMore() {
        loadData();
//        recyclerView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //刷新
//                if (!NetworkUtil.isNetworkAvailable(mContext)) {
//                    adapter.pauseMore();
//                    return;
//                }
//                ArrayList<OrderVo> arrayList = new ArrayList<OrderVo>();
//                for (int i = 0; i < 20; i++) {
//                    OrderVo orderVo = new OrderVo();
//                    arrayList.add(orderVo);
//                }
//                adapter.addAll(arrayList);
//                page++;
//            }
//        }, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ivBack, R.id.ivDelete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressedSupport();
                break;
            case R.id.ivDelete:
                etSearch.setText("");

                break;
        }
    }
}

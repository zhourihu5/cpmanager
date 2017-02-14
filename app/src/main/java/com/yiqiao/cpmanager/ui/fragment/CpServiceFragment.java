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
import com.yiqiao.cpmanager.entity.CouponVo;
import com.yiqiao.cpmanager.entity.CpServiceRequestVo;
import com.yiqiao.cpmanager.entity.HttpResult;
import com.yiqiao.cpmanager.entity.ServiceTypeLevel2Vo;
import com.yiqiao.cpmanager.http.RetrofitHelper;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.transformer.PageTransformer;
import com.yiqiao.cpmanager.ui.adapter.CpServiceLevel2Adapter;
import com.yiqiao.cpmanager.util.LogUtils;
import com.yiqiao.cpmanager.util.SharedPreferenceUtil;
import com.yiqiao.cpmanager.util.SystemUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscription;

/**
 * Created by codeest on 2016/8/11.
 * 公司服务
 */
public class CpServiceFragment extends BaseFragment implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private static final int PAGE_SIZE = Integer.MAX_VALUE;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    //    private CpServiceAdapter mAdapter;
    private CpServiceLevel2Adapter adapter;
    private int page=1;
    String pid;
    @Override
    protected int getLayoutId() {
        return R.layout.frag_cp_service;
    }
    public static CpServiceFragment getInstance(String pid){
        CpServiceFragment cpServiceFragment=new CpServiceFragment();
        cpServiceFragment.pid=pid;
        return cpServiceFragment;
    }
   /*  @Override
    protected void initEventAndData() {
        recycleView.setLayoutManager(new GridLayoutManager(mActivity, 3, GridLayoutManager.VERTICAL, false));
       ArrayList<CpServiceVo> arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CpServiceVo cpServiceVo = new CpServiceVo();
            cpServiceVo.setItemType(i % 5 == 0 ? CpServiceAdapter.TYPE_HEADER : CpServiceAdapter.TYPE_DATA);
            arrayList.add(cpServiceVo);
        }
        mAdapter = new CpServiceAdapter(arrayList);
        recycleView.setAdapter(mAdapter);
        recycleView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                switch (mAdapter.getItemViewType(i)) {
                    case CpServiceAdapter.TYPE_DATA:
                        toActivity(SearchSpuActivity.class);
                        break;
                    case CpServiceAdapter.TYPE_HEADER:

                        break;
                }
            }
        });

//        OnHeaderClickListener headerClickListener = new OnHeaderClickListener() {
//            @Override
//            public void onHeaderClick(View view, int id, int position) {
//            }
//
//            @Override
//            public void onHeaderLongClick(View view, int id, int position) {
//            }
//
//            @Override
//            public void onHeaderDoubleClick(View view, int id, int position) {
//            }
//        };
//        recyclerView.addItemDecoration(new PinnedHeaderItemDecoration.Builder(CpServiceAdapter.TYPE_HEADER).setDividerId(R.drawable.divider).enableDivider(true)
//                .setHeaderClickListener(headerClickListener).create());
        mAdapter.onAttachedToRecyclerView(recycleView);
        mAdapter.notifyDataSetChanged();
    }*/

    @Override
    protected void initEventAndData() {

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
//        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY,Util.dip2px(this,0.5f), Util.dip2px(this,72),0);
//        itemDecoration.setDrawLastItem(false);
//        recyclerView.addItemDecoration(itemDecoration);
        View emptyView = recyclerView.getEmptyView();
        TextView tvEmpty = (TextView) emptyView.findViewById(R.id.tvEmtpy);
        tvEmpty.setText("暂无数据~");
        ImageView ivEmpty = (ImageView) emptyView.findViewById(R.id.ivEmpty);
        ivEmpty.setImageResource(R.drawable.img_default);

        adapter = new CpServiceLevel2Adapter(mActivity, new ArrayList<ServiceTypeLevel2Vo>());
        adapter.setPid(pid);
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
        String customId = SharedPreferenceUtil.getAppSp().getInt(Constants.USER_ID) + "";
//        String commentState="0";//"commentState": "是否评价"  1、是 0 、否
//        customId="54142";//todo test
        CpServiceRequestVo orderListRequestVo = new CpServiceRequestVo();
        orderListRequestVo.setPid(pid);
//        orderListRequestVo.setPageNum(page);
//        orderListRequestVo.setPageSize(PAGE_SIZE);
        String sysCode = "111";
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String param = new Gson().toJson(orderListRequestVo);
        String sign = SystemUtil.getSign(sysCode, timeStamp, param);

        Subscription rxSubscription = RetrofitHelper.getCpMgrApiService()
                .getChildTypeByFrist(sysCode, timeStamp, param, sign)
                .compose(new PageTransformer<HttpResult<List<ServiceTypeLevel2Vo>>>())
                .subscribe(new RxSubscriber<HttpResult<List<ServiceTypeLevel2Vo>>>(CpServiceFragment.this) {
                    // 必须重写
                    @Override
                    public void onNext(HttpResult<List<ServiceTypeLevel2Vo>> contentBeen) {
                        recyclerView.setRefreshing(false);
                        //todo save img,name etc user's information
                        if (page == 1) {
                            adapter.clear();
                        }
                        List<ServiceTypeLevel2Vo> couponVo = contentBeen.getData();

                        adapter.addAll(couponVo);
                        if (couponVo == null || couponVo.size() < PAGE_SIZE) {
                            adapter.stopMore();
                        } else {
//                            adapter.pauseMore();
                        }
                        if (adapter.getCount() > 0) {
                            recyclerView.showRecycler();
                        } else {
                            recyclerView.showEmpty();
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
                            isFirst = true;
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
    public void onLoadMore() {
        LogUtils.e("onLoadMore");
        loadData();
    }

}

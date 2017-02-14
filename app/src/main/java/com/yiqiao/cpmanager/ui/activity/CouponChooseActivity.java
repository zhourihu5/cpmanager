package com.yiqiao.cpmanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.app.Constants;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.entity.CouponRequestVo;
import com.yiqiao.cpmanager.entity.CouponVo;
import com.yiqiao.cpmanager.entity.HttpResult;
import com.yiqiao.cpmanager.http.RetrofitHelper;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.transformer.PageTransformer;
import com.yiqiao.cpmanager.ui.adapter.UseCouponAdapter;
import com.yiqiao.cpmanager.ui.fragment.MyCouponFragment;
import com.yiqiao.cpmanager.util.LogUtils;
import com.yiqiao.cpmanager.util.SharedPreferenceUtil;
import com.yiqiao.cpmanager.util.SystemUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;

/**
 * Created by Xu on 2016/11/23.
 * 企业明细页
 */

public class CouponChooseActivity extends BaseActivity implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private static final int PAGE_SIZE = 10;
    public static final String SKU_ID="skuId";
    UseCouponAdapter adapter;
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
    @BindView(R.id.tvConfirm)
    TextView tvConfirm;
    private int page = 1;
    String skuId;
    @Override
    protected int getLayout() {
        return R.layout.activity_use_coupon;
    }

    @Override
    protected void initEventAndData() {
        skuId=getIntent().getStringExtra(SKU_ID);
        tvTitle.setText("使用优惠券");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY,Util.dip2px(this,0.5f), Util.dip2px(this,72),0);
//        itemDecoration.setDrawLastItem(false);
//        recyclerView.addItemDecoration(itemDecoration);
        View emptyView = recyclerView.getEmptyView();
        TextView tvEmpty = (TextView) emptyView.findViewById(R.id.tvEmtpy);
        tvEmpty.setText("还没有优惠券~");
        ImageView ivEmpty = (ImageView) emptyView.findViewById(R.id.ivEmpty);
        ivEmpty.setImageResource(R.drawable.empty_coupon);
        recyclerView.getErrorView().findViewById(R.id.retry_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.showProgress();
                loadData();
            }
        });
        adapter = new UseCouponAdapter(mContext, new ArrayList<CouponVo.RecordListBean>());
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
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                adapter.setSelected(position);

            }
        });
        recyclerView.setRefreshListener(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        LogUtils.e("onRefresh");
        page = 1;
        loadData();


    }


    private void loadData() {
        LogUtils.e("loadData");
        String customId = SharedPreferenceUtil.getAppSp().getInt(Constants.USER_ID) + "";
//        String commentState="0";//"commentState": "是否评价"  1、是 0 、否
//        customId="54142";//todo test
        CouponRequestVo orderListRequestVo = new CouponRequestVo();
        orderListRequestVo.setCustomerId(customId);
//        orderListRequestVo.setSkuId(skuId);
        String useStatus = "0";
        orderListRequestVo.setUseStatus(useStatus);
        orderListRequestVo.setPageNum(page);
        orderListRequestVo.setPageSize(PAGE_SIZE);
        String sysCode = "111";
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String param = new Gson().toJson(orderListRequestVo);
        String sign = SystemUtil.getSign(sysCode, timeStamp, param);

        Subscription rxSubscription = RetrofitHelper.getCpMgrApiService()
                .getUserCouponDetail(sysCode, timeStamp, param, sign)
//                .getCouponBySku(sysCode, timeStamp, param, sign)
                .compose(new PageTransformer<HttpResult<CouponVo>>())
                .subscribe(new RxSubscriber<HttpResult<CouponVo>>(mContext) {
                    // 必须重写
                    @Override
                    public void onNext(HttpResult<CouponVo> contentBeen) {
                        recyclerView.setRefreshing(false);
                        //todo save img,name etc user's information
                        if (page == 1) {
                            adapter.clear();
                        }
                        CouponVo couponVo = contentBeen.getData();

                        adapter.addAll(couponVo.getRecordList());
                        if (couponVo.getRecordList() == null || couponVo.getRecordList().size() < PAGE_SIZE) {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ivBack, R.id.tvConfirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressedSupport();
                break;
            case R.id.tvConfirm:
                int position=adapter.getSelected();
                CouponVo.RecordListBean recordListBean = adapter.getItem(position);
                //todo handle data
                Intent intent=new Intent();
                intent.putExtra( CouponVo.RecordListBean.class.getCanonicalName(),recordListBean);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}

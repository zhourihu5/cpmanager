package com.yiqiao.cpmanager.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.StringUtils;
import com.google.gson.Gson;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.db.RealmHelper;
import com.yiqiao.cpmanager.db.SearchSkuHistoryVo;
import com.yiqiao.cpmanager.entity.HttpResult;
import com.yiqiao.cpmanager.entity.SearchSkuRequestVo;
import com.yiqiao.cpmanager.entity.SkuListVo;
import com.yiqiao.cpmanager.entity.SkuVo;
import com.yiqiao.cpmanager.http.RetrofitHelper;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.transformer.PageTransformer;
import com.yiqiao.cpmanager.ui.adapter.SearchSpuAdapter;
import com.yiqiao.cpmanager.util.LogUtils;
import com.yiqiao.cpmanager.util.SystemUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;

/**
 * Created by Xu on 2016/11/23.
 */

public class SearchSpuActivity extends BaseActivity implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    public static final String KEY_WORD = "keyWord";
    public static final String LEVEL1_ID = "level1_id";
    public static final String LEVEL2_ID = "level2_id";
    public static final String LEVEL3_ID = "level3_id";
    private static final int PAGE_SIZE = 10;
    SearchSpuAdapter adapter;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    //    @BindView(R.id.tvTitle)
//    TextView tvTitle;
//    @BindView(R.id.tvRight)
//    TextView tvRight;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.ivDelete)
    ImageView ivDelete;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    private int page = 1;

    String keyword;
    String attrLevel1;
    String attrLevel2;
    String attrLevel3;

    @Override
    protected int getLayout() {
        return R.layout.activity_search_spu;
    }

    @Override
    protected void initEventAndData() {
        keyword = getIntent().getStringExtra(KEY_WORD);
        attrLevel1 = getIntent().getStringExtra(LEVEL1_ID);
        attrLevel2 = getIntent().getStringExtra(LEVEL2_ID);
        attrLevel3 = getIntent().getStringExtra(LEVEL3_ID);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(StringUtils.isEmpty(charSequence)){
                    ivDelete.setVisibility(View.GONE);
                }else {
                    ivDelete.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etSearch.setText(keyword);
        if(!StringUtils.isEmpty(keyword)){
            etSearch.setSelection(keyword.length());
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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

        adapter = new SearchSpuAdapter(this, new ArrayList<SkuVo>());
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

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    keyword = etSearch.getText().toString();
                    //todo searchData and save keyword
                    recyclerView.setRefreshing(true);
                    page = 1;
                    loadData();
                    return true;
                }
                return false;
            }
        });
        //todo searchData
        onRefresh();
    }

    private void loadData() {
        LogUtils.e("loadData");
        if (!StringUtils.isEmpty(keyword)) {
            SearchSkuHistoryVo searchSkuHistoryVo = new SearchSkuHistoryVo();
            searchSkuHistoryVo.setName(keyword);
            RealmHelper.getInstance().save(searchSkuHistoryVo);
        }
//        String commentState="0";//"commentState": "是否评价"  1、是 0 、否
        SearchSkuRequestVo searchSkuRequestVo = new SearchSkuRequestVo();
        searchSkuRequestVo.setCondition(keyword);
        searchSkuRequestVo.setAttrLevel1(attrLevel1);
        searchSkuRequestVo.setAttrLevel3(attrLevel3);
        searchSkuRequestVo.setPageNum(page);
        searchSkuRequestVo.setPageSize(PAGE_SIZE);
        String sysCode = "111";
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String param = new Gson().toJson(searchSkuRequestVo);
        String sign = SystemUtil.getSign(sysCode, timeStamp, param);

        Subscription rxSubscription = RetrofitHelper.getCpMgrApiService()
                .getProductOrPackage(sysCode, timeStamp, param, sign)
                .compose(new PageTransformer<HttpResult<SkuListVo>>())
                .subscribe(new RxSubscriber<HttpResult<SkuListVo>>(mContext) {
                    // 必须重写
                    @Override
                    public void onNext(HttpResult<SkuListVo> contentBeen) {
                        LogUtils.e("searchspu onNext");

                        recyclerView.setRefreshing(false);
                        if (page == 1) {
                            adapter.clear();
                        }
                        adapter.setKeyWord(keyword);
                        adapter.addAll(contentBeen.getData().getRecordList());
                        if(contentBeen.getData().getRecordList()==null){
                            adapter.stopMore();
                        }else if (contentBeen.getData().getRecordList().size() < PAGE_SIZE) {
                            adapter.stopMore();
                        } else {
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
                        LogUtils.e("searchspu onError");
                        ex.printStackTrace();
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
        page = 1;
        loadData();
    }

    @Override
    public void onLoadMore() {
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

package com.yiqiao.cpmanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.BarUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.db.RealmHelper;
import com.yiqiao.cpmanager.db.SearchSkuHistoryVo;
import com.yiqiao.cpmanager.entity.ServiceTypeLevel1Vo;
import com.yiqiao.cpmanager.http.RetrofitHelper;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.transformer.DefaultTransformer;
import com.yiqiao.cpmanager.ui.adapter.SearchSkuHistoryAdapter;
import com.yiqiao.cpmanager.util.SystemUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;

/**
 * Created by Xu on 2016/11/23.
 */

public class HomeSearchActivity extends BaseActivity {

    SearchSkuHistoryAdapter adapter;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.flServiceType)
    TagFlowLayout flServiceType;
    @BindView(R.id.ivDelete)
    ImageView ivDelete;
    MyTagAdapter myTagAdapter;

    View footer;
    @Override
    protected int getLayout() {
        return R.layout.activity_search_home;
    }

    @Override
    protected void setStatusBar() {
//        super.setStatusBar();
        BarUtils.setTranslucentForCoordinatorLayout(this, 0);
        BarUtils.setTransparentForImageView(this, toolbar);
        BarUtils.StatusBarLightMode(this);
        ivBack.setImageResource(R.drawable.back);
    }

    @Override
    protected void initEventAndData() {
        recycleView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new SearchSkuHistoryAdapter(mContext, new ArrayList<SearchSkuHistoryVo>());
        List<SearchSkuHistoryVo> list = RealmHelper.getInstance().getSearchSkuVoList();
        //todo 显示最近的8条
        adapter.addAll(list);
        recycleView.setAdapter(adapter);

        footer = View.inflate(mContext, R.layout.search_bottom_clear_hitory, null);
        setRecycleFooter();
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
        ivDelete.setVisibility(View.GONE);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Intent intent = new Intent(mContext, SearchSpuActivity.class);
                    intent.putExtra(SearchSpuActivity.KEY_WORD, etSearch.getText().toString());
                    startActivity(intent);
                }
                return false;
            }
        });

        flServiceType.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
               ServiceTypeLevel1Vo serviceTypeVo= myTagAdapter.getItem(position);
                Intent intent = new Intent(mContext, SearchSpuActivity.class);
                intent.putExtra(SearchSpuActivity.KEY_WORD, etSearch.getText().toString());
                intent.putExtra(SearchSpuActivity.LEVEL1_ID, serviceTypeVo.getTypeId());
                startActivity(intent);
                return false;
            }
        });
        loadData();
    }

    private void setRecycleFooter() {
        if (adapter.getCount() > 0) {
            adapter.setNoMore(footer, new RecyclerArrayAdapter.OnNoMoreListener() {
                @Override
                public void onNoMoreShow() {
//                adapter.resumeMore();
                }

                @Override
                public void onNoMoreClick() {
                    adapter.clear();
                    RealmHelper.getInstance().clearSearchSkuVos();
                }
            });
            adapter.stopMore();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.clear();
        List<SearchSkuHistoryVo> list = RealmHelper.getInstance().getSearchSkuVoList();
        //todo 显示最近的8条
        adapter.addAll(list);
        setRecycleFooter();
    }

    void loadData(){
        String sysCode="111";
        String timeStamp=String.valueOf(System.currentTimeMillis()/1000);
        String param="";
        String sign= SystemUtil.getSign(sysCode,timeStamp,param);


        Subscription rxSubscription = RetrofitHelper.getCpMgrApiService()
                .getFirstLevel(sysCode,timeStamp,param,sign)
                .compose(new DefaultTransformer<List<ServiceTypeLevel1Vo>>())
                .subscribe(new RxSubscriber<List<ServiceTypeLevel1Vo>>(mContext) {
                    // 必须重写
                    @Override
                    public void onNext(List<ServiceTypeLevel1Vo> contentBeen) {
                        //todo save img,name etc user's information
                        myTagAdapter=new MyTagAdapter(contentBeen);
                        flServiceType.setAdapter(myTagAdapter);

                    }


                    // 无需设置可以不用重写
                    // !!!!注意参数为ApiException 类型，要不要写在Throwable那个了
                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                    }

                    // 无需设置可以不用重写
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }
                });
        addSubscrebe(rxSubscription);
    }


    @OnClick({R.id.ivBack, R.id.ivDelete})
    public void onClick         (View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressedSupport();
                break;
            case R.id.ivDelete:
                etSearch.setText("");
                break;
        }
    }

    @Override
    public void onRequestStart() {
        super.onRequestStart();
        initProgressDialog();
        progressDialog.show();
    }

    class MyTagAdapter extends TagAdapter<ServiceTypeLevel1Vo> {


        public MyTagAdapter(List<ServiceTypeLevel1Vo> datas) {
            super(datas);
        }

        @Override
        public View getView(FlowLayout parent, int position, ServiceTypeLevel1Vo s) {
            View view = View.inflate(mContext, R.layout.item_tag_home_search, null);
            TextView tvTag = (TextView) view.findViewById(R.id.tvTag);
            tvTag.setText(s.getName());
            return view;
        }
    }
}

package com.yiqiao.cpmanager.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.BarUtils;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.db.RealmHelper;
import com.yiqiao.cpmanager.db.SearchTradeMarkHistoryVo;
import com.yiqiao.cpmanager.ui.adapter.SearchTrademarkHistoryAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Xu on 2016/11/23.
 */

public class SearchTradeMarkHistoryActivity extends BaseActivity {

    SearchTrademarkHistoryAdapter adapter;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.appbar)
    LinearLayout appbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.etSearch)
    EditText etSearch;

    @Override
    protected int getLayout() {
        return R.layout.activity_search_trade_mark_history;
    }

    @Override
    protected void setStatusBar() {
//        super.setStatusBar();
        BarUtils.setTranslucentForCoordinatorLayout(this, 0);
        BarUtils.setTransparentForImageView(this, toolbar);
        ivBack.setImageResource(R.drawable.back_white);
        tvTitle.setText("查询商标");
        tvTitle.setTextColor(Color.WHITE);
        tvRight.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initEventAndData() {
        recycleView.setLayoutManager(new LinearLayoutManager(mContext));
        List<SearchTradeMarkHistoryVo>list=RealmHelper.getInstance().getSearchTradeMarkVoList();
        adapter = new SearchTrademarkHistoryAdapter(mContext, list);
        recycleView.setAdapter(adapter);

        View footer = View.inflate(mContext, R.layout.search_bottom_clear_hitory, null);
        adapter.setNoMore(footer, new RecyclerArrayAdapter.OnNoMoreListener() {
            @Override
            public void onNoMoreShow() {
//                adapter.resumeMore();
            }

            @Override
            public void onNoMoreClick() {
                RealmHelper.getInstance().clearSearchTradeMarkVos();
                adapter.clear();
//                adapter.resumeMore();
            }
        });
        adapter.stopMore();

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId== EditorInfo.IME_ACTION_SEARCH){
                    Intent intent=new Intent(mContext,SearchTradeMarkActivity.class);
                    intent.putExtra(SearchTradeMarkActivity.KEY_WORD,etSearch.getText().toString());
                    startActivity(intent);

                }
                return false;
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.clear();
        List<SearchTradeMarkHistoryVo>list=RealmHelper.getInstance().getSearchTradeMarkVoList();
        adapter.addAll(list);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ivBack)
    public void onClick() {
        onBackPressedSupport();
    }
}

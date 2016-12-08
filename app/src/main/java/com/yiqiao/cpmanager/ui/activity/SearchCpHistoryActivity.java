package com.yiqiao.cpmanager.ui.activity;

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
import com.yiqiao.cpmanager.entity.OrderVo;
import com.yiqiao.cpmanager.ui.adapter.SearchCpHistoryAdapter;
import com.yiqiao.cpmanager.util.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Xu on 2016/11/23.
 */

public class SearchCpHistoryActivity extends BaseActivity {

    SearchCpHistoryAdapter adapter;
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
        return R.layout.activity_search_cp_history;
    }

    @Override
    protected void setStatusBar() {
//        super.setStatusBar();
        BarUtils.setTranslucentForCoordinatorLayout(this, 0);
        BarUtils.setTransparentForImageView(this, toolbar);
        ivBack.setImageResource(R.drawable.back_white);
        tvTitle.setText("搜索企业");
        tvTitle.setTextColor(Color.WHITE);
        tvRight.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initEventAndData() {
        recycleView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new SearchCpHistoryAdapter(mContext, new ArrayList<OrderVo>());
        ArrayList<OrderVo> arrayList = new ArrayList<OrderVo>();
        //todo 显示最近的8条
        for (int i = 0; i < 8; i++) {
            OrderVo orderVo = new OrderVo();
            arrayList.add(orderVo);
        }
        adapter.addAll(arrayList);
        recycleView.setAdapter(adapter);

        View footer = View.inflate(mContext, R.layout.search_bottom_clear_hitory, null);
        adapter.setNoMore(footer, new RecyclerArrayAdapter.OnNoMoreListener() {
            @Override
            public void onNoMoreShow() {
//                adapter.resumeMore();
            }

            @Override
            public void onNoMoreClick() {
                ToastUtil.shortShow("清空历史");
//                adapter.resumeMore();
            }
        });
        adapter.stopMore();

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId== EditorInfo.IME_ACTION_SEARCH){
                    toActivity(SearchTradeMarkActivity.class);
//                    SearchTradeMarkVo searchTradeMarkVo=new SearchTradeMarkVo();
//                    searchTradeMarkVo.set
//                    RealmHelper.getInstance().save();

                }
                return false;
            }
        });
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

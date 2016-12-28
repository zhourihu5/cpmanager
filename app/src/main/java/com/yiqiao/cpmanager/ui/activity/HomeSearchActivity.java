package com.yiqiao.cpmanager.ui.activity;

import android.content.Intent;
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
import com.yiqiao.cpmanager.db.SearchSkuVo;
import com.yiqiao.cpmanager.db.SearchTradeMarkVo;
import com.yiqiao.cpmanager.entity.OrderVo;
import com.yiqiao.cpmanager.ui.adapter.SearchSkuHistoryAdapter;
import com.yiqiao.cpmanager.ui.adapter.SearchTrademarkHistoryAdapter;
import com.yiqiao.cpmanager.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.tvTag1)
    TextView tvTag1;
    @BindView(R.id.tvTag2)
    TextView tvTag2;
    @BindView(R.id.tvTag3)
    TextView tvTag3;
    @BindView(R.id.tvTag4)
    TextView tvTag4;
    @BindView(R.id.tvTag5)
    TextView tvTag5;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;

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
        adapter = new SearchSkuHistoryAdapter(mContext, new ArrayList<SearchSkuVo>());
        ArrayList<SearchTradeMarkVo> arrayList = new ArrayList<SearchTradeMarkVo>();
        List<SearchSkuVo>list= RealmHelper.getInstance().getSearchSkuVoList();
        //todo 显示最近的8条
//        for (int i = 0; i < 8; i++) {
//            SearchTradeMarkVo orderVo = new SearchTradeMarkVo();
//            arrayList.add(orderVo);
//        }
        adapter.addAll(list);
        recycleView.setAdapter(adapter);

        View footer = View.inflate(mContext, R.layout.search_bottom_clear_hitory, null);
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

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Intent intent=new Intent(mContext,SearchSpuActivity.class);
                    intent.putExtra(SearchSpuActivity.KEY_WORD,etSearch.getText().toString());
                    startActivity(intent);
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

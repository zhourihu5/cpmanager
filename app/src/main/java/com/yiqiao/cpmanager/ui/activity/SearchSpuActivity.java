package com.yiqiao.cpmanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.entity.OrderVo;
import com.yiqiao.cpmanager.ui.adapter.SearchSpuAdapter;
import com.yiqiao.cpmanager.util.NetworkUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xu on 2016/11/23.
 */

public class SearchSpuActivity extends BaseActivity implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    public static final String KEY_WORD = "keyWord";
    SearchSpuAdapter adapter;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    //    @BindView(R.id.tvTitle)
//    TextView tvTitle;
//    @BindView(R.id.tvRight)
//    TextView tvRight;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.ivDelete)
    ImageView ivDelete;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    private int page;

    String keyword;

    @Override
    protected int getLayout() {
        return R.layout.activity_search_spu;
    }

    @Override
    protected void initEventAndData() {
        keyword = getIntent().getStringExtra(KEY_WORD);
        etSearch.setText(keyword);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY,Util.dip2px(this,0.5f), Util.dip2px(this,72),0);
//        itemDecoration.setDrawLastItem(false);
//        recyclerView.addItemDecoration(itemDecoration);

        adapter = new SearchSpuAdapter(this, new ArrayList<OrderVo>());
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
                    keyword=etSearch.getText().toString();
                    //todo searchData and save keyword
                }
                return false;
            }
        });
        //todo searchData
        onRefresh();
    }


    @Override
    public void onRefresh() {
        page = 0;
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                //刷新
                if (!NetworkUtil.isNetworkAvailable(SearchSpuActivity.this)) {
                    adapter.pauseMore();
                    recyclerView.showError();
                    return;
                }
                ArrayList<OrderVo> arrayList = new ArrayList<OrderVo>();
                for (int i = 0; i < 20; i++) {
                    OrderVo orderVo = new OrderVo();
                    arrayList.add(orderVo);
                }
                adapter.addAll(arrayList);
                page = 1;
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                //刷新
                if (!NetworkUtil.isNetworkAvailable(mContext)) {
                    adapter.pauseMore();
                    return;
                }
                ArrayList<OrderVo> arrayList = new ArrayList<OrderVo>();
                for (int i = 0; i < 20; i++) {
                    OrderVo orderVo = new OrderVo();
                    arrayList.add(orderVo);
                }
                adapter.addAll(arrayList);
                page++;
            }
        }, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

package com.yiqiao.cpmanager.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.entity.OrderVo;
import com.yiqiao.cpmanager.ui.adapter.HomeToplineAdapter;
import com.yiqiao.cpmanager.ui.adapter.MyChargeBackAdapter;
import com.yiqiao.cpmanager.util.NetworkUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xu on 2016/11/23.
 * 快报头条
 */

public class ToplineActivity extends BaseActivity implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    HomeToplineAdapter adapter;
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
    private int page;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_charge_back;
    }

    @Override
    protected void initEventAndData() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY,Util.dip2px(this,0.5f), Util.dip2px(this,72),0);
//        itemDecoration.setDrawLastItem(false);
//        recyclerView.addItemDecoration(itemDecoration);

        adapter = new HomeToplineAdapter(this, new ArrayList<OrderVo>());
        recyclerView.setAdapterWithProgress(adapter);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore, new RecyclerArrayAdapter.OnNoMoreListener() {
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
        adapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
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
        page = 0;
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                //刷新
                if (!NetworkUtil.isNetworkAvailable(ToplineActivity.this)) {
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

package com.yiqiao.cpmanager.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseFragment;
import com.yiqiao.cpmanager.entity.OrderVo;
import com.yiqiao.cpmanager.ui.adapter.InvoicePreset2Adapter;
import com.yiqiao.cpmanager.util.NetworkUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by codeest on 2016/8/11.
 * 企业管家购买服务
 */
public class InvoicePreset2Fragment extends BaseFragment  implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{


    InvoicePreset2Adapter adapter;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    private int page;

    private int type;

    public static final String ORDER_TYPE="orderType";
    @Override
    protected int getLayoutId() {
        return R.layout.frag_my_order;
    }

    public static InvoicePreset2Fragment getInstance(){
        InvoicePreset2Fragment myOrderFragment=new InvoicePreset2Fragment();
//        myOrderFragment.type=type;
        return myOrderFragment;
    }
    @Override
    protected void initEventAndData() {

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
//        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY,Util.dip2px(this,0.5f), Util.dip2px(this,72),0);
//        itemDecoration.setDrawLastItem(false);
//        recyclerView.addItemDecoration(itemDecoration);

        adapter=new InvoicePreset2Adapter(mActivity,new ArrayList<OrderVo>());
        adapter.setType(type);
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

    @Override
    public void onRefresh() {
        page = 0;
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                //刷新
                if (!NetworkUtil.isNetworkAvailable(mActivity)) {
                    adapter.pauseMore();
                    recyclerView.showError();
                    return;
                }
                ArrayList<OrderVo>arrayList=new ArrayList<OrderVo>();
                for(int i=0;i<20;i++){
                    OrderVo orderVo=new OrderVo();
                    arrayList.add(orderVo);
                }
                adapter.addAll(arrayList);
                page=1;
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
//        recyclerView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //刷新
//                if (!NetworkUtil.isNetworkAvailable(mActivity)) {
//                    adapter.pauseMore();
//                    return;
//                }
//                ArrayList<OrderVo>arrayList=new ArrayList<OrderVo>();
//                for(int i=0;i<20;i++){
//                    OrderVo orderVo=new OrderVo();
//                    arrayList.add(orderVo);
//                }
//                adapter.addAll(arrayList);
//                page++;
//            }
//        }, 2000);
    }
}

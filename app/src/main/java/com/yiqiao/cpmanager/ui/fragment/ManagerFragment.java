package com.yiqiao.cpmanager.ui.fragment;

import android.animation.ValueAnimator;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.utils.BarUtils;
import com.blankj.utilcode.utils.SPUtils;
import com.google.gson.Gson;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.app.Constants;
import com.yiqiao.cpmanager.base.BaseFragment;
import com.yiqiao.cpmanager.entity.HttpResult;
import com.yiqiao.cpmanager.entity.RecommendVo;
import com.yiqiao.cpmanager.entity.ServiceListRequestVo;
import com.yiqiao.cpmanager.entity.ServiceVo;
import com.yiqiao.cpmanager.entity.TaskOrProgressVo;
import com.yiqiao.cpmanager.http.RetrofitHelper;
import com.yiqiao.cpmanager.http.exception.ApiException;
import com.yiqiao.cpmanager.subscribers.RxSubscriber;
import com.yiqiao.cpmanager.transformer.PageTransformer;
import com.yiqiao.cpmanager.ui.activity.CpDetailActivity;
import com.yiqiao.cpmanager.ui.activity.CpNameCheckActivity;
import com.yiqiao.cpmanager.ui.activity.NoticeCenterActivity;
import com.yiqiao.cpmanager.ui.activity.SearchCpHistoryActivity;
import com.yiqiao.cpmanager.ui.activity.SearchTradeMarkHistoryActivity;
import com.yiqiao.cpmanager.ui.activity.TaskCenterActivity;
import com.yiqiao.cpmanager.ui.adapter.ManagerAdapter;
import com.yiqiao.cpmanager.ui.adapter.MgrRecommendAdapter;
import com.yiqiao.cpmanager.util.LogUtils;
import com.yiqiao.cpmanager.util.SharedPreferenceUtil;
import com.yiqiao.cpmanager.util.SystemUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;

/**
 * Created by codeest on 2016/8/11.
 */
public class ManagerFragment extends BaseFragment  implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private static final int PAGE_SIZE = 10;
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
    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.llTaskCenterRed)
    LinearLayout llTaskCenterRed;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;

    private PopMenu popWin;

    ManagerAdapter adapter;
    private int page;

    View emptyView;
    View llToTask;
    RecyclerView rvRecommend;

    MgrRecommendAdapter mgrRecommendAdapter;
    boolean isShowMenu=false;
    @Override
    protected int getLayoutId() {
        return R.layout.frag_manager;
    }

    @Override
    protected void initEventAndData() {
        BarUtils.setTransparentForImageView(mActivity, toolbar);

        tvTitle.setText("企业管家");
        if(isShowMenu){
            tvRight.setCompoundDrawablesWithIntrinsicBounds(R.drawable.menu_expand, 0, 0, 0);
        }else {
            tvRight.setCompoundDrawablesWithIntrinsicBounds(R.drawable.my_news_gray, 0, 0, 0);
        }
        ivBack.setImageResource(R.drawable.customer_service);
        ivBack.setVisibility(View.INVISIBLE);//todo 这个版本先不加这个
        tvRight.setVisibility(View.INVISIBLE);//todo 这个版本先不加这个
        popWin = new PopMenu();

        emptyView=recyclerView.getEmptyView();
        llToTask=emptyView.findViewById(R.id.llToTask);
        llToTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                toActivity(TaskCenterActivity.class);
                toActivity(CpNameCheckActivity.class);
            }
        });
        rvRecommend= (RecyclerView) emptyView.findViewById(R.id.recycleView);
        rvRecommend.setLayoutManager(new GridLayoutManager(mActivity,2));
        mgrRecommendAdapter=new MgrRecommendAdapter(mActivity,new ArrayList<RecommendVo>());
        rvRecommend.setAdapter(mgrRecommendAdapter);

        View errorView=recyclerView.getErrorView();
        errorView.findViewById(R.id.retry_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.showProgress();
                loadData();
            }
        });

        SPUtils spUtils = SharedPreferenceUtil.getAppSp();
        int uid = spUtils.getInt(Constants.USER_ID);
        if (uid > 0) {
//            llTaskCenter.setVisibility(View.GONE);
            llTaskCenterRed.setVisibility(View.GONE);//TODO 任务做完就不显示了 这个版本先不做任务中心了
            recyclerView.showProgress();

        } else {
//            llTaskCenter.setVisibility(View.VISIBLE);
            recyclerView.showEmpty();
            llTaskCenterRed.setVisibility(View.GONE);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        ArrayList<ServiceVo> arrayList = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            arrayList.add(new OrderVo());
//        }
        adapter = new ManagerAdapter(mActivity, arrayList);
        recyclerView.setRefreshListener(this);
        recyclerView.setAdapterWithProgress(adapter);
    }

    @Override
    protected void lazyLoadData() {
        super.lazyLoadData();
        loadData();
    }

   /* private void loadData() {
        LogUtils.e("loadData");
        String customId = SharedPreferenceUtil.getAppSp().getInt(Constants.USER_ID) + "";
        customId="1";
//        customId = "54142";//todo test
//        String commentState="0";//"commentState": "是否评价"  1、是 0 、否
        ServiceListRequestVo orderListRequestVo = new ServiceListRequestVo(customId);
        orderListRequestVo.setCurrentPage(page);
        orderListRequestVo.setPageSize(PAGE_SIZE);
        String sysCode = "111";
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String param = new Gson().toJson(orderListRequestVo);
        String sign = SystemUtil.getSign(sysCode, timeStamp, param);

        Subscription rxSubscription = RetrofitHelper.getXtApiService()
                .getServiceList(sysCode, timeStamp, param, sign)
                .compose(new PageTransformer<HttpResult<List<ServiceVo>>>())
                .subscribe(new RxSubscriber<HttpResult<List<ServiceVo>>>(ManagerFragment.this) {
                    // 必须重写
                    @Override
                    public void onNext(HttpResult<List<ServiceVo>> contentBeen) {
                        recyclerView.setRefreshing(false);
                        //todo save img,name etc user's information
                        if (page == 1) {
                            adapter.clear();
                        }
                        adapter.addAll(contentBeen.getData());
                        if (contentBeen.getData()!=null&&contentBeen.getData().size() < PAGE_SIZE) {
                            if(adapter.getCount()>0){
                                adapter.stopMore();
                            }
                        } else {
//                            adapter.pauseMore();
                        }
                        if(adapter.getCount()<=0){
                            recyclerView.showEmpty();
                            loadRecommendData();
                        }else {
                            recyclerView.showRecycler();
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
                            isFirst=true;
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
    }*/
    private void loadData() {
        LogUtils.e("loadData");
        String customId = SharedPreferenceUtil.getAppSp().getInt(Constants.USER_ID) + "";
//        customId="3895546";
        customId = "54142";//todo test
//        String commentState="0";//"commentState": "是否评价"  1、是 0 、否
        ServiceListRequestVo orderListRequestVo = new ServiceListRequestVo(customId);
        orderListRequestVo.setCurrentPage(page);
        orderListRequestVo.setPageSize(PAGE_SIZE);
        String sysCode = "111";
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String param = new Gson().toJson(orderListRequestVo);
        String sign = SystemUtil.getSign(sysCode, timeStamp, param);

        Subscription rxSubscription = RetrofitHelper.getCpMgrApiService()
                .getTaskOrProgress(sysCode, timeStamp, param, sign)
                .compose(new PageTransformer<HttpResult<TaskOrProgressVo>>())
                .subscribe(new RxSubscriber<HttpResult<TaskOrProgressVo>>(ManagerFragment.this) {
                    // 必须重写
                    @Override
                    public void onNext(HttpResult<TaskOrProgressVo> contentBeen) {
                        recyclerView.setRefreshing(false);
                        //todo save img,name etc user's information
                        if (page == 1) {
                            adapter.clear();
                        }
                        TaskOrProgressVo taskOrProgressVo=  contentBeen.getData();
                        List<ServiceVo>serviceVoList=  taskOrProgressVo.getProgressList();
                        adapter.addAll(serviceVoList);
                        if (serviceVoList!=null&&serviceVoList.size() < PAGE_SIZE) {
                            if(adapter.getCount()>0){
                                adapter.stopMore();
                            }
                        } else {
//                            adapter.pauseMore();
                        }
                        if(adapter.getCount()<=0){
                            recyclerView.showEmpty();
//                            loadRecommendData();
                           List<RecommendVo>recommendVoList= taskOrProgressVo.getRecommendVoList();
                            mgrRecommendAdapter.clear();
                            mgrRecommendAdapter.addAll(recommendVoList);
                            List<TaskOrProgressVo.NoOverTaskListBean>taskListBeanList=taskOrProgressVo.getNoOverTaskList();
                            if(taskListBeanList!=null&&taskListBeanList.size()>0){//没有任务

                            }else {//todo handle task

                            }
                        }else {
                            recyclerView.showRecycler();
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
                            isFirst=true;
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
   void   loadRecommendData(){
       LogUtils.e("loadRecommendData");
       String customId = SharedPreferenceUtil.getAppSp().getInt(Constants.USER_ID) + "";
//       customId = "54142";//todo test
//        String commentState="0";//"commentState": "是否评价"  1、是 0 、否
       ServiceListRequestVo orderListRequestVo = new ServiceListRequestVo(customId);
       orderListRequestVo.setCurrentPage(page);
       orderListRequestVo.setPageSize(PAGE_SIZE);
       String sysCode = "111";
       String timeStamp = String.valueOf(System.currentTimeMillis());
       String param = new Gson().toJson(orderListRequestVo);
       String sign = SystemUtil.getSign(sysCode, timeStamp, param);

       Subscription rxSubscription = RetrofitHelper.getCpMgrApiService()
               .getRecommendGoods(sysCode, timeStamp, param, sign)
               .compose(new PageTransformer<HttpResult<List<RecommendVo>>>())
               .subscribe(new RxSubscriber<HttpResult<List<RecommendVo>>>(ManagerFragment.this) {
                   // 必须重写
                   @Override
                   public void onNext(HttpResult<List<RecommendVo>> contentBeen) {
                       mgrRecommendAdapter.clear();
                       mgrRecommendAdapter.addAll(contentBeen.getData());
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
//    @Override
//    public void onRequestStart() {
//        super.onRequestStart();
//        initProgressDialog();
//        progressDialog.show();
//    }

    private void showPopMenu() {
        //添加pop窗口关闭事件
        popWin.setOnDismissListener(new PoponDismissListener());
//        popWin.showAsDropDown(tvRight,0,0, Gravity.RIGHT);
        popWin.showAsDropDown(tvRight);
//        backgroundAlpha(0.5f);
        setBackgroundAlpha(1,alpha,240);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @OnClick({R.id.llTaskCenterRed, R.id.ivBack, R.id.tvRight, R.id.ivClose, R.id.llToTask})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                //todo 小能客服
                break;
            case R.id.tvRight:
                if(isShowMenu){
                    showPopMenu();
                }else {
                    toActivity(NoticeCenterActivity.class);
                }
                break;
            case R.id.ivClose:
                llTaskCenterRed.setVisibility(View.GONE);
                break;
            case R.id.llTaskCenterRed:
//                break;
            case R.id.llToTask:
                toActivity(TaskCenterActivity.class);
                break;
        }
    }

    @Override
    public void onRefresh() {
        page=0;
        loadData();
    }

    @Override
    public void onLoadMore() {
        loadData();
    }


    class PopMenu extends PopupWindow {

        @BindView(R.id.llMyCp)
        LinearLayout llMyCp;
        @BindView(R.id.llSearchCp)
        LinearLayout llSearchCp;
        @BindView(R.id.llSearchTrademark)
        LinearLayout llSearchTrademark;
        @BindView(R.id.llNoticeCenter)
        LinearLayout llNoticeCenter;


        public PopMenu() {
            super();
            init();
        }

        public PopMenu(int width, int height) {
            super(width, height);
            init();
        }

        private void init() {
            View contentView = View.inflate(mActivity, R.layout.pop_menu_manager, null);
            ButterKnife.bind(this, contentView);
            setContentView(contentView);
            setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
            this.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
            this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            //点击空白处时，隐藏掉pop窗口
            this.setFocusable(true);
            this.setBackgroundDrawable(new BitmapDrawable());
            setAnimationStyle(R.style.TRM_ANIM_STYLE);
        }

        @OnClick({R.id.llMyCp, R.id.llSearchCp, R.id.llSearchTrademark, R.id.llNoticeCenter})
        public void onClick(View view) {
            dismiss();
            switch (view.getId()) {
                case R.id.llMyCp:
                    toActivity(CpDetailActivity.class);
                    break;
                case R.id.llSearchCp:
                    toActivity(SearchCpHistoryActivity.class);
                    break;
                case R.id.llSearchTrademark:
                    toActivity(SearchTradeMarkHistoryActivity.class);

                    break;
                case R.id.llNoticeCenter:
                    toActivity(NoticeCenterActivity.class);
                    break;
            }
        }
    }

    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     *
     * @author cg
     */
    class PoponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
//            backgroundAlpha(1f);
            setBackgroundAlpha(alpha,1,300);
        }
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
//    public void backgroundAlpha(float bgAlpha) {
//        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
//        lp.alpha = bgAlpha; //0.0-1.0
//        mActivity.getWindow().setAttributes(lp);
//    }
    float alpha=0.75f;
    private void setBackgroundAlpha(float from, float to, int duration) {
        final WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        ValueAnimator animator = ValueAnimator.ofFloat(from, to);
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                lp.alpha = (float) animation.getAnimatedValue();
                mActivity.getWindow().setAttributes(lp);
            }
        });
        animator.start();
    }
}

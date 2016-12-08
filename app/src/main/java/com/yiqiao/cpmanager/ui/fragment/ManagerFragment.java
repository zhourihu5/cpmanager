package com.yiqiao.cpmanager.ui.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseFragment;
import com.yiqiao.cpmanager.entity.OrderVo;
import com.yiqiao.cpmanager.ui.activity.CpDetailActivity;
import com.yiqiao.cpmanager.ui.activity.NoticeCenterActivity;
import com.yiqiao.cpmanager.ui.activity.SearchCpActivity;
import com.yiqiao.cpmanager.ui.activity.SearchCpHistoryActivity;
import com.yiqiao.cpmanager.ui.activity.SearchTradeMarkActivity;
import com.yiqiao.cpmanager.ui.activity.SearchTradeMarkHistoryActivity;
import com.yiqiao.cpmanager.ui.activity.TaskCenterActivity;
import com.yiqiao.cpmanager.ui.adapter.ManagerAdapter;
import com.yiqiao.cpmanager.widget.FullyHeightRecycleview;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by codeest on 2016/8/11.
 */
public class ManagerFragment extends BaseFragment {


    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.ivTaskCenter)
    ImageView ivTaskCenter;
    @BindView(R.id.llTaskCenter)
    LinearLayout llTaskCenter;
    @BindView(R.id.recycleView)
    FullyHeightRecycleview recycleView;
    private PopMenu popWin;

    ManagerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_manager;
    }

    @Override
    protected void initEventAndData() {
        popWin = new PopMenu();

        recycleView.setLayoutManager(new GridLayoutManager(mActivity,2));
        ArrayList<OrderVo>arrayList=new ArrayList<>();
        for (int i=0;i<20;i++){
            arrayList.add(new OrderVo());
        }
        adapter=new ManagerAdapter(mActivity,arrayList);
        recycleView.setAdapter(adapter);
    }

    private void showPopMenu() {
        //添加pop窗口关闭事件
        popWin.setOnDismissListener(new PoponDismissListener());
//        popWin.showAsDropDown(tvRight,0,0, Gravity.RIGHT);
        popWin.showAsDropDown(tvRight);
        backgroundAlpha(0.5f);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.ivBack, R.id.tvRight, R.id.ivTaskCenter, R.id.llTaskCenter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressedSupport();
                break;
            case R.id.tvRight:
                showPopMenu();
                break;
            case R.id.ivTaskCenter:
//                break;
            case R.id.llTaskCenter:
                toActivity(TaskCenterActivity.class);
                break;
        }
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
            backgroundAlpha(1f);

        }

        @OnClick({R.id.llMyCp, R.id.llSearchCp, R.id.llSearchTrademark, R.id.llNoticeCenter})
        public void onClick(View view) {
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
            backgroundAlpha(1f);
        }
    }
    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        mActivity.getWindow().setAttributes(lp);
    }
}

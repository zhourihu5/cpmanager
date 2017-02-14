package com.yiqiao.cpmanager.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;
import com.yiqiao.cpmanager.widget.FullyHeightRecycleview;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Xu on 2016/11/23.
 */

public class FeedBackActivity extends BaseActivity {

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.recycleView)
    FullyHeightRecycleview recycleView;

    ImgAdapter imgAdapter;//TODO 后期迭代版本中增加多张上传功能，先做成单张上传的
    @Override
    protected int getLayout() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void initEventAndData() {
        tvTitle.setText("意见反馈");
        recycleView.setLayoutManager(new GridLayoutManager(mContext,3));

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

    class ImgAdapter  extends RecyclerArrayAdapter<String>{
        String ADD_IMG="addImg";

        public ImgAdapter(Context context, List<String> objects) {
            super(context, objects);
        }

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecommendViewHolder(parent) ;
        }

        class RecommendViewHolder extends BaseViewHolder<String> {

            public RecommendViewHolder(ViewGroup parent) {
                super(parent, R.layout.item_feed_back_img);
                ButterKnife.bind(this, parent);
            }

            @Override
            public void setData(String data) {
                if(ADD_IMG.equals(data)){

                }
            }

        }
    }

}

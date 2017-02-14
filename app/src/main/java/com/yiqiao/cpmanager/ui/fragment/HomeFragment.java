package com.yiqiao.cpmanager.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.BarUtils;
import com.blankj.utilcode.utils.SPUtils;
import com.bumptech.glide.Glide;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.app.Constants;
import com.yiqiao.cpmanager.base.BaseFragment;
import com.yiqiao.cpmanager.component.ImageLoader;
import com.yiqiao.cpmanager.entity.OrderVo;
import com.yiqiao.cpmanager.ui.activity.CityActivity;
import com.yiqiao.cpmanager.ui.activity.CpServiceActivity;
import com.yiqiao.cpmanager.ui.activity.GoodsDetailActivity;
import com.yiqiao.cpmanager.ui.activity.HomeSearchActivity;
import com.yiqiao.cpmanager.ui.activity.HotInfoActivity;
import com.yiqiao.cpmanager.ui.activity.NoticeCenterActivity;
import com.yiqiao.cpmanager.ui.activity.ToplineActivity;
import com.yiqiao.cpmanager.ui.adapter.HomeBossTipsAdapter;
import com.yiqiao.cpmanager.ui.adapter.HomeHotInfoAdapter;
import com.yiqiao.cpmanager.ui.adapter.HomeServiceAdapter;
import com.yiqiao.cpmanager.ui.adapter.HomeToplineAdapter;
import com.yiqiao.cpmanager.util.SharedPreferenceUtil;
import com.yiqiao.cpmanager.util.ToastUtil;
import com.yiqiao.cpmanager.widget.FullyHeightRecycleview;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by codeest on 2016/8/11.
 */
public class HomeFragment extends BaseFragment {


    private static final int REQUEST_CITY_CODE = 1;
    @BindView(R.id.tvCity)
    TextView tvCity;
    @BindView(R.id.etSearch)
    TextView etSearch;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llToolbar)
    LinearLayout llToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vpBanner)
    RollPagerView vpBanner;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.rvService)
    FullyHeightRecycleview rvService;
    @BindView(R.id.rvBossTips)
    FullyHeightRecycleview rvBossTips;
    @BindView(R.id.rvTopline)
    FullyHeightRecycleview rvTopline;
    @BindView(R.id.rvHotinfo)
    FullyHeightRecycleview rvHotinfo;


    HomeServiceAdapter homeServiceAdapter;
    HomeBossTipsAdapter bossTipsAdapter;
    HomeHotInfoAdapter hotInfoAdapter;
    HomeToplineAdapter toplineAdapter;
    BannerLoopAdapter mLoopAdapter;
    @BindView(R.id.llCpService)
    LinearLayout llCpService;
    @BindView(R.id.llLegalAdvice)
    LinearLayout llLegalAdvice;
    @BindView(R.id.llSpot)
    LinearLayout llSpot;
    @BindView(R.id.ivSetMeal1)
    ImageView ivSetMeal1;
    @BindView(R.id.ivSetMeal2)
    ImageView ivSetMeal2;
    @BindView(R.id.ivSetMeal3)
    ImageView ivSetMeal3;
    @BindView(R.id.ivSetMeal4)
    ImageView ivSetMeal4;
    @BindView(R.id.ivSetMeal5)
    ImageView ivSetMeal5;
    @BindView(R.id.llTopLine)
    LinearLayout llTopLine;
    @BindView(R.id.llHotInfo)
    LinearLayout llHotInfo;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_home;
    }

    @Override
    protected void initEventAndData() {
        BarUtils.setTransparentForImageView(mActivity, toolbar);

        setCityText();
        rvBossTips.setLayoutManager(new GridLayoutManager(mContext, 2));
        rvService.setLayoutManager(new GridLayoutManager(mContext, 4));
        rvTopline.setLayoutManager(new LinearLayoutManager(mContext));
        rvHotinfo.setLayoutManager(new LinearLayoutManager(mContext));
        ArrayList<OrderVo> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            OrderVo orderVo = new OrderVo();
            arrayList.add(orderVo);
        }
        homeServiceAdapter = new HomeServiceAdapter(mContext, arrayList);
        bossTipsAdapter = new HomeBossTipsAdapter(mContext, arrayList);
        hotInfoAdapter = new HomeHotInfoAdapter(mContext, arrayList);
        toplineAdapter = new HomeToplineAdapter(mContext, arrayList);

        rvHotinfo.setAdapter(hotInfoAdapter);
        rvService.setAdapter(homeServiceAdapter);
        rvTopline.setAdapter(toplineAdapter);
        rvBossTips.setAdapter(bossTipsAdapter);


//        vpBanner.setPlayDelay(1000);
        vpBanner.setAdapter(mLoopAdapter = new BannerLoopAdapter(vpBanner));
        vpBanner.setHintView(new ColorPointHintView(mActivity, Color.YELLOW, Color.WHITE));
//        vpBanner.setHintView(new IconHintView(this,R.drawable.point_focus,R.drawable.point_normal));
        //mRollViewPager.setHintView(new TextHintView(this));
        //mRollViewPager.setHintView(null);
        vpBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ToastUtil.shortShow("轮播图点击了");
            }
        });
        mLoopAdapter.setImgs(
                new String[]{
                        "http://ww1.sinaimg.cn/large/610dc034jw1faj6sozkluj20u00nt75p.jpg"
                        , "http://ww4.sinaimg.cn/large/610dc034jw1fagrnmiqm1j20u011hanr.jpg"
                        , "http://ww1.sinaimg.cn/large/610dc034jw1fahy9m7xw0j20u00u042l.jpg"
                }
        );

    }

    @Override
    public void onPause() {
        super.onPause();
        vpBanner.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        vpBanner.resume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @OnClick({R.id.tvCity, R.id.etSearch, R.id.tvRight, R.id.llCpService, R.id.llLegalAdvice, R.id.llSpot, R.id.ivSetMeal1, R.id.ivSetMeal2, R.id.ivSetMeal3, R.id.ivSetMeal4, R.id.ivSetMeal5, R.id.llTopLine, R.id.llHotInfo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvCity:
                Intent intent=new Intent(mActivity,CityActivity.class);
               startActivityForResult(intent, REQUEST_CITY_CODE);
                break;
            case R.id.etSearch:
                toActivity(HomeSearchActivity.class);
                break;
            case R.id.tvRight:
                toActivity(NoticeCenterActivity.class);
                break;
            case R.id.llCpService:
                toActivity(CpServiceActivity.class);
                break;
            case R.id.llLegalAdvice:
                ToastUtil.shortShow("服务筹备中，敬请期待！");
                break;
            case R.id.llSpot:
                ToastUtil.shortShow("服务筹备中，敬请期待！");
                break;
            case R.id.ivSetMeal1:
                toActivity(GoodsDetailActivity.class);
                break;
            case R.id.ivSetMeal2:
                break;
            case R.id.ivSetMeal3:
                break;
            case R.id.ivSetMeal4:
                break;
            case R.id.ivSetMeal5:
                break;
            case R.id.llTopLine:
                toActivity(ToplineActivity.class);
                break;
            case R.id.llHotInfo:
                toActivity(HotInfoActivity.class);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CITY_CODE:
                if(resultCode==RESULT_OK){
                    setCityText();
                }
                break;
        }
    }
    void   setCityText(){
        SPUtils spUtils= SharedPreferenceUtil.getAppSp();
        String currentCityName= spUtils .getString(Constants.CURRENT_CITY_NAME,"北京");
        tvCity.setText(currentCityName);
    }

    private class BannerLoopAdapter extends LoopPagerAdapter {
        String[] imgs = new String[0];

        public void setImgs(String[] imgs) {
            this.imgs = imgs;
            notifyDataSetChanged();
        }


        public BannerLoopAdapter(RollPagerView viewPager) {
            super(viewPager);
        }

        @Override
        public View getView(ViewGroup container, int position) {

            ImageView view = new ImageView(container.getContext());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Log.i("RollViewPager","onClick");
                }
            });
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ImageLoader.load(mActivity,imgs[position],view,R.drawable.img_banner_default);

            return view;
        }

        @Override
        public int getRealCount() {
            return imgs.length;
        }

    }
}

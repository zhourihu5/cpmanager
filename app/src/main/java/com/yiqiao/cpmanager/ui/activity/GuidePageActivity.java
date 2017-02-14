package com.yiqiao.cpmanager.ui.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yiqiao.cpmanager.R;
import com.yiqiao.cpmanager.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xu on 2016/11/23.
 * 关于我们
 */

public class GuidePageActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected int getLayout() {
        return R.layout.activity_guidepage;
    }

//    @Override
//    protected void setStatusBar() {
////        super.setStatusBar();
//    }

    @Override
    protected void initEventAndData() {
        ArrayList<View>viewArrayList=new ArrayList<>();
        View viewPage1=View.inflate(mContext,R.layout.item_guide_page1,null);
        View viewPage2=View.inflate(mContext,R.layout.item_guide_page2,null);
        View viewPage3=View.inflate(mContext,R.layout.item_guide_page3,null);
        View tvGo=viewPage3.findViewById(R.id.tvGo);
        tvGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toActivity(MainActivity.class);
            }
        });
        viewArrayList.add(viewPage1);
        viewArrayList.add(viewPage2);
        viewArrayList.add(viewPage3);
        GuidePagerAdapter guidePagerAdapter=new GuidePagerAdapter();
        guidePagerAdapter.setViews(viewArrayList);
        viewPager.setAdapter(guidePagerAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    class GuidePagerAdapter extends PagerAdapter {
        ArrayList<View>views;

        public void setViews(ArrayList<View> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view=views.get(position);
            container.addView(view);
            return view;
       }
    }

}

package com.yiqiao.cpmanager.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Xu on 2016/11/22.
 * 嵌套到滚动控件里的 recycleview
 */

public class FullyHeightRecycleview extends RecyclerView{
    public FullyHeightRecycleview(Context context) {
        super(context);
        init();
    }

    public FullyHeightRecycleview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FullyHeightRecycleview(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    void init(){
        setNestedScrollingEnabled(false);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int newHeightSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, newHeightSpec);
    }
}

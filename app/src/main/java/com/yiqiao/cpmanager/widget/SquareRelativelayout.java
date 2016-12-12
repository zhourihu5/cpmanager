package com.yiqiao.cpmanager.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by codeest on 16/8/13.
 */

public class SquareRelativelayout extends RelativeLayout {

    public SquareRelativelayout(Context context) {
        super(context);
    }

    public SquareRelativelayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SquareRelativelayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}


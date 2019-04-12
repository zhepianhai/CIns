package com.zph.cins.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.zph.cins.R;
// 这里继承FrameLayout的LayoutParams即可
public  class MyZLayoutParams extends FrameLayout.LayoutParams {
    public final static int DEFAULT_ZORDER = 1;
    public int zOrder;
    public MyZLayoutParams(@NonNull Context c, @Nullable AttributeSet attrs) {
        super(c, attrs);
        TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.ZOrderLayout);
        zOrder = a.getInt(R.styleable.ZOrderLayout_layout_zorder, DEFAULT_ZORDER);
        a.recycle();
    }
}

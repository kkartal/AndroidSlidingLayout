package com.coffeebreakcodes.slidinglayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khan on 15/08/2017.
 */

public class SlidingGroupLayout extends RelativeLayout {

    private static final String TAG = "SLIDING_LAYOUT:";
    private List<SlidingLayout> slidingLayoutList;
    private boolean isIndependentGroup;

    public SlidingGroupLayout(Context context) {
        super(context);
        slidingLayoutList = new ArrayList<>();
    }

    public SlidingGroupLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SlidingGroupLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        slidingLayoutList = new ArrayList<>();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SlidingGroupLayout);
        final int N = a.getIndexCount();
        for (int i = 0; i < N; ++i) {
            if (a.getIndex(i) == R.styleable.SlidingGroupLayout_independent_layouts) {
                isIndependentGroup = a.getBoolean(a.getIndex(i), false);
            }
        }
    }

    @Override
    public void onViewAdded(final View child) {
        super.onViewAdded(child);
        if (!isIndependentGroup()) {
            checkChildren(this);
        }
    }

    private void checkChildren(ViewGroup parent) {
        for (int i = parent.getChildCount() - 1; i >= 0; i--) {
            final View child = parent.getChildAt(i);
            if (child instanceof SlidingLayout) {
                slidingLayoutList.add((SlidingLayout) child);
                ((SlidingLayout) child).setStatusListener(new StatusListener() {
                    @Override
                    public void status(boolean isExpanded) {
                        if (!isExpanded) {
                            for (SlidingLayout slidingLayout : slidingLayoutList) {
                                if (!((SlidingLayout) child).getLayoutId().equals(slidingLayout.getLayoutId())) {
                                    slidingLayout.expand();
                                }
                            }
                        }
                    }
                });

            } else if (child instanceof ViewGroup) {
                checkChildren((ViewGroup) child);
            }
        }
    }

    public void expandOthers(SlidingLayout slidingLayout, boolean isExpanded) {
        if (!isExpanded) {
            for (SlidingLayout sLayout : slidingLayoutList) {
                if (!slidingLayout.getLayoutId().equals(sLayout.getLayoutId())) {
                    sLayout.expand();
                }
            }
        }
    }

    public boolean isIndependentGroup() {
        return isIndependentGroup;
    }
}

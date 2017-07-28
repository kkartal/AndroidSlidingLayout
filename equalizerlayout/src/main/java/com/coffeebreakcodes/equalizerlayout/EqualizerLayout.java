package com.coffeebreakcodes.equalizerlayout;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * coffeebreakcodes.com - 2017
 */

public class EqualizerLayout extends RelativeLayout {

    private final String TAG = "SlidingLayoutError";

    private boolean isExpanded = true;
    private float duration = 300;
    private float minSize = 50;
    private float maxSize = 0;
    private int direction = 1;
    private Context context;

    private StatusListener statusListener;


    private String orientation;

    public EqualizerLayout(Context context) {
        super(context);
        this.context = context;
        initSwipeListener();
    }

    public EqualizerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initSwipeListener();
    }

    public EqualizerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initSwipeListener();
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public float getMinSize() {
        return minSize;
    }

    public void setMinSize(float minSize) {
        this.minSize = minSize;
    }

    public float getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(float maxSize) {
        this.maxSize = maxSize;
    }

    public String getOrientation() {
        return orientation;
    }

    private void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public void setStatusListener(StatusListener statusListener) {
        this.statusListener = statusListener;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int dir) {
        this.direction = dir;
        setOrientation(EqualizerUtil.orientation(context, dir));
    }

    public void setExpandedAtStart(boolean expandedAtStart) {
        if (expandedAtStart) {
            EqualizerUtil.manuelExpand(this);
        } else {
            EqualizerUtil.manuelCollapse(this);
        }
    }

    public void autoToggle() {
        EqualizerUtil.getToggle(this);
        if (statusListener != null) {
            statusListener.status(isExpanded());
        }
    }

    public void collapse() {
        EqualizerUtil.getCollapsed(this);
        if (statusListener != null) {
            statusListener.status(false);
        }
    }

    public void expand() {
        EqualizerUtil.getExpand(this);
        if (statusListener != null) {
            statusListener.status(true);
        }
    }

    private void initSwipeListener() {
        this.setOnTouchListener(new OnSwipeTouchListener(context) {
            public void onSwipeTop() {
                if (direction == 1 || direction == 2) {
                    autoToggle();
                }
            }

            public void onSwipeRight() {
                if (direction == 3 || direction == 4) {
                    autoToggle();
                }
            }

            public void onSwipeLeft() {
                if (direction == 3 || direction == 4) {
                    autoToggle();
                }
            }

            public void onSwipeBottom() {
                if (direction == 1 || direction == 2) {
                    autoToggle();
                }
            }

        });
//
//        this.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                autoToggle();
//            }
//        });
    }
}


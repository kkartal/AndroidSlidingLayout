package com.coffeebreakcodes.equalizerlayout;


import android.content.Context;
import android.graphics.Path;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * coffeebreakcodes.com - 2017
 */

public class EqualizerLayout extends RelativeLayout {

    private boolean isExpanded = true;
    private float duration = 300;
    private float minSize = 50;
    private float maxSize = 0;
    private int direction = 1;
    private Context context;

    private StatusListener statusListener;

    private float dx;
    private float dy;
    private float x1;
    private float x2;
    private float y1;
    private float y2;

    private String orientation;

    public EqualizerLayout(Context context) {
        super(context);
        this.context = context;
    }

    public EqualizerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public EqualizerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
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

    public void hasSwipeListener(boolean isSwipeEnabled) {
        if (isSwipeEnabled) {
            this.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            x1 = view.getX();
                            y1 = view.getY();
                            if (direction == SlidingCase.EXPAND_DOWN ||
                                    direction == SlidingCase.EXPAND_UP) {
                                dy = view.getY() - motionEvent.getRawY();
                            } else {
                                dx = view.getX() - motionEvent.getRawX();
                            }
                            break;

                        case MotionEvent.ACTION_MOVE:
                            if (direction == SlidingCase.EXPAND_DOWN ||
                                    direction == SlidingCase.EXPAND_UP) {
                                if (Math.abs(getTranslationY()) >=
                                        Math.abs(EqualizerUtil.getExpandValue(
                                                EqualizerLayout.this)) &&
                                        Math.abs(getTranslationY()) <=
                                                Math.abs(EqualizerUtil.getCollapseValue(
                                                        EqualizerLayout.this))) {
                                    view.animate()
                                            .y(motionEvent.getRawY() + dy)
                                            .setDuration(0)
                                            .start();
                                }

                            } else {
                                if (Math.abs(getTranslationX()) >=
                                        Math.abs(EqualizerUtil.getExpandValue(
                                                EqualizerLayout.this)) &&
                                        Math.abs(getTranslationX()) <=
                                                Math.abs(EqualizerUtil.getCollapseValue(
                                                        EqualizerLayout.this))) {
                                    view.animate()
                                            .x(motionEvent.getRawX() + dx)
                                            .setDuration(0)
                                            .start();
                                }

                            }
                            break;

                        case MotionEvent.ACTION_UP:
                            x2 = view.getX();
                            y2 = view.getY();

                            float deltaX = x1 - x2;
                            float deltaY = y1 - y2;

                            if (direction == SlidingCase.EXPAND_DOWN ||
                                    direction == SlidingCase.EXPAND_UP) {
                                if (deltaY > 0) {
                                    if (direction == SlidingCase.EXPAND_UP) {
                                        expand();
                                    } else {
                                        collapse();
                                    }
                                } else {
                                    if (direction == SlidingCase.EXPAND_UP) {
                                        collapse();
                                    } else {
                                        expand();
                                    }
                                }

                            } else {
                                if (deltaX > 0) {
                                    if (direction == SlidingCase.EXPAND_LEFT) {
                                        expand();
                                    } else {
                                        collapse();
                                    }
                                } else {
                                    if (direction == SlidingCase.EXPAND_LEFT) {
                                        collapse();
                                    } else {
                                        expand();
                                    }
                                }
                            }
                            return true;

                        default:
                            return false;
                    }
                    return true;
                }
            });
        } else {
            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    autoToggle();
                }
            });
        }
    }
}


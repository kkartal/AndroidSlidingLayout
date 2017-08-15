package com.coffeebreakcodes.slidinglayout;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Log;

/**
 * coffeebreakcodes.com - 2017
 */

public class SlidingUtil {

    private static final String TAG = "EqualizerLayoutError";

    static void getToggle(SlidingLayout slidingLayout) {

        if (slidingLayout.isExpanded()) {
            ObjectAnimator oa = ObjectAnimator.ofFloat(
                    slidingLayout, slidingLayout.getOrientation(),
                    getCollapseValue(slidingLayout));
            oa.setDuration((long) slidingLayout.getDuration());
            oa.start();
            slidingLayout.setExpanded(false);

        } else {
            ObjectAnimator oa = ObjectAnimator.ofFloat(
                    slidingLayout, slidingLayout.getOrientation(),
                    getExpandValue(slidingLayout));
            oa.setDuration((long) slidingLayout.getDuration());
            oa.start();
            slidingLayout.setExpanded(true);
        }
    }

    static void getCollapsed(final SlidingLayout slidingLayout) {
//        if (equalizerLayout.isExpanded()) {
            slidingLayout.post(new Runnable() {
                @Override
                public void run() {
                    ObjectAnimator oa = ObjectAnimator.ofFloat(
                            slidingLayout, slidingLayout.getOrientation(),
                            getCollapseValue(slidingLayout));
                    oa.setDuration((long) slidingLayout.getDuration());
                    oa.start();
                    slidingLayout.setExpanded(false);

                }
            });
//        }
    }

    static void getExpand(final SlidingLayout slidingLayout) {
//        if (!equalizerLayout.isExpanded()) {
            slidingLayout.post(new Runnable() {
                @Override
                public void run() {
                    ObjectAnimator oa = ObjectAnimator.ofFloat(
                            slidingLayout, slidingLayout.getOrientation(), getExpandValue(slidingLayout));
                    oa.setDuration((long) slidingLayout.getDuration());
                    oa.start();
                    slidingLayout.setExpanded(true);
                }
            });

//        }
    }

    static void manuelCollapse(final SlidingLayout slidingLayout) {
        slidingLayout.post(new Runnable() {
            @Override
            public void run() {
                if (slidingLayout.getDirection() == 1 ||
                        slidingLayout.getDirection() == 2) {
                    float y = getCollapseValue(slidingLayout);
                    slidingLayout.setTranslationY(y);

                } else if (slidingLayout.getDirection() == 3 ||
                        slidingLayout.getDirection() == 4) {
                    float x = getCollapseValue(slidingLayout);
                    slidingLayout.setTranslationX(x);
                }

                slidingLayout.setExpanded(false);
            }
        });
    }

    static void manuelExpand(final SlidingLayout slidingLayout) {
        slidingLayout.post(new Runnable() {
            @Override
            public void run() {
                if (slidingLayout.getDirection() == 1 ||
                        slidingLayout.getDirection() == 2) {
                    float y = getExpandValue(slidingLayout);
                    slidingLayout.setTranslationY(y);

                } else if (slidingLayout.getDirection() == 3 ||
                        slidingLayout.getDirection() == 4) {
                    float x = getExpandValue(slidingLayout);
                    slidingLayout.setTranslationX(x);
                }
                slidingLayout.setExpanded(true);

            }

        });
    }

    static String orientation(Context context, int dir) {
        if (dir == 1 ||
                dir == 2) {
            return SlidingCase.dimY;
        } else if (dir == 3 ||
                dir == 4) {
            return SlidingCase.dimX;
        } else {
            Log.e(TAG, context.getResources().getString(R.string.error_invalid_direction));
            return SlidingCase.dimY;
        }
    }

    static float getCollapseValue(SlidingLayout slidingLayout) {
        float val = 0;
        int dir = slidingLayout.getDirection();
        if (dir == 1) {
            val = (slidingLayout.getHeight() - slidingLayout.getMinSize());
        } else if (dir == 2) {
            val = -(slidingLayout.getHeight() - slidingLayout.getMinSize());
        } else if (dir == 3) {
            val = (slidingLayout.getWidth() - slidingLayout.getMinSize());
        } else if (dir == 4) {
            val = -(slidingLayout.getWidth() - slidingLayout.getMinSize());
        }
        return val;
    }

    static float getExpandValue(SlidingLayout slidingLayout) {
        float val = 0;
        int dir = slidingLayout.getDirection();

        float maxHeight = slidingLayout.getHeight();
        float maxWidth = slidingLayout.getWidth();

        if (slidingLayout.getMaxSize() > 0) {
            maxHeight = slidingLayout.getMaxSize();
            maxWidth = slidingLayout.getMaxSize();
        }

        if (dir == 1) {
            val = (slidingLayout.getHeight() - maxHeight);
        } else if (dir == 2) {
            val = -(slidingLayout.getHeight() - maxHeight);
        } else if (dir == 3) {
            val = (slidingLayout.getWidth() - maxWidth);
        } else if (dir == 4) {
            val = -(slidingLayout.getWidth() - maxWidth);
        }

        return val;
    }
}

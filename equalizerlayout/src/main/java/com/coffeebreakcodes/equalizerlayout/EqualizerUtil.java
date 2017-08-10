package com.coffeebreakcodes.equalizerlayout;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Log;

/**
 * coffeebreakcodes.com - 2017
 */

public class EqualizerUtil {

    private static final String TAG = "EqualizerLayoutError";

    static void getToggle(EqualizerLayout equalizerLayout) {

        if (equalizerLayout.isExpanded()) {
            ObjectAnimator oa = ObjectAnimator.ofFloat(
                    equalizerLayout, equalizerLayout.getOrientation(),
                    getCollapseValue(equalizerLayout));
            oa.setDuration((long) equalizerLayout.getDuration());
            oa.start();
            equalizerLayout.setExpanded(false);

        } else {
            ObjectAnimator oa = ObjectAnimator.ofFloat(
                    equalizerLayout, equalizerLayout.getOrientation(),
                    getExpandValue(equalizerLayout));
            oa.setDuration((long) equalizerLayout.getDuration());
            oa.start();
            equalizerLayout.setExpanded(true);
        }
    }

    static void getCollapsed(final EqualizerLayout equalizerLayout) {
//        if (equalizerLayout.isExpanded()) {
            equalizerLayout.post(new Runnable() {
                @Override
                public void run() {
                    ObjectAnimator oa = ObjectAnimator.ofFloat(
                            equalizerLayout, equalizerLayout.getOrientation(),
                            getCollapseValue(equalizerLayout));
                    oa.setDuration((long) equalizerLayout.getDuration());
                    oa.start();
                    equalizerLayout.setExpanded(false);

                }
            });
//        }
    }

    static void getExpand(final EqualizerLayout equalizerLayout) {
//        if (!equalizerLayout.isExpanded()) {
            equalizerLayout.post(new Runnable() {
                @Override
                public void run() {
                    ObjectAnimator oa = ObjectAnimator.ofFloat(
                            equalizerLayout, equalizerLayout.getOrientation(), getExpandValue(equalizerLayout));
                    oa.setDuration((long) equalizerLayout.getDuration());
                    oa.start();
                    equalizerLayout.setExpanded(true);
                }
            });

//        }
    }

    static void manuelCollapse(final EqualizerLayout equalizerLayout) {
        equalizerLayout.post(new Runnable() {
            @Override
            public void run() {
                if (equalizerLayout.getDirection() == 1 ||
                        equalizerLayout.getDirection() == 2) {
                    float y = getCollapseValue(equalizerLayout);
                    equalizerLayout.setTranslationY(y);

                } else if (equalizerLayout.getDirection() == 3 ||
                        equalizerLayout.getDirection() == 4) {
                    float x = getCollapseValue(equalizerLayout);
                    equalizerLayout.setTranslationX(x);
                }

                equalizerLayout.setExpanded(false);
            }
        });
    }

    static void manuelExpand(final EqualizerLayout equalizerLayout) {
        equalizerLayout.post(new Runnable() {
            @Override
            public void run() {
                if (equalizerLayout.getDirection() == 1 ||
                        equalizerLayout.getDirection() == 2) {
                    float y = getExpandValue(equalizerLayout);
                    equalizerLayout.setTranslationY(y);

                } else if (equalizerLayout.getDirection() == 3 ||
                        equalizerLayout.getDirection() == 4) {
                    float x = getExpandValue(equalizerLayout);
                    equalizerLayout.setTranslationX(x);
                }
                equalizerLayout.setExpanded(true);

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

    static float getCollapseValue(EqualizerLayout equalizerLayout) {
        float val = 0;
        int dir = equalizerLayout.getDirection();
        if (dir == 1) {
            val = (equalizerLayout.getHeight() - equalizerLayout.getMinSize());
        } else if (dir == 2) {
            val = -(equalizerLayout.getHeight() - equalizerLayout.getMinSize());
        } else if (dir == 3) {
            val = (equalizerLayout.getWidth() - equalizerLayout.getMinSize());
        } else if (dir == 4) {
            val = -(equalizerLayout.getWidth() - equalizerLayout.getMinSize());
        }
        return val;
    }

    static float getExpandValue(EqualizerLayout equalizerLayout) {
        float val = 0;
        int dir = equalizerLayout.getDirection();

        float maxHeight = equalizerLayout.getHeight();
        float maxWidth = equalizerLayout.getWidth();

        if (equalizerLayout.getMaxSize() > 0) {
            maxHeight = equalizerLayout.getMaxSize();
            maxWidth = equalizerLayout.getMaxSize();
        }

        if (dir == 1) {
            val = (equalizerLayout.getHeight() - maxHeight);
        } else if (dir == 2) {
            val = -(equalizerLayout.getHeight() - maxHeight);
        } else if (dir == 3) {
            val = (equalizerLayout.getWidth() - maxWidth);
        } else if (dir == 4) {
            val = -(equalizerLayout.getWidth() - maxWidth);
        }

        return val;
    }
}

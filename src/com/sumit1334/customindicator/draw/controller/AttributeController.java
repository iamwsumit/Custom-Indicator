package com.sumit1334.customindicator.draw.controller;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.sumit1334.customindicator.animation.type.AnimationType;
import com.sumit1334.customindicator.animation.type.BaseAnimation;
import com.sumit1334.customindicator.animation.type.ColorAnimation;
import com.sumit1334.customindicator.animation.type.FillAnimation;
import com.sumit1334.customindicator.animation.type.ScaleAnimation;
import com.sumit1334.customindicator.draw.data.Indicator;
import com.sumit1334.customindicator.draw.data.Orientation;
import com.sumit1334.customindicator.draw.data.RtlMode;
import com.sumit1334.customindicator.utils.DensityUtils;

public class AttributeController {

    private Indicator indicator;

    private static final int DEFAULT_IDLE_DURATION = 3000;

    public AttributeController(@NonNull Indicator indicator) {
        this.indicator = indicator;
    }

    public void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        initCountAttribute();
        initColorAttribute();
        initAnimationAttribute();
        initSizeAttribute();
    }

    private void initCountAttribute() {
        int viewPagerId = View.NO_ID;
        boolean autoVisibility = true;
        boolean dynamicCount = false;
        int count = Indicator.COUNT_NONE;

        if (count == Indicator.COUNT_NONE) {
            count = Indicator.DEFAULT_COUNT;
        }

        int position = 0;
        if (position < 0) {
            position = 0;
        } else if (count > 0 && position > count - 1) {
            position = count - 1;
        }

        indicator.setViewPagerId(viewPagerId);
        indicator.setAutoVisibility(autoVisibility);
        indicator.setDynamicCount(dynamicCount);
        indicator.setCount(count);

        indicator.setSelectedPosition(position);
        indicator.setSelectingPosition(position);
        indicator.setLastSelectedPosition(position);
    }

    private void initColorAttribute() {
        int unselectedColor = Color.parseColor(ColorAnimation.DEFAULT_UNSELECTED_COLOR);
        int selectedColor =  Color.parseColor(ColorAnimation.DEFAULT_SELECTED_COLOR);

        indicator.setUnselectedColor(unselectedColor);
        indicator.setSelectedColor(selectedColor);
    }

    private void initAnimationAttribute() {
        boolean interactiveAnimation = false;
        long animationDuration = (long) BaseAnimation.DEFAULT_ANIMATION_TIME;
        if (animationDuration < 0) {
            animationDuration = 0;
        }

        int animIndex = AnimationType.NONE.ordinal();
        AnimationType animationType = getAnimationType(animIndex);

        int rtlIndex = RtlMode.Off.ordinal();
        RtlMode rtlMode = getRtlMode(rtlIndex);

        boolean fadeOnIdle = false;
        long idleDuration = (long) DEFAULT_IDLE_DURATION;

        indicator.setAnimationDuration(animationDuration);
        indicator.setInteractiveAnimation(interactiveAnimation);
        indicator.setAnimationType(animationType);
        indicator.setRtlMode(rtlMode);
        indicator.setFadeOnIdle(fadeOnIdle);
        indicator.setIdleDuration(idleDuration);
    }

    private void initSizeAttribute() {
        int orientationIndex =  Orientation.HORIZONTAL.ordinal();
        Orientation orientation;

        if (orientationIndex == 0) {
            orientation = Orientation.HORIZONTAL;
        } else {
            orientation = Orientation.VERTICAL;
        }

        int radius = (int) DensityUtils.dpToPx(Indicator.DEFAULT_RADIUS_DP);
        if (radius < 0) {
            radius = 0;
        }

        int padding = (int) DensityUtils.dpToPx(Indicator.DEFAULT_PADDING_DP);
        if (padding < 0) {
            padding = 0;
        }

        float scaleFactor = ScaleAnimation.DEFAULT_SCALE_FACTOR;
        if (scaleFactor < ScaleAnimation.MIN_SCALE_FACTOR) {
            scaleFactor = ScaleAnimation.MIN_SCALE_FACTOR;

        } else if (scaleFactor > ScaleAnimation.MAX_SCALE_FACTOR) {
            scaleFactor = ScaleAnimation.MAX_SCALE_FACTOR;
        }

        int stroke = (int) DensityUtils.dpToPx(FillAnimation.DEFAULT_STROKE_DP);
        if (stroke > radius) {
            stroke = radius;
        }

        if (indicator.getAnimationType() != AnimationType.FILL) {
            stroke = 0;
        }

        indicator.setRadius(radius);
        indicator.setOrientation(orientation);
        indicator.setPadding(padding);
        indicator.setScaleFactor(scaleFactor);
        indicator.setStroke(stroke);
    }

    private AnimationType getAnimationType(int index) {
        switch (index) {
            case 0:
                return AnimationType.NONE;
            case 1:
                return AnimationType.COLOR;
            case 2:
                return AnimationType.SCALE;
            case 3:
                return AnimationType.WORM;
            case 4:
                return AnimationType.SLIDE;
            case 5:
                return AnimationType.FILL;
            case 6:
                return AnimationType.THIN_WORM;
            case 7:
                return AnimationType.DROP;
            case 8:
                return AnimationType.SWAP;
            case 9:
                return AnimationType.SCALE_DOWN;
        }

        return AnimationType.NONE;
    }

    private RtlMode getRtlMode(int index) {
        switch (index) {
            case 0:
                return RtlMode.On;
            case 1:
                return RtlMode.Off;
            case 2:
                return RtlMode.Auto;
        }

        return RtlMode.Auto;
    }
}

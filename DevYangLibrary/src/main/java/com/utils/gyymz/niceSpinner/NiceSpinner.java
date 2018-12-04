package com.utils.gyymz.niceSpinner;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lsjr.net.R;
import com.utils.gyymz.utils.UIUtils;

/*
 * Copyright (C) 2015 Angelo Marchesin.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class NiceSpinner extends LinearLayout {
    private static final int MAX_LEVEL = 10000;
    private View rootViewDrop;
    private TextView selectTv;
    private ImageView selectIg;
    private String titleText;
    private Drawable downImag;
    private Drawable upImage;
    private boolean isArrowHidden;

    public NiceSpinner(Context context) {
        this(context, null);
    }

    public NiceSpinner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NiceSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypedArray(context, attrs);
    }


    public void initTypedArray(Context context, @Nullable AttributeSet attrs) {
        if (attrs != null) {// 得到自定义属性
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.NiceSpinner);
            downImag = ta.getDrawable(R.styleable.NiceSpinner_showDorpUpImage);
            upImage = ta.getDrawable(R.styleable.NiceSpinner_showDropDownImage);
            titleText = ta.getString(R.styleable.NiceSpinner_showTv);
            isArrowHidden = ta.getBoolean(R.styleable.NiceSpinner_hideArrow, false);
            ta.recycle();
        }
        init();
    }


    private void init() {
        rootViewDrop = UIUtils.inflate(R.layout.view_drop_down);
        selectTv = rootViewDrop.findViewById(R.id.selected_stand_status);
        if (titleText != null) {
            selectTv.setText(titleText);
        }
        selectIg = rootViewDrop.findViewById(R.id.chevron_image);
        addView(rootViewDrop);
    }

    public ImageView getSelectIg() {
        return selectIg;
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        // arrowDrawable = initArrowDrawable(arrowDrawableTint);
        //setArrowDrawableOrHide(arrowDrawable);
    }

    public void setSelectTv(String title) {
        this.selectTv.setText(title);
    }
    //    private Drawable initArrowDrawable(int drawableTint) {
//        Drawable drawable = ContextCompat.getDrawable(getContext(), arrowDrawableResId);
//        if (drawable != null) {
//            drawable = DrawableCompat.wrap(drawable);
//            if (drawableTint != Integer.MAX_VALUE && drawableTint != 0) {
//                DrawableCompat.setTint(drawable, drawableTint);
//            }
//        }
//        return drawable;
//    }


    private int getDefaultTextColor(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme()
                .resolveAttribute(android.R.attr.textColorPrimary, typedValue, true);
        TypedArray typedArray = context.obtainStyledAttributes(typedValue.data,
                new int[]{android.R.attr.textColorPrimary});
        int defaultTextColor = typedArray.getColor(0, Color.BLACK);
        typedArray.recycle();
        return defaultTextColor;
    }


    private boolean isShowing = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isEnabled() && event.getAction() == MotionEvent.ACTION_UP) {
            if (isShowing) {
                showDropDown();
            } else {
                dismissDropDown();
            }
            isShowing = !isShowing;
        }
        return super.onTouchEvent(event);
    }

    private void animateArrow(boolean shouldRotateUp) {
        int start = shouldRotateUp ? 0 : MAX_LEVEL;
        int end = shouldRotateUp ? MAX_LEVEL : 0;
        //  ObjectAnimator animator = ObjectAnimator.ofInt(arrowDrawable, "level", start, end);
        //    animator.setInterpolator(new LinearOutSlowInInterpolator());
        // animator.start();
    }

    public void dismissDropDown() {
        if (!isArrowHidden) {
            animateArrow(false);
        }
    }

    public void showDropDown() {
        if (!isArrowHidden) {
            animateArrow(true);
        }
    }


}

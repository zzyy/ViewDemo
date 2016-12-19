package com.zy.md.ui.component;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Simon on 2016/12/16.
 */

public class DragViewContainer extends FrameLayout {
    private ViewDragHelper mViewDragHelper;

    private View mDragView;

    public DragViewContainer(Context context) {
        super(context);
        init();
    }

    public DragViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public DragViewContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mViewDragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == mDragView;
            }

            @Override
            public int getViewVerticalDragRange(View child) {
                return 1;
            }

            @Override
            public int getViewHorizontalDragRange(View child) {
                return 1;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                if (top < 0) return 0;

                if (top > getHeight() - child.getHeight()) return getHeight() - child.getHeight();

                return top;
            }


            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                if (left < 0) return 0;
                if (left > getWidth() - child.getWidth()) return getWidth() - child.getWidth();
                return left;
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 1) {
            throw new IllegalStateException("必须有且仅有一个子view");
        }
        mDragView = getChildAt(0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }
}

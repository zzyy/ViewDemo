package com.simon.example.viewdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Simon on 2016/10/7.
 */

public class DragHelperView extends ViewGroup {
    // 速度检测的最小速度
    private static final int MIN_FLING_VELOCITY = 400;  // dip per second
    private static final float TOUCH_SLOP_SENSITIVITY = 1.f;

    View mDragView;

    boolean mInLayout = false;
    ViewDragCallback mBottomCallback;
    ViewDragHelper mBottomDragger;

    public DragHelperView(Context context) {
        this(context, null);
    }

    public DragHelperView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragHelperView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final float density = getResources().getDisplayMetrics().density;

        final float minVel = MIN_FLING_VELOCITY * density;

        mBottomCallback = new ViewDragCallback();
        mBottomDragger = ViewDragHelper.create(this, TOUCH_SLOP_SENSITIVITY, mBottomCallback);
        mBottomDragger.setEdgeTrackingEnabled( ViewDragHelper.EDGE_BOTTOM );
        mBottomDragger.setMinVelocity( minVel );

    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        super.addView(child, params);

    }

    boolean isContentView(View view) {
        return ((LayoutParams) view.getLayoutParams()).gravity == Gravity.NO_GRAVITY;
    }

    boolean isDragView(View view) {
        final int gravity = ((LayoutParams) view.getLayoutParams()).gravity;
        final int absGravity = GravityCompat.getAbsoluteGravity(gravity, ViewCompat.getLayoutDirection(view));

        return ((LayoutParams) view.getLayoutParams()).gravity != Gravity.NO_GRAVITY;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final boolean interceptForDrag = mBottomDragger.shouldInterceptTouchEvent( ev );


        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(widthSize, heightSize);

        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);

            if (child.getVisibility() == GONE) {
                continue;
            }

            final LayoutParams lp = (LayoutParams) child.getLayoutParams();

            if (isContentView(child)) {
                final int contentWidhtSpec = MeasureSpec.makeMeasureSpec(widthSize - lp.leftMargin - lp.rightMargin, MeasureSpec.EXACTLY);
                final int contentHeightSpec = MeasureSpec.makeMeasureSpec(heightSize - lp.topMargin - lp.bottomMargin, MeasureSpec.EXACTLY);
                child.measure(contentWidhtSpec, contentHeightSpec);
            } else if (isDragView(child)) {
                final int dragWidthSpec = getChildMeasureSpec(widthMeasureSpec, lp.leftMargin + lp.rightMargin, lp.width);
                final int dragHeightSpec = getChildMeasureSpec(heightMeasureSpec, lp.topMargin + lp.bottomMargin, lp.height);
                child.measure(dragWidthSpec, dragHeightSpec);
            } else {
                throw new IllegalStateException("测量出错, 不应该啊 - - ");
            }

        }


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mInLayout = true;
        final int height = b - t;

        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);

            if (child.getVisibility() == GONE) {
                continue;
            }

            final LayoutParams lp = (LayoutParams) child.getLayoutParams();

            if (isContentView(child)) {
                child.layout(lp.leftMargin, lp.topMargin, lp.leftMargin + lp.width, lp.topMargin + lp.height);
            } else {
                final int childWidth = child.getMeasuredWidth();
                final int childHeight = child.getMeasuredHeight();

                int childTop = 0;
                float newOffset = 1;
                if (lp.gravity == Gravity.BOTTOM) {
                    childTop = height - (int) (childHeight * lp.onScreen);
                    newOffset = (height - childTop) / childWidth;
                }

                final boolean changeOffset = newOffset != lp.onScreen;

                child.layout(lp.leftMargin, childTop, lp.leftMargin + childWidth, childTop + childHeight );
            }
        }

        mInLayout = false;
    }

    @Override
    public void requestLayout() {
       if (!mInLayout){
           super.requestLayout();
       }
    }

    private class ViewDragCallback extends ViewDragHelper.Callback{

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return false;
        }
    }

    public static class LayoutParams extends MarginLayoutParams {
        public int gravity = Gravity.NO_GRAVITY;
        public float onScreen = 0f;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);

            TypedArray a = c.obtainStyledAttributes(attrs, new int[]{android.R.attr.layout_gravity});

            this.gravity = a.getInt(0, Gravity.NO_GRAVITY);

            a.recycle();

        }

        public LayoutParams(int width, int height) {
            super(width, height);
            this.gravity = Gravity.NO_GRAVITY;
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }
}

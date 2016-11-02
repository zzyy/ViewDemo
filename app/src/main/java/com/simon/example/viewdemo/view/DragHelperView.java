package com.simon.example.viewdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.simon.example.viewdemo.R;

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
        mBottomDragger.setEdgeTrackingEnabled( ViewDragHelper.EDGE_BOTTOM | ViewDragHelper.EDGE_LEFT);
        mBottomDragger.setMinVelocity( minVel );
        mBottomCallback.setDragger(mBottomDragger);

    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        super.addView(child, params);

        if(isDragView(child)){
            mDragView = child;
        }

    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
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


        return interceptForDrag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mBottomDragger.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        if (mBottomDragger.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
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
                child.layout(lp.leftMargin, lp.topMargin, lp.leftMargin + child.getMeasuredWidth(), lp.topMargin + child.getMeasuredHeight());
            } else {
                final int childWidth = child.getMeasuredWidth();
                final int childHeight = child.getMeasuredHeight();

                int childTop = 0;
                float newOffset = 1;
//                if (lp.gravity == Gravity.BOTTOM) {
                    childTop = height - (int) (childHeight * lp.onScreen);
                    newOffset = (height - childTop) / childWidth;
//                }

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
        ViewDragHelper mDragger;

        public void setDragger(ViewDragHelper dragger) {
            this.mDragger = dragger;
        }

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            if (isDragView(child)){
                return true;
            }
            return false;
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return isDragView(child) ? child.getHeight() : 0;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            int maxTop = getHeight();
            int minTop = getHeight() - child.getHeight();
            if (top>maxTop){
                return maxTop;
            }else if (top < minTop){
                return minTop;
            }
            return top;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            Log.w("zy", "onViewReleased");
            int top = getHeight();
            int releasedChildHeight = releasedChild.getHeight();

            int midTop = getHeight() - (int)(releasedChildHeight/2);

            if (yvel > 0 || releasedChild.getTop() >= midTop ){
                top = getHeight();
            }else {
                top = getHeight() - releasedChildHeight;
            }

            mDragger.settleCapturedViewAt( releasedChild.getLeft(), top );

            invalidate();
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);

        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            Log.w("zy", "onEdgeDragStarted mDragView="+ mDragView);
            mDragger.captureChildView(mDragView, pointerId);
        }

        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            Log.w("zy", "onEdgeTouched");
        }
    }

    public static class LayoutParams extends MarginLayoutParams {
        public int gravity = Gravity.NO_GRAVITY;
        public float onScreen = 0f;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);

            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.DragHelperView_LayoutParams);

            this.gravity = a.getInt(R.styleable.DragHelperView_LayoutParams_gravity, Gravity.NO_GRAVITY);

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

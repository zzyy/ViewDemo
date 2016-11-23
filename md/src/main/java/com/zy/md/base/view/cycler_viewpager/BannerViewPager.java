package com.zy.md.base.view.cycler_viewpager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Simon on 2016/11/6.
 */

public class BannerViewPager extends ViewPager {
    private final int MSG_NEXT_PAGE = 0;
    private final int STAY_TIME = 5 * 1000;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MSG_NEXT_PAGE) {
                gotoNextPage();
                startTimer();
            }
        }
    };

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BannerViewPager(Context context) {
        this(context, null);
    }


    private void gotoNextPage() {
        if (getAdapter() == null) return;
        final int currPos = getCurrentItem();
        final int nextPos = currPos + 1 < getAdapter().getCount() ? currPos + 1 : 0;
        setCurrentItem(nextPos, true);

    }

    private void startTimer() {
        mHandler.removeMessages(MSG_NEXT_PAGE);
        mHandler.sendEmptyMessageDelayed(MSG_NEXT_PAGE, STAY_TIME);
    }

    private void stopTimer() {
        mHandler.removeMessages(MSG_NEXT_PAGE);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startTimer();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopTimer();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                stopTimer();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                startTimer();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}

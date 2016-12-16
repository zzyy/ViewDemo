package com.zy.md.ui.component;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Simon on 2016/12/16.
 */

public class DragViewContainer extends FrameLayout {
    private ViewDragHelper mViewDragHelper;

    public DragViewContainer(Context context) {
        super(context);
    }

    public DragViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragViewContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        mViewDragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return false;
            }
        });
    }
}

package com.zy.md.welcome;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by Simon on 2016/11/2.
 */

public class WelcomeVideoView extends VideoView {
    public WelcomeVideoView(Context context) {
        super(context);
    }

    public WelcomeVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WelcomeVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize( widthMeasureSpec );
        setMeasuredDimension( width, (int) (width/0.56f));
    }
}

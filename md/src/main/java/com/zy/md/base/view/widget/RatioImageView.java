package com.zy.md.base.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Simon on 2016/11/28.
 */

public class RatioImageView extends ImageView {
    private int mOriginalWidth;
    private int mOriginalHeight;

    public RatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOriginalSize(int originalWidth, int originalHeight) {
        this.mOriginalWidth = originalWidth;
        this.mOriginalHeight = originalHeight;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mOriginalWidth > 0 && mOriginalHeight > 0) {

            int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
            if (measureWidth > 0) {

                float ratio = mOriginalWidth * 0.1f / mOriginalHeight;

                int height = (int) (measureWidth * 0.1f / ratio);

                setMeasuredDimension(measureWidth, height);

                return;
            }

        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}

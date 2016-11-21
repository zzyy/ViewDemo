package com.zy.md.main.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zy.md.R;

/**
 * Created by Simon on 2016/11/21.
 */

public class SheetDialog extends BottomSheetDialog {
    public SheetDialog(@NonNull Context context) {
        super(context);
    }

    private BottomSheetBehavior mBottomSheetBehavior;

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN){
                dismiss();
                BottomSheetBehavior.from(bottomSheet).setState( BottomSheetBehavior.STATE_COLLAPSED );
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void setMaxHeight(int maxHeight){
        Window window = getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, maxHeight);
        window.setGravity(Gravity.BOTTOM);
    }

    public void setPeekHeight(int peekHeight){
        getBottomSheetBehavior().setPeekHeight( peekHeight );
    }

    private BottomSheetBehavior getBottomSheetBehavior(){
        if (mBottomSheetBehavior == null){
            View v = getWindow().findViewById(android.support.design.R.id.design_bottom_sheet);
            mBottomSheetBehavior = BottomSheetBehavior.from(v);
        }
        return mBottomSheetBehavior;
    }

    protected void setBottomSheetCallback() {
        getBottomSheetBehavior().setBottomSheetCallback( mBottomSheetCallback );
    }
}

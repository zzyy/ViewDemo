package com.zy.md.utils;

import android.view.View;

/**
 * Created by Simon on 2016/11/30.
 */

public class ActivityHelper {
    private static final int SYSTEM_UI_BASE_VISIBILITY = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

    private static final int SYSTEM_UI_IMMERSIVE = View.SYSTEM_UI_FLAG_IMMERSIVE
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN;


    /**
     * 显示导航栏和状态栏
     */
    public static void showSystemUi(View view) {

        view.setSystemUiVisibility(SYSTEM_UI_BASE_VISIBILITY);
    }

    /**
     * 隐藏导航栏和状态栏
     */
    public static void hideSystemUi(View view) {
        view.setSystemUiVisibility(SYSTEM_UI_BASE_VISIBILITY | SYSTEM_UI_IMMERSIVE);
    }

}

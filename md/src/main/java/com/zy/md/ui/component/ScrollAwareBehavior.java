package com.zy.md.ui.component;

import android.support.design.widget.CoordinatorLayout;
import android.view.View;

/**
 * Created by Simon on 2016/12/16.
 */

public class ScrollAwareBehavior extends CoordinatorLayout.Behavior<View> {

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return super.layoutDependsOn(parent, child, dependency);
    }
}

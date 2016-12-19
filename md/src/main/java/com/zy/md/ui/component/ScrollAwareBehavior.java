package com.zy.md.ui.component;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.wingsofts.byeburgernavigationview.ByeBurgerBottomBehavior;

/**
 * Created by Simon on 2016/12/16.
 */

public class ScrollAwareBehavior extends ByeBurgerBottomBehavior {


    public ScrollAwareBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        boolean nestedVertical = nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
        if (nestedVertical && child instanceof FloatingActionsMenu){
            ((FloatingActionsMenu) child).collapse();
        }

        return nestedVertical;
    }
}

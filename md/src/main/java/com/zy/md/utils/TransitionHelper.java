package com.zy.md.utils;

/**
 * Created by Simon on 2016/11/30.
 */

import android.app.Activity;
import android.support.v4.util.Pair;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 构建页面跳转动画的辅助工具类
 */
public class TransitionHelper {

    /**
     * 创建包含状态栏 导航栏的activity过度动画, 避免在使用transition时, 状态栏和导航栏闪烁
     * @param activity The activity used as start for the transition.
     * @param includeStatusBar If false, the status bar will not be added as the transition
     *        participant.
     * @return All transition participants.
     */
    public static Pair<View, String>[] createSafeTransitionParticipants(Activity activity, boolean includeStatusBar, Pair... otherParticipants){
        View decore = activity.getWindow().getDecorView();
        View statusBar = null;
        if (includeStatusBar){
            statusBar = decore.findViewById(android.R.id.statusBarBackground);
        }

        View navBar = decore.findViewById(android.R.id.navigationBarBackground);

        List<Pair> participants = new ArrayList<>(3);

        addNonNullViewToTransitionParticipants(statusBar, participants);
        addNonNullViewToTransitionParticipants(navBar, participants);

        // only add transition participants if there's at least one none-null element
        if (otherParticipants != null && !(otherParticipants.length == 1
                && otherParticipants[0] == null)) {
            participants.addAll(Arrays.asList(otherParticipants));
        }

        return participants.toArray( new Pair[participants.size()] );
    }



    private static void addNonNullViewToTransitionParticipants(View view, List<Pair> participants) {
        if (view == null) {
            return;
        }
        participants.add(new android.support.v4.util.Pair<>(view, view.getTransitionName()));
    }
}

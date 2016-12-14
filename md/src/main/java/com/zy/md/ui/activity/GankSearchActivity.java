package com.zy.md.ui.activity;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.zy.md.R;
import com.zy.md.base.view.BaseActivity;
import com.zy.md.utils.common.MathUtils;

import butterknife.BindView;



public class GankSearchActivity extends BaseActivity {
    @BindView(R.id.search_view_container)
    View mSearchContainerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_search);

        setupTransitionAnimator();
    }

    private void setupTransitionAnimator() {

//        Transition shareTransition = getWindow().getSharedElementEnterTransition();

        Visibility enterTransition = buildEnterTransition();
        getWindow().setEnterTransition( enterTransition );
//        Transition enterTransition = getWindow().getEnterTransition();
        enterTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                playEnterRevealAnimator();

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                enterTransition.removeListener(this);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });



    }

    private void playEnterRevealAnimator(){
        final float maxRadius = MathUtils.calculateCatercorner(mSearchContainerView.getWidth(), mSearchContainerView.getHeight());

        Animator animator = ViewAnimationUtils.createCircularReveal(mSearchContainerView
                , 0, 0, 0, maxRadius);
        animator.setDuration(3000);

        animator.start();
    }

    private Visibility buildEnterTransition() {
        Fade enterTransition = new Fade();
        enterTransition.setDuration(1000);
        enterTransition.excludeTarget(android.R.id.statusBarBackground, true);
        enterTransition.excludeTarget(android.R.id.navigationBarBackground, true);
        return enterTransition;
    }
}

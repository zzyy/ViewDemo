package com.zy.md.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.zy.md.R;
import com.zy.md.base.view.BaseActivity;
import com.zy.md.utils.common.MathUtils;

import butterknife.BindView;
import butterknife.OnClick;


public class GankSearchActivity extends BaseActivity {
    private static final int TRANSITION_TIME = 1000;

    @BindView(R.id.search_view_container)
    View mSearchContainerView;
    @BindView(R.id.search_icon_view)
    View mSeachIconView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_search);

        setupTransitionAnimator();
        setupView();
    }

    private void setupView() {
        rxClick(R.id.btn_search_cancel).subscribe(aVoid -> ActivityCompat.finishAfterTransition(this));
    }


    private void setupTransitionAnimator() {
        if (Build.VERSION.SDK_INT < 21) return;
        setupEnterTransition();
        setupReturnTransition();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupEnterTransition() {
        Visibility enterTransition = buildEnterTransition();
        getWindow().setEnterTransition(enterTransition);
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


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupReturnTransition() {
        Transition returnTransition = buildReturnTransition();
        getWindow().setReturnTransition(returnTransition);
        returnTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                playReturnRevealAnimator();
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                returnTransition.removeListener(this);
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void playEnterRevealAnimator() {
        final float maxRadius = MathUtils.calculateCatercorner(mSearchContainerView.getWidth(), mSearchContainerView.getHeight());
        final float minRadius = MathUtils.calculateCatercorner(mSeachIconView.getWidth(), mSeachIconView.getHeight());
        final int[] searchIconViewLocation = new int[2];
        mSeachIconView.getLocationInWindow(searchIconViewLocation);
        Animator animator = ViewAnimationUtils.createCircularReveal(mSearchContainerView
                , searchIconViewLocation[0] + mSeachIconView.getWidth()/2, searchIconViewLocation[1]+mSeachIconView.getHeight()/2 , minRadius, maxRadius);
        animator.setDuration(TRANSITION_TIME);
        animator.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void playReturnRevealAnimator() {
        final float maxRadius = MathUtils.calculateCatercorner(mSearchContainerView.getWidth(), mSearchContainerView.getHeight());
        final float minRadius = MathUtils.calculateCatercorner(mSeachIconView.getWidth(), mSeachIconView.getHeight());
        final int[] searchIconViewLocation = new int[2];
        mSeachIconView.getLocationOnScreen(searchIconViewLocation);
        Animator animator = ViewAnimationUtils.createCircularReveal(mSearchContainerView, searchIconViewLocation[0] + mSeachIconView.getWidth()/2, searchIconViewLocation[1]+mSeachIconView.getHeight()/2, maxRadius, minRadius);
        animator.setDuration(TRANSITION_TIME);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mSearchContainerView.setVisibility(View.INVISIBLE);
            }
        });
        animator.start();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Visibility buildReturnTransition() {
        Visibility returnTransition = new Slide();
        returnTransition.setDuration(TRANSITION_TIME);
        returnTransition.excludeChildren(R.id.search_view_container, true);
        returnTransition.excludeTarget(R.id.search_view_container, true);
        returnTransition.excludeTarget(android.R.id.statusBarBackground, true);
        returnTransition.excludeTarget(android.R.id.navigationBarBackground, true);
        return returnTransition;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Visibility buildEnterTransition() {
        Slide enterTransition = new Slide();
        enterTransition.setDuration(TRANSITION_TIME);
        enterTransition.excludeChildren(R.id.search_view_container, true);
        enterTransition.excludeTarget(R.id.search_view_container, true);
        enterTransition.excludeTarget(android.R.id.statusBarBackground, true);
        enterTransition.excludeTarget(android.R.id.navigationBarBackground, true);
        return enterTransition;
    }
}

package com.zy.md.ui.activity;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.zy.md.R;
import com.zy.md.base.view.BaseActivity;

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
        int width = mSearchContainerView.getWidth();
        int height = mSearchContainerView.getHeight();

        final float maxRadius = (float) Math.sqrt( width*width + height+height  );

        Animator animator = ViewAnimationUtils.createCircularReveal(mSearchContainerView
        , 0, 0, 0, maxRadius);
        animator.setDuration(3000);

        animator.start();

    }
}

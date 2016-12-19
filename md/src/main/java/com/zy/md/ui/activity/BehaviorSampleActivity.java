package com.zy.md.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.simon.ex_recyclerview.BaseRecyclerAdapter;
import com.simon.ex_recyclerview.BaseRecyclerHolder;
import com.zy.md.R;
import com.zy.md.base.view.BaseActivity;
import com.zy.md.utils.TransitionHelper;
import com.zy.md.utils.common.DisplayUtils;
import com.zy.md.utils.common.MathUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BehaviorSampleActivity extends BaseActivity {

    @BindView(R.id.activity_behavior_sample)
    View mRootView;

    @BindView(R.id.appbar_view)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.iv_share_view)
    View mShareView;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior_sample);

        setupView();
        setupWindowAnimations();

    }

    private void setupWindowAnimations() {
        setupEnterAnimations();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupEnterAnimations() {
        Transition transition = new ChangeBounds();
        transition = TransitionHelper.excludeSystemUi( transition );
        getWindow().setSharedElementEnterTransition(transition);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                hideViews();
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                final int[] searchIconViewLocation = new int[2];
                mShareView.getLocationInWindow(searchIconViewLocation);
                final int startX = searchIconViewLocation[0] + mShareView.getWidth()/2;
                final int startY = searchIconViewLocation[1]+mShareView.getHeight()/2;
                float maxRadius = MathUtils.calculateCatercorner(DisplayUtils.getScreenWidth(BehaviorSampleActivity.this), DisplayUtils.getScreenHeight(BehaviorSampleActivity.this));
                float minRadius = MathUtils.calculateCatercorner(mShareView.getWidth(), mShareView.getHeight())/2;
                Animator animator = ViewAnimationUtils.createCircularReveal( mRootView, startX, startY, minRadius,  maxRadius*2);
                animator.setDuration(1000);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        showViews();
                    }
                });
                animator.start();
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


    void hideViews(){
        mAppBarLayout.setVisibility(View.INVISIBLE);
        mToolbar.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    void showViews(){
        mAppBarLayout.setVisibility(View.VISIBLE);
        mToolbar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void setupView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new Adapter());
    }

    static class Adapter extends BaseRecyclerAdapter<String> {
        static List<String> DATA = new ArrayList<>();

        static {
            for (int i = 0; i < 60; i++) {
                DATA.add(i + " item data");
            }
        }

        public Adapter() {
            super(DATA, R.layout.item_test_card);
        }

        public void addData(int position, String data) {
            mData.add(position, data);
            notifyItemInserted(position);
        }

        @Override
        public void onBindViewHolder(BaseRecyclerHolder holder, int position, String itemData) {
            TextView textView = holder.getView(R.id.tv_item_card);
            textView.setText(itemData);
        }

    }
}

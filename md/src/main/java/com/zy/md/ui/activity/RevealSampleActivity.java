package com.zy.md.ui.activity;

import android.animation.Animator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zy.md.R;
import com.zy.md.base.view.BaseActivity;

import butterknife.BindView;
import rx.Observable;

public class RevealSampleActivity extends BaseActivity {
    private View mDecor;

    @BindView(R.id.iv_reveal)
    ImageView mImageView;

    @BindView(R.id.fl_reveal_test)
    FrameLayout mFrameLayout;

    @BindView(R.id.tv_test1)
    TextView mTextView1;

    @BindView(R.id.tv_test2)
    TextView mTextView2;

    @BindView(R.id.fl_reveal_search)
    View mSearchRevealView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_sample);
        mDecor = getWindow().findViewById(android.R.id.content);

        mSearchRevealView.setOnTouchListener(this::playRevealAnimator);


        mImageView.setOnTouchListener((view, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP){

                final int x = (int) event.getX();
                final int y = (int) event.getY();

                Animator revealAnimator = ViewAnimationUtils.createCircularReveal( mImageView, x, y, 0, mImageView.getWidth() );
                revealAnimator.setDuration(3000);
                revealAnimator.start();
            }

            return true;
        });


        mFrameLayout.setOnTouchListener((view, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP){
                final int x = (int) event.getX();
                final int y = (int) event.getY();

                final View animatorView = mTextView1.getVisibility() == View.VISIBLE ? mTextView2 : mTextView1;
                final View hideView = mTextView1.getVisibility() == View.VISIBLE ?   mTextView1 : mTextView2;

                Animator revealAnimator = ViewAnimationUtils.createCircularReveal( animatorView, x, y, 0, animatorView.getWidth() );
                revealAnimator.setDuration(3000);
                revealAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        animatorView.setVisibility( View.VISIBLE );
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        hideView.setVisibility(View.INVISIBLE);
                        hideView.bringToFront();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                revealAnimator.start();


            }

            return true;
        });

    }


    private boolean playRevealAnimator(View view, MotionEvent event ){
        if (event.getAction() == MotionEvent.ACTION_UP){

            int width = view.getWidth();
            int height = view.getHeight();

            final float maxRadius = (float) Math.sqrt( width*width + height+height  );

            final int x = (int) event.getX();
            final int y = (int) event.getY();

            Animator revealAnimator = ViewAnimationUtils.createCircularReveal( view, x, y, 0, maxRadius);
            revealAnimator.setDuration(3000);
            revealAnimator.start();
        }

        return true;
    }

}

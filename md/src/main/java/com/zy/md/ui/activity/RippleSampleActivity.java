package com.zy.md.ui.activity;

import android.animation.Animator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.andexert.library.RippleView;
import com.zy.md.R;

import butterknife.BindView;

public class RippleSampleActivity extends AppCompatActivity {



    @BindView(R.id.ripple_view)
    RippleView mRippleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple_sample);


    }


}

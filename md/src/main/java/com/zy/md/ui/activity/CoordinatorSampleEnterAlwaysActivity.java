package com.zy.md.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.zy.md.R;
import com.zy.md.base.view.BaseActivity;

import butterknife.BindView;

public class CoordinatorSampleEnterAlwaysActivity extends BaseActivity {

    @BindView(R.id.image_view)
    View mImageView;

    @BindView(R.id.first_view)
    View mFirstView;

    @BindView(R.id.secod_view)
    View mSecondView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_enter_always_sample);

        setupView();
    }

    private void setupView() {
        rxClick(R.id.btn_exit_until_collapsed)
                .subscribe();

    }


}



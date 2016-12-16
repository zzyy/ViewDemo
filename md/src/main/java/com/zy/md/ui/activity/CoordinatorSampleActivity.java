package com.zy.md.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.zy.md.R;
import com.zy.md.base.view.BaseActivity;

import butterknife.BindView;

public class CoordinatorSampleActivity extends BaseActivity {

    @BindView(R.id.image_view)
    View mImageView;

    @BindView(R.id.first_view)
    View mFirstView;

    @BindView(R.id.secod_view)
    View mSecondView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_sample);

        setupView();
    }

    private void setupView() {
        rxClick(R.id.btn_snake_float_action_button)
                .subscribe(aVoid -> start(CoordinatorSampleSnackBarActivity.class));

        rxClick(R.id.btn_bottom_sheet)
                .subscribe(aVoid -> start(CoordinatorSampleBottomSheetActivity.class));

        rxClick(R.id.btn_exit_until_collapsed)
                .subscribe(aVoid -> start(CoordinatorSampleExitUntilCollapsedActivity.class));

        rxClick(R.id.btn_first_enter_always)
                .subscribe(aVoid -> start(CoordinatorSampleEnterAlwaysActivity.class));

        rxClick(R.id.btn_make_second_no_scroll)
                .subscribe(aVoid -> start(CoordinatorSampleNoScrollActivity.class));

        rxClick(R.id.btn_all_in_one)
                .subscribe(aVoid -> start(CoordinatorSampleAllInActivity.class));
    }


}



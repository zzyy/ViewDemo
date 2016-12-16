package com.zy.md.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.zy.md.R;
import com.zy.md.base.view.BaseActivity;
import com.zy.md.utils.common.ToastUtils;

import butterknife.BindView;

public class CoordinatorSampleSnackBarActivity extends BaseActivity {

    @BindView(R.id.activity_coordinator_sample)
    CoordinatorLayout mCoordinatorLayout;

    @BindView(R.id.image_view)
    View mImageView;

    @BindView(R.id.first_view)
    View mFirstView;

    @BindView(R.id.secod_view)
    View mSecondView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_snack_bar_sample);

        setupView();
    }

    private void setupView() {
        rxClick(R.id.snack)
                .subscribe(aVoid -> showSnack());
        rxClick(R.id.snack_with_action)
                .subscribe(aVoid -> showSnackWithAction());
    }

    void showSnack() {
        Snackbar.make(mCoordinatorLayout, "显示snackBar", Snackbar.LENGTH_SHORT).show();
    }

    void showSnackWithAction() {
        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, "显示snackBarWithAction", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getInstance(getApplication()).s("action was clicked");
            }
        });
        snackbar.show();
    }


}



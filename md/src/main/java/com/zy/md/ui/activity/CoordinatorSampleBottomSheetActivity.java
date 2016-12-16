package com.zy.md.ui.activity;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zy.md.R;
import com.zy.md.base.view.BaseActivity;
import com.zy.md.main.TestBottomSheetDialog;

import butterknife.BindView;

public class CoordinatorSampleBottomSheetActivity extends BaseActivity {

    @BindView(R.id.tv_bottom_sheet_content)
    TextView mBottomSheetContentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_sample_bottom_sheet);

        setupView();
    }

    private void setupView() {

        setupBottomSheetDialog();
        setupBottomSheet();
    }

    private void setupBottomSheetDialog() {
        rxClick(R.id.btn_show_bottom_sheet_dialog)
                .subscribe(aVoid -> {
                   new TestBottomSheetDialog(this).show();
                });
    }

    private void setupBottomSheet() {
        mBottomSheetContentTextView.setText(R.string.lorem);
        View bottomSheet = findViewById(R.id.bottom_sheet);
        final BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(bottomSheet);
        /*behavior.setHideable(true);
        behavior.setPeekHeight( 300 );
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);*/

        rxClick(R.id.btn_show_bottom_sheet)
                .subscribe(aVoid -> behavior.setState(BottomSheetBehavior.STATE_COLLAPSED));
    }
}

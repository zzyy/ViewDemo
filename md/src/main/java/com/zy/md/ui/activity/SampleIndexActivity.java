package com.zy.md.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zy.md.R;
import com.zy.md.base.view.BaseActivity;

public class SampleIndexActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_index);

        rxClick(R.id.btn_enter_tab_layout_sample)
                .subscribe(aVoid -> start(TabSampleActivity.class));
    }
}

package com.zy.md;

import android.os.Bundle;

import com.zy.md.base.view.BaseActivity;
import com.zy.md.welcome.WelcomeActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(WelcomeActivity.class);
        finish();
    }
}

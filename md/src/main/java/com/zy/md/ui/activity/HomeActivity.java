package com.zy.md.ui.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;

import com.zy.md.R;
import com.zy.md.base.view.BaseActivity;
import com.zy.md.main.SampleActivity;
import com.zy.md.ui.fragment.DouBanFragment;
import com.zy.md.ui.fragment.GankFragment;
import com.zy.md.ui.fragment.GankMeiziFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {
    private static final String FIRST_FRAGMENT_TAG = "gank";
    private static final String SECOND_FRAGMENT_TAG = "meizi";
    private static final String THIRD_FRAGMENT_TAG = "sample";

    private Map<String, Fragment> mFragments = new HashMap<>(4);

    @BindView(R.id.home_content)
    View mContentView;

    @BindView(R.id.home_drawer_nav_view)
    NavigationView mNavigationView;

    @BindView(R.id.home_drawer_layout)
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupView();
    }

    private void setupView() {
        mFragments.put(FIRST_FRAGMENT_TAG, new GankFragment());
        mFragments.put(SECOND_FRAGMENT_TAG, new GankMeiziFragment());
        mFragments.put(THIRD_FRAGMENT_TAG, new DouBanFragment());

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.home_content, mFragments.get(FIRST_FRAGMENT_TAG), FIRST_FRAGMENT_TAG)
                .hide(mFragments.get(FIRST_FRAGMENT_TAG))
                .add(R.id.home_content, mFragments.get(SECOND_FRAGMENT_TAG), SECOND_FRAGMENT_TAG)
                .hide(mFragments.get(SECOND_FRAGMENT_TAG))
                .add(R.id.home_content, mFragments.get(THIRD_FRAGMENT_TAG), THIRD_FRAGMENT_TAG)
                .hide(mFragments.get(THIRD_FRAGMENT_TAG))
                .commit();

        BottomNavigationView bottomNavigationView = $(R.id.bnv_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_android:
                    showFragment(FIRST_FRAGMENT_TAG);
                    break;
                case R.id.action_gank_meizi:
                    showFragment(SECOND_FRAGMENT_TAG);
                    break;
                case R.id.action_3:
                    showFragment(THIRD_FRAGMENT_TAG);
                    break;
            }
            return true;
        });

        bottomNavigationView.setSelected(true);

        mNavigationView.setNavigationItemSelectedListener(this::onNavigationSelected);
    }

    private boolean onNavigationSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.nav_ripple) {
            start(RippleSampleActivity.class);
        } else if (id == R.id.nav_reveal) {
            start(RevealSampleActivity.class);
        }else if (id == R.id.nav_recycler){
            start(SampleActivity.class);
        }else if (id == R.id.nav_coordinator){
            start(CoordinatorSampleActivity.class);
        }else if (id == R.id.nav_sample){
            start(SampleIndexActivity.class);
        }

        mDrawerLayout.closeDrawer( GravityCompat.START );

        return true;
    }


    private void showFragment(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment.isVisible()) {
            return;
        }

        fragmentManager.beginTransaction()
                .hide(mFragments.get(FIRST_FRAGMENT_TAG))
                .hide(mFragments.get(SECOND_FRAGMENT_TAG))
                .hide(mFragments.get(THIRD_FRAGMENT_TAG))
                .show(fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

package com.zy.md.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.zy.md.R;
import com.zy.md.base.view.BaseActivity;
import com.zy.md.ui.fragment.GankMeiziFragment;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.home_content)
    View mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();

    }

    private void initView() {
        BottomNavigationView bottomNavigationView = $(R.id.bnv_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.action_android:
                    break;

                case R.id.action_gank_meizi:
                    showMeiFragment();
                    break;

                case R.id.action_3:
                    break;
            }
            return true;
        });

    }

    private void showMeiFragment(){
        String fragmentTag = "meizi";
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag( fragmentTag );
        if (fragment == null){
            fragment = new GankMeiziFragment();
        }

        fragmentManager.beginTransaction().add(R.id.home_content, fragment, fragmentTag)
                .show(fragment)
                .commit();

    }

}

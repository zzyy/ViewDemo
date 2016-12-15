package com.zy.md.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zy.md.R;
import com.zy.md.base.view.BaseActivity;

import butterknife.BindView;

public class TabSampleActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_sample);


        Adapter adapter = new Adapter();
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);

        rxClick(R.id.btn_change_mode)
                .subscribe(aVoid -> mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE));

//        rxClick(R.id.btn_change_indicator)
//                .subscribe(aVoid -> mTabLayout.indi);
    }


    static class Adapter extends PagerAdapter{

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = new TextView(container.getContext());
            textView.setLayoutParams( new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            textView.setGravity(Gravity.CENTER);
            textView.setText("第" + position + "页");
            container.addView(textView, 0);
            return textView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "第" + position + "页";
        }
    }
}

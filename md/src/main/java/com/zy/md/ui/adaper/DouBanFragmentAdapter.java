package com.zy.md.ui.adaper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.zy.md.ui.fragment.DouBanItemFragment;

import java.util.List;

/**
 * Created by Simon on 2016/12/7.
 */

public class DouBanFragmentAdapter extends FragmentPagerAdapter {
    List<DouBanItemFragment> mData;

    public DouBanFragmentAdapter(FragmentManager fm, List<DouBanItemFragment> data) {
        super(fm);
        mData = data;
    }

    @Override
    public DouBanItemFragment getItem(int position) {
        return mData.get( position );
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getItem(position).getTitle();
    }
}

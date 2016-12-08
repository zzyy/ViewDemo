package com.zy.md.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zy.md.R;
import com.zy.md.base.view.BaseFragment;
import com.zy.md.ui.adaper.DouBanFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;

public class DouBanFragment extends BaseFragment {

    @BindArray(R.array.girl_cid)
    String[] mGirlCids;

    @BindArray(R.array.girl)
    String[] mGirlTitles;

    @BindView(R.id.tb_douban)
    TabLayout mTabLayout;

    @BindView(R.id.vp_douban_content)
    ViewPager mViewPager;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dou_ban;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mToolbar.setTitle("DouBan");

        List<DouBanItemFragment> fragmentList = new ArrayList<>();
        int N = mGirlCids.length;
        for (int i = 0; i<N; i++){
            fragmentList.add( new DouBanItemFragment( mGirlCids[i], mGirlTitles[i] ) );
        }

        DouBanFragmentAdapter adapter = new DouBanFragmentAdapter( getChildFragmentManager(), fragmentList );
        mViewPager.setAdapter( adapter );

        mTabLayout.setupWithViewPager( mViewPager );

    }
}

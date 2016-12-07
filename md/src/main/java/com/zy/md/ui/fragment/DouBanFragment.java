package com.zy.md.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zy.md.R;
import com.zy.md.base.view.BaseFragment;

import butterknife.BindArray;
import butterknife.BindView;

public class DouBanFragment extends BaseFragment {

    @BindArray(R.array.girl_cid)
    String[] mGirlCids;

    @BindArray(R.array.girl)
    String[] mGirlTitles;



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

        DouBanItemFragment fragment = DouBanItemFragment.newInstance(2, "title");

        getChildFragmentManager().beginTransaction()
                .replace(R.id.fl_content, fragment)
                .commit();
    }
}

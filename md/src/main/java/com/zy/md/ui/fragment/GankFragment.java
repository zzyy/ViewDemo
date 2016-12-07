package com.zy.md.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.zy.md.R;
import com.zy.md.base.view.BaseFragment;

import butterknife.BindView;

/**
 * Created by Simon on 2016/12/6.
 */

public class GankFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.rv_gank_content)
    RecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank;
    }
}

package com.zy.md.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zy.md.R;
import com.zy.md.base.App;
import com.zy.md.base.view.BaseFragment;
import com.zy.md.ui.di.DaggerDouBanItemFragmentComponent;
import com.zy.md.ui.di.DouBanItemModule;
import com.zy.md.ui.presenter.DouBanItemPresenter;

import javax.inject.Inject;

/**
 * Created by Simon on 2016/12/6.
 */

public class DouBanItemFragment extends BaseFragment {
    private String mId;
    private String mTitle;

    public DouBanItemFragment() {}

    public DouBanItemFragment(String id, String title) {
        mId = id;
        mTitle = title;
    }

    @Inject
    DouBanItemPresenter mPresenter;

    @Override
    protected void getPresenter() {
        DaggerDouBanItemFragmentComponent.builder()
                .appComponent(App.getContext().getAppComponent())
                .douBanItemModule( new DouBanItemModule(this))
                .build()
                .inject(this);
    }


    public String getTitle() {
        return mTitle;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_douban_item;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter.loadData();
    }
}

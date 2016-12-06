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
    private static final String ARGS_ID = "id";
    private static final String ARGS_TITLE = "title";
    private int mId;
    private String mTitle;


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

    public static DouBanItemFragment newInstance(int id, String title){
        DouBanItemFragment fragment = new DouBanItemFragment();
        Bundle args = new Bundle();
        args.putInt( ARGS_ID, id );
        args.putString( ARGS_TITLE, title );
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        mId = args.getInt( ARGS_ID );
        mTitle = args.getString( ARGS_TITLE );
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

package com.zy.md.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.zy.md.R;
import com.zy.md.base.App;
import com.zy.md.base.view.BaseFragment;
import com.zy.md.data.pojo.GankItemData;
import com.zy.md.ui.adaper.GankPicturesAdapter;
import com.zy.md.ui.di.GanMeiziModule;
import com.zy.md.ui.presenter.GankMeiziFragmentPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class GankMeiziFragment extends BaseFragment {

    @Inject
    GankMeiziFragmentPresenter mPresenter;

    @BindView(R.id.rv_content)
    RecyclerView mContentRecyclerView;

    GankPicturesAdapter mAdapter;



    public GankMeiziFragment() {
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank_meizi;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Logger.i( mPresenter.toString() );

        setupView();

        loadData();

    }

    public void showData(List<GankItemData> datas){
        mAdapter.setData( datas );
    }

    private void setupView() {
        mAdapter = new GankPicturesAdapter();
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        mContentRecyclerView.setLayoutManager( mLayoutManager);
        mContentRecyclerView.setAdapter(mAdapter);
    }

    private void loadData(){
        mPresenter.loadData( 1 );
    }

    protected void setupComponent(){
        App.getContext().getAppComponent()
                .plus(new GanMeiziModule(this))
                .inject(this);
    }
}

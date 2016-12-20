package com.zy.md.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.simon.ex_recyclerview.EndlessRecyclerOnScrollListener;
import com.simon.ex_recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.simon.ex_recyclerview.widget.LoadingFooter;
import com.zy.md.R;
import com.zy.md.base.App;
import com.zy.md.base.view.BaseActivity;
import com.zy.md.base.view.BaseFragment;
import com.zy.md.base.view.recycleview.BaseRecyclerAdapter;
import com.zy.md.base.view.recycleview.BaseRecyclerHolder;
import com.zy.md.base.view.recycleview.DividerDecorarion;
import com.zy.md.data.pojo.GankItemData;
import com.zy.md.ui.activity.GankSearchActivity;
import com.zy.md.ui.di.DaggerGankFragmentComponent;
import com.zy.md.ui.di.GankFragmentModule;
import com.zy.md.ui.presenter.GankFragmentPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Simon on 2016/12/6.
 */

public class GankFragment extends BaseFragment {

    @Inject
    GankFragmentPresenter mPresenter;

    @BindView(R.id.abl_gank)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.rv_gank_content)
    RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private HeaderAndFooterRecyclerViewAdapter mHeaderFooterAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView();

        loadCatalogData();
    }



    private void setupView() {

        mAppBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            final int totalRange = appBarLayout.getTotalScrollRange();

        });


        mAdapter = new Adapter();
        mHeaderFooterAdapter = new HeaderAndFooterRecyclerViewAdapter(mAdapter);

        mRecyclerView.setLayoutManager( new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration( new DividerDecorarion( 30, 20, 30, 20 ));
        mRecyclerView.setAdapter(mHeaderFooterAdapter);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(){
            @Override
            public void onLoadNextPage(View view) {
                loadNextPage();
            }
        });
    }

    void loadNextPage(){
        mHeaderFooterAdapter.addFooterView( new LoadingFooter(getContext()).setState(LoadingFooter.State.Loading) );
    }

    private void loadCatalogData() {
        mPresenter.loadCatalogData( 1 );
    }

    public void onLoadCatalogDataComplete( List<GankItemData> datas ){
        mAdapter.addData( datas );
    }

    public void onLoadCatalogDataError(){

    }

    @OnClick(R.id.gank_search_view)
    void onClick(View v){
        Intent intent = new Intent(getContext(), GankSearchActivity.class);
        BaseActivity.start(getActivity(), intent);
    }

    @Override
    public GankFragmentPresenter getPresenter() {
        if (mPresenter == null){
            DaggerGankFragmentComponent.builder()
                    .appComponent( App.getContext().getAppComponent() )
                    .gankFragmentModule( new GankFragmentModule(this))
                    .build()
                    .inject( this );
        }
        return mPresenter;
    }
}

class Adapter extends BaseRecyclerAdapter<GankItemData>{

    public Adapter() {
        super(null, R.layout.item_gank_fragment_adapter);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerHolder holder, int position, GankItemData itemData) {
        TextView titleTextView = holder.getView( R.id.tv_desc_item_gank );
        ImageView picImageView = holder.getView(R.id.iv_image_item_gank);
        Glide.with(App.getContext())
                .load( itemData.url )
                .centerCrop()
                .into( picImageView );
        titleTextView.setText( itemData.desc );
    }
}

package com.zy.md.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.zy.md.R;
import com.zy.md.base.App;
import com.zy.md.base.view.BaseFragment;
import com.zy.md.base.view.BasePresenter;
import com.zy.md.base.view.recycleview.BaseRecyclerAdapter;
import com.zy.md.base.view.recycleview.ItemClickSupport;
import com.zy.md.data.pojo.GankItemData;
import com.zy.md.ui.activity.SingleImageActivity;
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

        Logger.i(mPresenter.toString());

        setupView();

        loadData();

    }

    public void showData(List<GankItemData> datas) {
        mAdapter.setData(datas);
    }

    private void setupView() {
        mAdapter = new GankPicturesAdapter();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        mContentRecyclerView.setLayoutManager(layoutManager);
        mContentRecyclerView.setAdapter(mAdapter);

        ItemClickSupport.addTo(mContentRecyclerView)
                .setOnItemClickListener((recyclerView, position, v) -> {
                    View shareView = v.findViewById(R.id.iv_picture);
                    GankItemData itemData = mAdapter.getItem(position);
                    String url = itemData.getUrl();


                    ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(), shareView, getResources().getString(R.string.transition_single_image) );
//                    ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeScaleUpAnimation(
//                      shareView,  shareView.getWidth()/2, shareView.getHeight()/2, 0 , 0
//                    );
                    Intent intent = new Intent(getContext(), SingleImageActivity.class);
                    intent.putExtra("url", url);
                    ActivityCompat.startActivity(getActivity(), intent, activityOptions.toBundle()  );
                });
    }

    private void loadData() {
        mPresenter.loadData(1);
    }

    protected BasePresenter getPresenter() {
        if (mPresenter == null){

            App.getContext().getAppComponent()
                    .plus(new GanMeiziModule(this))
                    .inject(this);
        }
        return mPresenter;
    }
}

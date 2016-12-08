package com.zy.md.ui.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.zy.md.R;
import com.zy.md.base.App;
import com.zy.md.base.view.BaseFragment;
import com.zy.md.base.view.recycleview.BaseRecyclerAdapter;
import com.zy.md.base.view.recycleview.BaseRecyclerHolder;
import com.zy.md.data.pojo.DouBanGirlItemData;
import com.zy.md.ui.di.DaggerDouBanItemFragmentComponent;
import com.zy.md.ui.di.DouBanItemModule;
import com.zy.md.ui.presenter.DouBanItemPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Simon on 2016/12/6.
 */

public class DouBanItemFragment extends BaseFragment {
    private String mId;
    private String mTitle;

    @BindView(R.id.rv_douban_item)
    RecyclerView mRecyclerView;

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

        setupView();
        mPresenter.loadData();
    }

    private void setupView() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy( StaggeredGridLayoutManager.GAP_HANDLING_NONE );

        mRecyclerView.setLayoutManager( layoutManager );

    }
}

class ItemImageAdapter extends BaseRecyclerAdapter<DouBanGirlItemData>{

    public ItemImageAdapter(List<DouBanGirlItemData> data, @LayoutRes int layoutResId) {
        super(data, layoutResId);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerHolder holder, int position, DouBanGirlItemData itemData) {

    }
}

package com.zy.md.ui.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.zy.md.R;
import com.zy.md.base.view.BaseFragment;
import com.zy.md.base.view.recycleview.BaseRecyclerAdapter;
import com.zy.md.base.view.recycleview.BaseRecyclerHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Simon on 2016/12/6.
 */

public class GankFragment extends BaseFragment {

    @BindView(R.id.abl_gank)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.rv_gank_content)
    RecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView();
    }

    private void setupView() {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int totalRange = appBarLayout.getTotalScrollRange();

                Logger.d("totalRange="+ totalRange + "; current=" + verticalOffset);
            }
        });

        Adapter adapter = new Adapter();

        mRecyclerView.setLayoutManager( new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
    }
}

class Adapter extends BaseRecyclerAdapter<String>{
    static List<String> DATA = new ArrayList<>(50);
    static {
        for (int i =0; i<50; i++){
            DATA.add("item " + i);
        }
    }

    public Adapter() {
        super(DATA, android.R.layout.simple_list_item_1);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerHolder holder, int position, String itemData) {
        TextView view = holder.getView(android.R.id.text1);
        view.setText( getItem(position) );
    }
}

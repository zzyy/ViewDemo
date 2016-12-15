package com.zy.md.ui.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.simon.ex_recyclerview.BaseRecyclerAdapter;
import com.simon.ex_recyclerview.BaseRecyclerHolder;
import com.zy.md.R;
import com.zy.md.base.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon on 2016/12/15.
 */

public class RecyclerSampleFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycler_sample;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view;

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter( new Adapter() );

    }


    ItemTouchHelper.Callback mItemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP|ItemTouchHelper.DOWN, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };


    interface ItemTouchAdapter{
        void onItemMoved();
    }



    static class Adapter extends BaseRecyclerAdapter<String> {
        static List<String> DATA = new ArrayList<>();
        static {
            for (int i =0; i<60; i++){
                DATA.add( i + " item data" );
            }
        }

        public Adapter() {
            super(DATA, 0);
        }

        @Override
        public void onBindViewHolder(BaseRecyclerHolder holder, int position, String itemData) {

        }
    }
}



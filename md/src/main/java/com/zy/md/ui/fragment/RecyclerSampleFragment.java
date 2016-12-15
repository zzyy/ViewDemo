package com.zy.md.ui.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import com.simon.ex_recyclerview.BaseRecyclerAdapter;
import com.simon.ex_recyclerview.BaseRecyclerHolder;
import com.zy.md.R;
import com.zy.md.base.view.BaseFragment;

import java.util.ArrayList;
import java.util.Collections;
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
        Adapter adapter = new Adapter();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchCallBack(ItemTouchHelper.UP|ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT, adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter( adapter );

    }


    static class ItemTouchCallBack extends ItemTouchHelper.SimpleCallback {
        private ItemTouchAdapter mAdapter;
        public ItemTouchCallBack(int dragDirs, int swipeDirs, ItemTouchAdapter adapter) {
            super(dragDirs, swipeDirs);
            mAdapter = adapter;
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            mAdapter.onItemMoved( viewHolder.getAdapterPosition(), target.getAdapterPosition() );
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            mAdapter.onItemSwip( viewHolder.getAdapterPosition() );
        }
    }


    interface ItemTouchAdapter{
        void onItemMoved(int fromPosition, int toPosition);
        void onItemSwip(int position);
    }



    static class Adapter extends BaseRecyclerAdapter<String> implements ItemTouchAdapter{
        static List<String> DATA = new ArrayList<>();
        static {
            for (int i =0; i<60; i++){
                DATA.add( i + " item data" );
            }
        }

        public Adapter() {
            super(DATA, R.layout.item_test_card);
        }

        @Override
        public void onBindViewHolder(BaseRecyclerHolder holder, int position, String itemData) {
            TextView textView = holder.getView(R.id.tv_item_card);
            textView.setText( itemData );
        }

        @Override
        public void onItemMoved(int fromPosition, int toPosition) {
            Collections.swap(mData, fromPosition, toPosition);
            notifyItemMoved( fromPosition, toPosition );
        }

        @Override
        public void onItemSwip(int position) {
            mData.remove(position);
            notifyItemRemoved(position);
        }
    }
}



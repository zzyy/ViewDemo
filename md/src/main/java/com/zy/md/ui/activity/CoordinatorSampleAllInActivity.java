package com.zy.md.ui.activity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.simon.ex_recyclerview.BaseRecyclerAdapter;
import com.simon.ex_recyclerview.BaseRecyclerHolder;
import com.simon.ex_recyclerview.DividerDecorarion;
import com.simon.ex_recyclerview.click.ItemClickSupport;
import com.zy.md.R;
import com.zy.md.base.view.BaseActivity;
import com.zy.md.main.TestBottomSheetDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

public class CoordinatorSampleAllInActivity extends BaseActivity {

    @BindView(R.id.activity_coordinator_sample_all_in)
    View mCoordinatorLayout;

    @BindView(R.id.float_action_menu_view)
    FloatingActionsMenu mFloatingActionsMenu;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    Adapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private StaggeredGridLayoutManager mGrilLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_sample_all_in);
        setupView();
    }

    private void setupView() {
        setupRecyclerView();

        setupFloatActionMenu();
    }



    private void setupRecyclerView() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        mGrilLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerDecorarion(20, 15, 20, 15));
        mAdapter = new Adapter();
        mRecyclerView.setAdapter(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchCallBack(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT
                        , ItemTouchHelper.RIGHT, new ItemTouchCallBack.Listener() {
                    @Override
                    public void onItemMoved(int fromPosition, int toPosition) {
                        onAdapterItemMoved(fromPosition, toPosition);
                    }

                    @Override
                    public void onItemSwiped(int position) {
                        onAdapterItemSwiped(position);
                    }
                }));
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        TestBottomSheetDialog bottomSheetDialog = new TestBottomSheetDialog(this);

        ItemClickSupport.addTo(mRecyclerView)
                .setOnItemClickListener((recyclerView, position, v) -> bottomSheetDialog.show())
                .setChildOnClickListener(R.id.iv_favorite, (recyclerView, position, v) -> itemTouchHelper.startDrag(mRecyclerView.findViewHolderForAdapterPosition(position)));
    }

    private void setupFloatActionMenu() {
        rxClick(R.id.change_to_grid_view)
                .subscribe(aVoid -> {
                    mRecyclerView.setLayoutManager(mGrilLayoutManager);
                    mFloatingActionsMenu.collapse();
                });

        rxClick(R.id.change_to_linear_view)
                .subscribe(aVoid -> {
                    mRecyclerView.setLayoutManager(mLinearLayoutManager);
                    mFloatingActionsMenu.collapse();
                });
    }

    void onAdapterItemSwiped(int position) {
        String itemData = mAdapter.getItem(position);
        mAdapter.onItemSwip(position);
        Snackbar.make(mCoordinatorLayout, "第" + position + "项被移除", Snackbar.LENGTH_SHORT)
                .setAction("撤销", view -> mAdapter.addData(position, itemData))
                .show();
    }

    void onAdapterItemMoved(int fromPosition, int toPosition) {
        mAdapter.onItemMoved(fromPosition, toPosition);
    }


    static class ItemTouchCallBack extends ItemTouchHelper.SimpleCallback {
        public interface Listener {
            void onItemMoved(int fromPosition, int toPosition);

            void onItemSwiped(int position);
        }

        private Listener mListener;

        public ItemTouchCallBack(int dragDirs, int swipeDirs, Listener listener) {
            super(dragDirs, swipeDirs);
            mListener = listener;
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return false;
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            if (mListener != null) {
                mListener.onItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            }
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            if (mListener != null) {
                mListener.onItemSwiped(viewHolder.getAdapterPosition());
            }
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                float width = viewHolder.itemView.getWidth();
                float alpha = 1 - Math.abs(dX) / width;
                viewHolder.itemView.setAlpha(alpha);
                viewHolder.itemView.setTranslationX(dX);
            } else {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }

        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE){
                viewHolder.itemView.setBackgroundColor( Color.LTGRAY );
            }

            super.onSelectedChanged(viewHolder, actionState);
        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            viewHolder.itemView.setBackgroundColor( Color.parseColor("#ffffff") );
        }
    }

    interface ItemTouchAdapter {
        void onItemMoved(int fromPosition, int toPosition);

        void onItemSwip(int position);
    }

    static class Adapter extends BaseRecyclerAdapter<String> implements ItemTouchAdapter {
        static List<String> DATA = new ArrayList<>();

        static {
            for (int i = 0; i < 60; i++) {
                DATA.add(i + " item data");
            }
        }

        public Adapter() {
            super(DATA, R.layout.item_test_card);
        }

        public void addData(int position, String data) {
            mData.add(position, data);
            notifyItemInserted(position);
        }

        @Override
        public void onBindViewHolder(BaseRecyclerHolder holder, int position, String itemData) {
            TextView textView = holder.getView(R.id.tv_item_card);
            textView.setText(itemData);
        }

        @Override
        public void onItemMoved(int fromPosition, int toPosition) {
            Collections.swap(mData, fromPosition, toPosition);
            notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onItemSwip(int position) {
            mData.remove(position);
            notifyItemRemoved(position);
        }
    }
}

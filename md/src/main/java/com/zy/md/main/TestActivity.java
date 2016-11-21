package com.zy.md.main;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zy.md.R;
import com.zy.md.base.utils.T;
import com.zy.md.base.view.BaseActivity;
import com.zy.md.main.ui.BaseRecyclerAdapter;
import com.zy.md.main.ui.BaseRecyclerHolder;
import com.zy.md.main.ui.DividerItemDecorarion;
import com.zy.md.main.ui.ItemClickSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

public class TestActivity extends BaseActivity {

    @BindView(R.id.iv_test_head)
    ImageView mHeadImageView;

    @BindView(R.id.content_test)
    RecyclerView mRecyclerView;

    @BindView(R.id.root_view)
    CoordinatorLayout mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_test);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);



        initView();
    }


    void initView() {
        Picasso.with(this).load("http://ww3.sinaimg.cn/large/610dc034gw1f9shm1cajkj20u00jy408.jpg")
                .into( mHeadImageView );


        RecycleViewAdapter adapter = new RecycleViewAdapter();
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager( new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration( new DividerItemDecorarion(20, 15, 20, 15));

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL );
//        dividerItemDecoration.setDrawable(new ShapeDrawable( new RectShape()));
//        mRecyclerView.addItemDecoration(  dividerItemDecoration );

        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                int swipeFlag = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
                return makeMovementFlags(dragFlag, swipeFlag);
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return true;
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                adapter.onItemMove( viewHolder.getAdapterPosition(), target.getAdapterPosition()  );
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.onItemDismiss( viewHolder.getAdapterPosition() );
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
                    float width = viewHolder.itemView.getWidth();
                    float alpha = 1 - Math.abs(dX)/width;
                    viewHolder.itemView.setAlpha( alpha );
                    viewHolder.itemView.setTranslationX( dX );
                }else {
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
        });
        touchHelper.attachToRecyclerView( mRecyclerView );


        /*mRecyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                final int position = mRecyclerView.getChildAdapterPosition( view );

                view.findViewById(R.id.iv_favorite).setOnClickListener( v -> {
                    T.getInstance( TestActivity.this ).s("position:" + position);
                });
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {

            }
        });*/

        View bottomSheet = findViewById(R.id.bottom_sheet);
        final BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setHideable(true);
        behavior.setPeekHeight( 300 );


        ItemClickSupport.addTo( mRecyclerView ).setOnItemClickListener((recyclerView, position, v) -> {
            if (position ==0){
                new TestBottomSheetDialog(this).show();

                return;
            }
            if (position < 10){
                behavior.setState( BottomSheetBehavior.STATE_EXPANDED );
            }else {
                behavior.setState( BottomSheetBehavior.STATE_HIDDEN );

            }

            T.getInstance(this).s("item click: " + position);
        }).setChildOnClickListener(R.id.iv_favorite, (recyclerView, position, v) -> {
            T.getInstance(this).s("child click: position=" + position);
            Snackbar.make(mRootView, "child click: position=" + position, Snackbar.LENGTH_SHORT).show();

        });
    }

}

interface ItemTouchHelperAdapter {

    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}

class RecycleViewAdapter extends BaseRecyclerAdapter<String> implements ItemTouchHelperAdapter {
     final static List<String> DATA = new ArrayList<>();

    static{
        for (int i =0; i<50; i++){
            DATA.add( i + " item data" );
        }
    }

    public RecycleViewAdapter() {
        super(DATA, R.layout.item_test_card);
    }


    @Override
    public void onBindViewHolder(BaseRecyclerHolder holder, int position, String itemData) {
        final TextView textView = holder.getView(R.id.tv_item_card);
        textView.setText( itemData );
    }


    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap( mData, fromPosition, toPosition );
        notifyItemMoved( fromPosition, toPosition );

    }

    @Override
    public void onItemDismiss(int position) {
        mData.remove( position );
        notifyItemRemoved( position );
    }
}

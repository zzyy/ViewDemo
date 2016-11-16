package com.zy.md.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zy.md.R;
import com.zy.md.base.view.BaseActivity;
import com.zy.md.main.ui.BaseRecyclerHolder;
import com.zy.md.main.ui.DividerItemDecorarion;

import butterknife.BindView;

public class TestActivity extends BaseActivity {

    @BindView(R.id.iv_test_head)
    ImageView mHeadImageView;

    @BindView(R.id.content_test)
    RecyclerView mRecyclerView;

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
    }

}

class RecycleViewAdapter extends RecyclerView.Adapter<BaseRecyclerHolder> {

    @Override
    public BaseRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return  new BaseRecyclerHolder( layoutInflater.inflate(R.layout.item_test_card, parent, false) ) ;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerHolder holder, int position) {
        final TextView textView = holder.getView(R.id.tv_item_card);
        textView.setText(position + " item data");
    }

    @Override
    public int getItemCount() {
        return 30;
    }

}

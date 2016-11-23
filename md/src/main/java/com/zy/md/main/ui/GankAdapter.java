package com.zy.md.main.ui;

import android.widget.TextView;

import com.zy.md.R;
import com.zy.md.data.pojo.GankItemData;
import com.zy.md.base.view.recycleview.BaseRecyclerAdapter;
import com.zy.md.base.view.recycleview.BaseRecyclerHolder;

import java.util.List;

/**
 * Created by Simon on 2016/11/4.
 */

public class GankAdapter extends BaseRecyclerAdapter<GankItemData> {

    public GankAdapter() {
        this(null);
    }

    public GankAdapter(List<GankItemData> mData) {
        super(mData, R.layout.item_gank);
    }


    @Override
    public void onBindViewHolder(BaseRecyclerHolder holder, int position, GankItemData itemData) {
        TextView titleTextView = holder.getView(R.id.tv_title);

        titleTextView.setText( itemData.getDesc() );
    }
}



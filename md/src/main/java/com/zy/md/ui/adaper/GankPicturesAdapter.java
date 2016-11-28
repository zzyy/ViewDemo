package com.zy.md.ui.adaper;

import android.graphics.Bitmap;
import android.util.SparseIntArray;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.zy.md.R;
import com.zy.md.base.App;
import com.zy.md.base.view.recycleview.BaseRecyclerAdapter;
import com.zy.md.base.view.recycleview.BaseRecyclerHolder;
import com.zy.md.base.view.widget.RatioImageView;
import com.zy.md.data.pojo.GankItemData;

import java.util.concurrent.ExecutionException;

/**
 * Created by Simon on 2016/11/24.
 */

public class GankPicturesAdapter extends BaseRecyclerAdapter<GankItemData> {
    public GankPicturesAdapter() {
        super(null, R.layout.item_gank_pic);
        this.setHasStableIds(true);
    }


    SparseIntArray imageHeightRecord = new SparseIntArray();

    @Override
    public void onBindViewHolder(BaseRecyclerHolder holder, int position, GankItemData itemData) {
        RatioImageView imageView = holder.getView(R.id.iv_picture);
        TextView desTextView = holder.getView(R.id.tv_description);


        imageView.setOriginalSize( itemData.width, itemData.height );

        Glide.with(App.getContext())
                .load(itemData.getUrl())
                .into(imageView);



        desTextView.setText(itemData.getDesc());
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId().hashCode();
    }

    @Override
    public void onViewAttachedToWindow(BaseRecyclerHolder holder) {
        super.onViewAttachedToWindow(holder);
    }
}

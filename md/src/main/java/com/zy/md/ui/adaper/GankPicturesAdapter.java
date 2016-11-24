package com.zy.md.ui.adaper;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zy.md.R;
import com.zy.md.base.App;
import com.zy.md.base.view.recycleview.BaseRecyclerAdapter;
import com.zy.md.base.view.recycleview.BaseRecyclerHolder;
import com.zy.md.data.pojo.GankItemData;

/**
 * Created by Simon on 2016/11/24.
 */

public class GankPicturesAdapter extends BaseRecyclerAdapter<GankItemData> {
    public GankPicturesAdapter() {
        super(null, R.layout.item_gank_pic);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerHolder holder, int position, GankItemData itemData) {
        ImageView imageView = holder.getView(R.id.iv_picture);
        TextView desTextView = holder.getView(R.id.tv_description);

        Picasso.with(App.getContext())
                .load( itemData.getUrl() )
                .config(Bitmap.Config.RGB_565)
                .noFade()
                .into(imageView);

        desTextView.setText( itemData.getDesc() );
    }
}

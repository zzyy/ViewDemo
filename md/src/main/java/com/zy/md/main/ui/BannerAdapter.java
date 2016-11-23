package com.zy.md.main.ui;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zy.md.base.App;
import com.zy.md.R;
import com.zy.md.data.pojo.GankItemData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon on 2016/11/6.
 */

public class BannerAdapter extends PagerAdapter {
    LayoutInflater mInflater;
    List<GankItemData> mData;


    public void setData(List<GankItemData> data) {
        if (mData == null)
            mData = new ArrayList<>();

        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (mInflater ==null){
            mInflater = LayoutInflater.from(container.getContext());
        }
        View rootView = mInflater.inflate(R.layout.item_main_banner, container, false);

        ImageView imageView = (ImageView) rootView.findViewById(R.id.iv_banner_pic);

        Picasso.with(App.getContext()).load(mData.get(position).getUrl()).into(imageView);

        container.addView(rootView, 0);
        return rootView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

package com.zy.md.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.view.RxView;
import com.zy.md.R;
import com.zy.md.base.view.BaseActivity;
import com.zy.md.base.view.BaseFragment;
import com.zy.md.ui.activity.BehaviorSampleActivity;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Simon on 2016/12/1.
 */

public class SampleFragment extends BaseFragment {

    @BindView(R.id.iv_share_view)
    ImageView mImageView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sample;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Glide.with(this)
                .load(R.drawable.statusbar_bg)
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .into(mImageView);

        RxView.clicks(mImageView)
                .subscribe(aVoid -> {
                    Pair<View, String> shareElement = new Pair<View, String>(mImageView, getString(R.string.behavior_sample_share_img));
                    Intent intent = new Intent(getContext(), BehaviorSampleActivity.class);
                    BaseActivity.start(getActivity(), intent, shareElement);
                });
    }


}

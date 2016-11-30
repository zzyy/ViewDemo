package com.zy.md.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.zy.md.R;
import com.zy.md.base.view.BaseActivity;
import com.zy.md.utils.ActivityHelper;

import butterknife.BindView;

public class SingleImageActivity extends BaseActivity {


    @BindView(R.id.iv_single_img)
    ImageView mImageView;
    private String mUrl;

    private boolean mIsSystemUiVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_image);

        mUrl = getIntent().getStringExtra("url");

        setupView();


    }

    private void setupView() {


        Glide.with(this)
                .load(mUrl)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        mImageView.setImageDrawable(resource);
                        return false;
                    }
                })
                .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);

        mImageView.setOnClickListener( v ->{
            if (mIsSystemUiVisible){
                ActivityHelper.hideSystemUi( v );
                mIsSystemUiVisible = false;
            }else {
                ActivityHelper.showSystemUi( v );
                mIsSystemUiVisible = true;
            }
        });

    }

    public static void start(Activity activity, String url, View shareView) {
        Intent intent = new Intent(activity, SingleImageActivity.class);
        intent.putExtra("url", url);

        start(activity, intent,
                new Pair<View, String>(shareView, activity.getResources().getString(R.string.transition_single_image)));
    }
}

package com.zy.md.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.zy.md.R;
import com.zy.md.base.view.BaseActivity;

import butterknife.BindView;

public class SingleImageActivity extends BaseActivity {

    @BindView(R.id.iv_single_img)
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_image);

        String url = getIntent().getStringExtra("url");

        Glide.with(this)
                .load(url)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        mImageView.setImageDrawable(resource );
                        return false;
                    }
                })
                .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
    }

    public static void start(Activity activity, String url, View shareView) {
        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity, shareView, activity.getResources().getString(R.string.transition_single_image) );
//                    ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeScaleUpAnimation(
//                      shareView,  shareView.getWidth()/2, shareView.getHeight()/2, 0 , 0
//                    );
        Intent intent = new Intent(activity, SingleImageActivity.class);
        intent.putExtra("url", url);
        ActivityCompat.startActivity(activity, intent, activityOptions.toBundle()  );
    }
}

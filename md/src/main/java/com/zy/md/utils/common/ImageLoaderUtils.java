package com.zy.md.utils.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.target.Target;

import java.util.concurrent.ExecutionException;

/**
 * 对Glide封装下
 * Created by Simon on 2016/12/8.
 */

public class ImageLoaderUtils {
    RequestManager mGlideRequestManager;

    private ImageLoaderUtils(Context context) {
        mGlideRequestManager = Glide.with(context);
    }

    private ImageLoaderUtils(Activity activity) {
        mGlideRequestManager = Glide.with(activity);
    }

    private ImageLoaderUtils(FragmentActivity activity) {
        mGlideRequestManager = Glide.with(activity);
    }

    private ImageLoaderUtils(android.app.Fragment fragment) {
        mGlideRequestManager = Glide.with(fragment);
    }

    private ImageLoaderUtils(Fragment fragment) {
        mGlideRequestManager = Glide.with(fragment);
    }

    public static ImageLoaderUtils with(Context context) {
        return new ImageLoaderUtils(context);
    }

    public static ImageLoaderUtils with(Activity activity) {
        return new ImageLoaderUtils(activity);
    }

    public static ImageLoaderUtils with(FragmentActivity activity) {
        return new ImageLoaderUtils(activity);
    }

    public static ImageLoaderUtils with(android.app.Fragment fragment) {
        return new ImageLoaderUtils(fragment);
    }

    public static ImageLoaderUtils with(Fragment fragment) {
        return new ImageLoaderUtils(fragment);
    }

    public Bitmap fetchBitmap(String url) throws ExecutionException, InterruptedException {
       return mGlideRequestManager
                .load(url)
                .asBitmap()
                .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .get();
    }

    public Point fetchImageSize(String url){
        Point point = new Point();
        try {
            final Bitmap bitmap = fetchBitmap(url);
            point.x = bitmap.getWidth();
            point.y = bitmap.getHeight();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return point;
    }
}

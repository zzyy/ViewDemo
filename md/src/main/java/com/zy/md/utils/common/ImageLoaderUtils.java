package com.zy.md.utils.common;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.util.concurrent.ExecutionException;

/**
 * Created by Simon on 2016/12/8.
 */

public class ImageLoaderUtils {

    public static Bitmap fetchBitmap(Fragment fragment, String url) throws ExecutionException, InterruptedException {
        Bitmap bitmap = Glide.with( fragment )
                .load(url)
                .asBitmap()
                .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .get();
        return bitmap;
    }
}

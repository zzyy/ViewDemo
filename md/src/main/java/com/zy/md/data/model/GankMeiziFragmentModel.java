package com.zy.md.data.model;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.annotation.IntRange;
import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.orhanobut.logger.Logger;
import com.zy.md.data.net.GankApi;
import com.zy.md.data.pojo.GankData;
import com.zy.md.data.pojo.GankItemData;

import java.util.List;
import java.util.concurrent.ExecutionException;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by Simon on 2016/11/28.
 */

public class GankMeiziFragmentModel {
    private final static int PAGE_SIZE = 20;


    private GankApi mGankApi;
    private Fragment mFragment;


    public GankMeiziFragmentModel(Fragment fragment, GankApi gankApi) {
        mFragment = fragment;
        mGankApi = gankApi;
    }

    public Observable<GankData<List<GankItemData>>> loadFromNet(@IntRange(from = 1) int pageNo) {
        return mGankApi.getMenu(GankApi.TYPE_MEZI, PAGE_SIZE, pageNo)
                .subscribeOn(Schedulers.io())
                .doOnNext(listGankData -> {
                    for (GankItemData itemData : listGankData.getResults()) {
                        if (itemData.width == 0) {

                            Point size = new Point();
                            try {
                                fetchPictureSize(itemData.getUrl(), size);

                                itemData.width = size.x;
                                itemData.height = size.y;

                            } catch (Exception e) {
                                Logger.e("获取图片大小报错", e);
                            }
                        }
                    }
                });
    }


    public void loadFromDb(){

    }

    private void saveToDb() {

    }


    private void fetchPictureSize(String url, Point size) throws ExecutionException, InterruptedException {
        Bitmap bitmap = Glide.with( mFragment )
                .load(url)
                .asBitmap()
                .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .get();

        size.x = bitmap.getWidth();
        size.y = bitmap.getHeight();
    }

}

package com.zy.md.data.model;

import com.zy.md.data.net.GankApi;
import com.zy.md.data.pojo.GankData;
import com.zy.md.data.pojo.GankItemData;
import com.zy.md.utils.common.DateUtils;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by Simon on 2016/12/8.
 */

public class GankDataModel {
    private static final int PAGE_SIZE = 20;

    GankApi mGankApi;

    @Inject
    public GankDataModel(GankApi gankApi) {
        mGankApi = gankApi;
    }


    private GankData<List<GankItemData>> mergeMeiziAndVideoData(GankData<List<GankItemData>> meiziData, GankData<List<GankItemData>> videoData) {
        for (GankItemData meizi : meiziData.getResults()) {
            meizi.desc = meizi.desc + "\n" + getFirstVideoDesc(meizi.publishedAt, videoData.getResults());
        }

        return meiziData;
    }

    private int mLastVideoIndex = 0;

    private String getFirstVideoDesc(Date publishedAt, List<GankItemData> results) {
        String videoDesc = "";
        for (int i = mLastVideoIndex; i < results.size(); i++) {
            GankItemData video = results.get(i);
            if (video.publishedAt == null) video.publishedAt = video.createdAt;
            if (DateUtils.isTheSameDay(publishedAt, video.publishedAt)) {
                videoDesc = video.desc;
                mLastVideoIndex = i;
                break;
            }
        }
        return videoDesc;
    }

    private void saveData(List<GankItemData> data) {

    }

    public Observable<List<GankItemData>> loadData(int pageNo) {
        return Observable
                .zip(mGankApi.getMenu(GankApi.TYPE_MEZI, PAGE_SIZE, pageNo),
                        mGankApi.getMenu(GankApi.TYPE_VIDEO, PAGE_SIZE, pageNo),
                        this::mergeMeiziAndVideoData)
                .subscribeOn(Schedulers.io())
                .map(GankData::getResults)
                .flatMap(iterable -> Observable.from(iterable))
                .toSortedList((gankItemData, gankItemData2) ->
                        gankItemData2.publishedAt.compareTo(gankItemData.publishedAt))
                .doOnNext(this::saveData);

    }





}

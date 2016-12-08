package com.zy.md.data.model;

import com.zy.md.data.net.GankApi;
import com.zy.md.data.pojo.GankData;
import com.zy.md.data.pojo.GankItemData;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by Simon on 2016/12/8.
 */

public class GankDataModel {
    private static final int PAGE_SIZE = 20;

    GankApi mGankApi;

    public GankDataModel(GankApi gankApi) {
        mGankApi = gankApi;
    }


    private GankData<List<GankItemData>> mergeMeiziAndVideoData(GankData<List<GankItemData>> meiziData, GankData<List<GankItemData>> videoData  ){
      return null;
    }

    private  void saveData( List<GankItemData> data ){

    }

    public Observable<List<GankItemData>> loadData( int pageNo ){
        return Observable
                .zip( mGankApi.getMenu( GankApi.TYPE_MEZI, PAGE_SIZE, pageNo),
                        mGankApi.getMenu( GankApi.TYPE_VIDEO, PAGE_SIZE, pageNo),
                        this::mergeMeiziAndVideoData)
                .map(GankData::getResults)
                .flatMap(iterable -> Observable.from(iterable))
                .toSortedList((gankItemData, gankItemData2) ->
                    gankItemData2.publishedAt.compareTo( gankItemData.publishedAt ))
                .doOnNext(this::saveData);

    }



}

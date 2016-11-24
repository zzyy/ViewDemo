package com.zy.md.ui.presenter;

import android.support.annotation.IntRange;

import com.orhanobut.logger.Logger;
import com.zy.md.data.net.GankApi;
import com.zy.md.ui.fragment.GankMeiziFragment;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Simon on 2016/11/24.
 */

public class GankMeiziFragmentPresenter {
    private static final int PAGE_SIZE = 20;

    GankApi mGankApi;

    GankMeiziFragment mGankMeiziFragment;

    public GankMeiziFragmentPresenter(GankMeiziFragment gankMeiziFragment, GankApi gankApi) {
        mGankMeiziFragment = gankMeiziFragment;
        mGankApi = gankApi;
    }


    public void loadData(@IntRange(from = 1) int pageNo) {
        mGankApi.getMenu(GankApi.TYPE_MEZI, PAGE_SIZE, pageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listGankData -> {
                    mGankMeiziFragment.showData( listGankData.getResults() );

                },
                        throwable -> {
                            Logger.e(throwable, "加载出错");})
        ;
    }

}

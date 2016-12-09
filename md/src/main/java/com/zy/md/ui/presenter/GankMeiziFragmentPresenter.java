package com.zy.md.ui.presenter;

import android.support.annotation.IntRange;

import com.orhanobut.logger.Logger;
import com.zy.md.base.view.BasePresenter;
import com.zy.md.data.model.GankMeiziFragmentModel;
import com.zy.md.ui.fragment.GankMeiziFragment;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Simon on 2016/11/24.
 */

public class GankMeiziFragmentPresenter extends BasePresenter {
    private static final int PAGE_SIZE = 20;

    GankMeiziFragmentModel mGankMeiziModel;

    GankMeiziFragment mGankMeiziFragment;


    public GankMeiziFragmentPresenter(GankMeiziFragment gankMeiziFragment, GankMeiziFragmentModel gankMeiziModel) {
        mGankMeiziFragment = gankMeiziFragment;
        mGankMeiziModel = gankMeiziModel;
    }

    public void loadData(@IntRange(from = 1) int pageNo) {

        Subscription subscription = mGankMeiziModel.loadFromNet(pageNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listGankData -> {
                            mGankMeiziFragment.showData(listGankData.getResults());
                        },
                        throwable -> Logger.e(throwable, "加载出错"));



        /*mGankApi.getMenu(GankApi.TYPE_MEZI, PAGE_SIZE, pageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listGankData -> {
                    mGankMeiziFragment.showData( listGankData.getResults() );

                },
                        throwable -> {
                            Logger.e(throwable, "加载出错");})
        ;*/
    }


}

package com.zy.md.ui.presenter;

import android.graphics.Point;

import com.orhanobut.logger.Logger;
import com.zy.md.base.view.BasePresenter;
import com.zy.md.data.net.DouBanGirlApi;
import com.zy.md.data.pojo.DouBanGirlItemData;
import com.zy.md.data.utils.DouBanJsoupParseUtil;
import com.zy.md.ui.fragment.DouBanItemFragment;
import com.zy.md.utils.common.ImageLoaderUtils;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Simon on 2016/12/6.
 */

public class DouBanItemPresenter extends BasePresenter {
    private final static int PAGE_SIZE = 20;

    DouBanItemFragment mView;

    // 就一个请求接口,懒得写model了...
    DouBanGirlApi mDouBanGirlApi;

    ImageLoaderUtils mImageLoaderUtils;


    @Inject
    public DouBanItemPresenter(DouBanGirlApi douBanGirlApi, DouBanItemFragment douBanItemFragment) {
        mDouBanGirlApi = douBanGirlApi;
        mView = douBanItemFragment;

        mImageLoaderUtils = ImageLoaderUtils.with(mView);
    }


    Subscriber mLoadDataSubscriber = new Subscriber() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Object o) {

        }
    };

    public void loadData( String cid ) {
        mDouBanGirlApi.getGirlItemData(cid, PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .map(s -> DouBanJsoupParseUtil.parseGirls(s) )
                .flatMap(Observable::from)
                .map(this::fetchImageSize)
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(douBanGirlItemDatas -> {
                    Logger.d("douban success: " + douBanGirlItemDatas);
                    mView.onLoadDataCompelete( douBanGirlItemDatas );
                }, throwable ->  Logger.e(throwable, "加载豆瓣数据报错") );

    }

    private DouBanGirlItemData fetchImageSize(DouBanGirlItemData data){
        final Point size = mImageLoaderUtils.fetchImageSize( data.url );
        data.width = size.x;
        data.height = size.y;
        return  data;
    }



}

package com.zy.md.ui.presenter;

import android.util.Log;

import com.orhanobut.logger.Logger;
import com.zy.md.base.dagger.FragmentScope;
import com.zy.md.base.view.BasePresenter;
import com.zy.md.data.net.DouBanGirlApi;
import com.zy.md.data.pojo.DouBanGirlItemData;
import com.zy.md.data.utils.DouBanJsoupParseUtil;
import com.zy.md.ui.fragment.DouBanItemFragment;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Simon on 2016/12/6.
 */

public class DouBanItemPresenter extends BasePresenter {

    DouBanItemFragment mDouBanItemFragment;

    // 就一个请求接口,懒得写model了...
    DouBanGirlApi mDouBanGirlApi;


    @Inject
    public DouBanItemPresenter(DouBanGirlApi douBanGirlApi, DouBanItemFragment douBanItemFragment) {
        mDouBanGirlApi = douBanGirlApi;
        mDouBanItemFragment = douBanItemFragment;
    }


    public void loadData() {
        mDouBanGirlApi.getGirlItemData(2, 20)
                .subscribeOn(Schedulers.io())
                .map(s -> {
                    List<DouBanGirlItemData> datas = DouBanJsoupParseUtil.parseGirls(s);
                    Logger.d(datas);
                    return datas;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(douBanGirlItemDatas -> {

                }, throwable -> {
                });

    }

}

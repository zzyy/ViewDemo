package com.zy.md.ui.presenter;

import com.zy.md.base.dagger.FragmentScope;
import com.zy.md.base.view.BasePresenter;
import com.zy.md.data.net.DouBanGirlApi;
import com.zy.md.ui.fragment.DouBanItemFragment;

import javax.inject.Inject;

/**
 * Created by Simon on 2016/12/6.
 */

public class DouBanItemPresenter extends BasePresenter {

    DouBanItemFragment mDouBanItemFragment;

    // 就一个请求接口,懒得写model了...
    DouBanGirlApi mDouBanGirlApi;


    @Inject
    @FragmentScope
    public DouBanItemPresenter(DouBanGirlApi douBanGirlApi, DouBanItemFragment douBanItemFragment) {
        mDouBanGirlApi = douBanGirlApi;
        mDouBanItemFragment = douBanItemFragment;
    }


    public void loadData(){
        mDouBanGirlApi.getGirlItemData( 2, 20 );

    }

}

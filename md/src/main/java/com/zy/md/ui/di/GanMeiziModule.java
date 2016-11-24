package com.zy.md.ui.di;

import com.zy.md.base.dagger.FragmentScope;
import com.zy.md.data.net.GankApi;
import com.zy.md.ui.fragment.GankMeiziFragment;
import com.zy.md.ui.presenter.GankMeiziFragmentPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Simon on 2016/11/24.
 */

@Module
public class GanMeiziModule {

    private GankMeiziFragment mGankMeiziFragment;

    public GanMeiziModule(GankMeiziFragment gankMeiziFragment) {
        mGankMeiziFragment = gankMeiziFragment;
    }

    @Provides
    @FragmentScope
    public GankMeiziFragment provideGankMeiziFragment(){
        return mGankMeiziFragment;
    }

    @Provides
    @FragmentScope
    public GankMeiziFragmentPresenter provideGankMeiziFragmentPresenter(GankMeiziFragment fragment, GankApi gankApi){
        return new GankMeiziFragmentPresenter(fragment, gankApi);
    }

}

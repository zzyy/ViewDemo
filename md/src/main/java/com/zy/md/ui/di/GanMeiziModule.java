package com.zy.md.ui.di;

import com.zy.md.base.dagger.FragmentScope;
import com.zy.md.data.model.GankMeiziFragmentModel;
import com.zy.md.data.net.GankApi;
import com.zy.md.ui.fragment.GankMeiziFragment;
import com.zy.md.ui.presenter.GankMeiziFragmentPresenter;

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
    public  GankMeiziFragmentModel provideGankMeiziFragmentModel(GankMeiziFragment fragment, GankApi gankApi){
        return new GankMeiziFragmentModel( fragment, gankApi );
    }

    @Provides
    @FragmentScope
    public GankMeiziFragmentPresenter provideGankMeiziFragmentPresenter(GankMeiziFragment fragment, GankMeiziFragmentModel model){
        return new GankMeiziFragmentPresenter(fragment, model);
    }


}

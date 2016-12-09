package com.zy.md.ui.di;

import com.zy.md.base.dagger.FragmentScope;
import com.zy.md.ui.fragment.GankFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Simon on 2016/12/9.
 */

@Module
public class GankFragmentModule {
    GankFragment mFragment;

    public GankFragmentModule(GankFragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @FragmentScope
    public GankFragment provideGankFragment(){
        return mFragment;
    }

}

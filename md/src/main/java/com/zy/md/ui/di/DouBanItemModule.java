package com.zy.md.ui.di;

import com.zy.md.base.dagger.FragmentScope;
import com.zy.md.ui.fragment.DouBanItemFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Simon on 2016/12/6.
 */

@Module
public class DouBanItemModule {
    DouBanItemFragment mFragment;

    public DouBanItemModule(DouBanItemFragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @FragmentScope
    public DouBanItemFragment provideDouBanItemFragment(){
        return mFragment;
    }
}

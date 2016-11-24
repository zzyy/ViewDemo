package com.zy.md.ui.di;

import com.zy.md.base.dagger.FragmentScope;
import com.zy.md.ui.fragment.GankMeiziFragment;

import dagger.Subcomponent;

/**
 * Created by Simon on 2016/11/24.
 */


@FragmentScope
@Subcomponent(modules = GanMeiziModule.class)
public interface GankMeiziFragmentComponent {

    void inject(GankMeiziFragment fragment);

}

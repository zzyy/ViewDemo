package com.zy.md.ui.di;

import com.zy.md.base.AppComponent;
import com.zy.md.base.dagger.FragmentScope;
import com.zy.md.ui.fragment.DouBanItemFragment;

import dagger.Component;

/**
 * Created by Simon on 2016/12/6.
 */


@FragmentScope
@Component(dependencies = AppComponent.class,
modules = DouBanItemModule.class)
public interface DouBanItemFragmentComponent {

    void inject(DouBanItemFragment fragment);
}

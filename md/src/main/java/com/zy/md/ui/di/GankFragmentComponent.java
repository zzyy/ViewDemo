package com.zy.md.ui.di;

import com.zy.md.base.AppComponent;
import com.zy.md.base.dagger.FragmentScope;

import dagger.Component;

/**
 * Created by Simon on 2016/12/9.
 */

@FragmentScope
@Component(dependencies = AppComponent.class)
public interface GankFragmentComponent {


}

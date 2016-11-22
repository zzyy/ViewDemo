package com.zy.md.ui.component;

import com.zy.md.MainActivity;
import com.zy.md.base.dagger.ActivityScope;
import com.zy.md.base.dagger.AppComponent;

import dagger.Component;

/**
 * Created by Simon on 2016/11/22.
 */

@ActivityScope
@Component(dependencies = AppComponent.class)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);

}

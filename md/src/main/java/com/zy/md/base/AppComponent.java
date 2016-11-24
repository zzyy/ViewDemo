package com.zy.md.base;

import android.app.Application;

import com.zy.md.data.net.GankApi;
import com.zy.md.data.net.ServerModule;
import com.zy.md.ui.di.GanMeiziModule;
import com.zy.md.ui.di.GankMeiziFragmentComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Simon on 2016/11/22.
 */
@Singleton
@Component(modules = {AppModule.class, ServerModule.class})
public interface AppComponent {

    Application getApplication();

    GankApi getGankApi();

    GankMeiziFragmentComponent plus(GanMeiziModule module);
}

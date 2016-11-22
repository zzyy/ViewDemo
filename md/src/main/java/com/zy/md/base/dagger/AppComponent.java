package com.zy.md.base.dagger;

import android.app.Application;

import com.zy.md.base.net.GankApi;

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
}

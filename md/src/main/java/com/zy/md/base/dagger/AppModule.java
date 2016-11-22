package com.zy.md.base.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Simon on 2016/11/22.
 */

@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }



    @Provides
    @Singleton
    public Application provideApplication() {
        return mApplication;
    }


}

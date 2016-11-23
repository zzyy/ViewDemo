package com.zy.md.utils.common;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Simon on 2016/11/23.
 */

@Module
public class CommonUtilsModule {


    @Singleton
    @Provides
    public ToastUtils provideToastUtils(Context context){
        return ToastUtils.getInstance( context );
    }

    @Singleton
    @Provides
    public PreferenceUtils providePreferenceUtils(Context context){
        return PreferenceUtils.with( context );
    }
}

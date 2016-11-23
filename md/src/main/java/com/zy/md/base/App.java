package com.zy.md.base;

import android.app.Application;

import com.orhanobut.logger.Logger;
import com.zy.md.base.dagger.DaggerAppComponent;
import com.zy.md.utils.common.ThreadInfoUtils;

/**
 * Created by Simon on 2016/11/2.
 */

public class App extends Application {
    private static App sContext;

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        init();
    }

    private void init() {
        initLog();

        if (ThreadInfoUtils.getProcessName(this).equals(getPackageName())) {
            initAppComponent();
        }
    }

    private void initAppComponent() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    private void initLog() {
        Logger.init("zy")
                .hideThreadInfo();
    }


    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static App getContext() {
        return sContext;
    }
}

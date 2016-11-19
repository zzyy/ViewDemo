package com.zy.md;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.Logger;
import com.zy.md.base.utils.ThreadInfoUtils;

/**
 * Created by Simon on 2016/11/2.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        init();
    }

    private void init() {
        initLog();

        Logger.i(ThreadInfoUtils.getProcessName(this));
        if (ThreadInfoUtils.getProcessName(this).equals(getPackageName())) {

        }
    }

    private void initLog() {
        Logger.init("zy")
                .hideThreadInfo();
    }

    private static Context mContext;
    public static Context getContext(){
        return mContext;
    }
}

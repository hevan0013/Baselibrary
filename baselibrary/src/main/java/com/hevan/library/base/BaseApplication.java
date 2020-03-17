package com.hevan.library.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by huangwx on 2016/6/15.
 */
public abstract class BaseApplication extends Application {

    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;
    }

    public static Context getAppContext() {
        return mAppContext;
    }
}

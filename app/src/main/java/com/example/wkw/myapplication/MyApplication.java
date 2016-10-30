package com.example.wkw.myapplication;

import android.app.Application;

import com.thinkland.sdk.android.JuheSDKInitializer;

/**
 * Created by wkw on 2016/10/30.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        JuheSDKInitializer.initialize(getApplicationContext());
    }
}

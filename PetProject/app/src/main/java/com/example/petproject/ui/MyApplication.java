package com.example.petproject.ui;

import android.app.Application;

import com.tencent.mmkv.MMKV;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MMKV.initialize(this);
    }
}

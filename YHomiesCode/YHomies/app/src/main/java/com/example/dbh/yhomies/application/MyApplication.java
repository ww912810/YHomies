package com.example.dbh.yhomies.application;


import org.xutils.x;

public class MyApplication extends android.app.Application {

    public static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        x.Ext.init(this);
    }

}

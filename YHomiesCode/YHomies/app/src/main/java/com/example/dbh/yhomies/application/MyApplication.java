package com.example.dbh.yhomies.application;


public class MyApplication extends android.app.Application {

    public static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}

package com.example.dbh.yhomies.utils;

import org.xutils.common.Callback;

/**
 * 网络请求的回调方法的重写的类 子类
 * Created by Administrator on 2017/1/16.
 */
public class MyCallBack implements Callback.CommonCallback<String> {
    @Override
    public void onSuccess(String result) {

    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {

    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
}

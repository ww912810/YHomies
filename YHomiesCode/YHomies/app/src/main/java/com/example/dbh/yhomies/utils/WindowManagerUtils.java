package com.example.dbh.yhomies.utils;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

/**
 * WindowManager工具类
 * @author 段博涵
 */
public class WindowManagerUtils {

    /**
     * 设置全屏界面
     * @param mActivity
     */
    public static void SetFullscreenWindow(Activity mActivity){
        Window window = mActivity.getWindow();
        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
    }

}

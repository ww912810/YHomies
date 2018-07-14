package com.example.dbh.yhomies.view.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.utils.manager.AppManager;

/**
 * 用户第一次安装进入app欢迎页/引导页
 * @author 段博涵
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 添加Activity到堆栈
        AppManager.getAppManager().finishActivity(this);
    }
}

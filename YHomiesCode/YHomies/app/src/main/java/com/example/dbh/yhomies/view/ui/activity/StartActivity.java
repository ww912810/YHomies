package com.example.dbh.yhomies.view.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.utils.WindowManagerUtils;
import com.example.dbh.yhomies.utils.manager.AppManager;

/**
 * app启动页
 *
 * @author 段博涵
 */
public class StartActivity extends AppCompatActivity {

    private Context mContext;
    private Handler mHandler;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManagerUtils.SetFullscreenWindow(this);
        setContentView(R.layout.activity_start);
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        mContext = this;

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        startActivity(new Intent(mContext, MainActivity.class));
                        finish();
                        break;
                }
            }
        };
        startMainActivity();
    }

    private void startMainActivity() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    mHandler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
    }
}

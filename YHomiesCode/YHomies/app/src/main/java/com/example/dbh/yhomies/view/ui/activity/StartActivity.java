package com.example.dbh.yhomies.view.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dbh.yhomies.MainActivity;
import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.utils.WindowManagerUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * app启动页
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
        //在内容视图设置后创建我们的管理器实例
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // 设置自定义状态栏颜色
        tintManager.setTintColor(Color.parseColor("#99ffffff"));

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 0:
                            startActivity(new Intent(mContext,MainActivity.class));
                            finish();
                        break;
                }
            }
        };
        startMainActivity();
    }

    private void startMainActivity(){
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

}

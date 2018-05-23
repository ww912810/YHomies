package com.example.dbh.yhomies.view.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.utils.WindowManagerUtils;

/**
 * app启动页
 * @author 段博涵
 */
public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManagerUtils.SetFullscreenWindow(this);
        setContentView(R.layout.activity_start);
    }
}

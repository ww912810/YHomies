package com.example.dbh.yhomies.view.ui.activity;

import android.content.Context;
import android.os.Bundle;

import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.view.ui.base_view.BlackBaseActivity;
import com.example.dbh.yhomies.view.ui.base_view.WhiteBaseActivity;

public class MainActivity extends WhiteBaseActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

}

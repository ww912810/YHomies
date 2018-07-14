package com.example.dbh.yhomies.view.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dawn.dawnsutils.ToastUtils;
import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.utils.glide.GlideCatchUtil;
import com.example.dbh.yhomies.view.base_view.WhiteBaseActivity;

public class SettingActivity extends WhiteBaseActivity {

    private Context mContext;

    private ImageView ivLeftImage;

    private TextView tvFeedBack, tvAbout, tvCacheNumber, tvCheckUpdate;
    private LinearLayoutCompat llcClearCache;
    private Handler mHandler;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mContext = this;
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 0:
                        tvCacheNumber.setText(GlideCatchUtil.getInstance().getCacheSize());
                        break;
                }
            }
        };
    }

    @Override
    protected void initView() {
        ivLeftImage = findViewById(R.id.ivLeftImage);
        ImageView ivRightImage = findViewById(R.id.ivRightImage);
        ivRightImage.setVisibility(View.GONE);
        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("设置");

        tvFeedBack = findViewById(R.id.tvFeedBack);
        tvAbout = findViewById(R.id.tvAbout);
        tvCacheNumber = findViewById(R.id.tvCacheNumber);
        tvCheckUpdate = findViewById(R.id.tvCheckUpdate);
        llcClearCache = findViewById(R.id.llcClearCache);

        tvCacheNumber.setText(GlideCatchUtil.getInstance().getCacheSize());
    }

    @Override
    protected void initListener() {
        //返回按钮点击
        ivLeftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //关于点击
        tvAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, AboutActivity.class));
            }
        });

        llcClearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GlideCatchUtil.getInstance().clearCacheMemory()) {
                    ToastUtils.showSafeShortToast(mContext,"清除内存缓存成功!");
                } else {
                    ToastUtils.showSafeShortToast(mContext,"清除内存缓存失败!");
                }
                if (GlideCatchUtil.getInstance().clearCacheDiskSelf()) {
                    ToastUtils.showSafeShortToast(mContext,"清除磁盘缓存成功!");
                } else {
                    ToastUtils.showSafeShortToast(mContext,"清除磁盘缓存失败!");
                }
                mHandler.sendEmptyMessage(0);
            }
        });
    }
}

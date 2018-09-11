package com.example.dbh.yhomies.view.ui.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.view.adapter.MainFragmentsAdapter;
import com.example.dbh.yhomies.view.base_view.WhiteBaseActivity;
import com.example.dbh.yhomies.view.ui.fragment.ChatFragment;
import com.example.dbh.yhomies.view.ui.fragment.NoticeFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

public class MessageActivity extends WhiteBaseActivity {

    private Context mContext;

    private ImageView ivBack;
    private SlidingTabLayout tabLayoutMessage;
    private ViewPager vpMessage;
    private String tabTitle[] = new String[]{"聊天", "通知"};
    private ChatFragment chatFragment;
    private NoticeFragment noticeFragment;
    private ArrayList<Fragment> fragments;
    private MainFragmentsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        mContext = this;
        initFragment();
    }

    @Override
    protected void initView() {
        ivBack = findViewById(R.id.ivBack);
        tabLayoutMessage = findViewById(R.id.tabLayoutMessage);
        vpMessage = findViewById(R.id.vpMessage);
    }

    @Override
    protected void initListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        chatFragment = new ChatFragment();
        noticeFragment = new NoticeFragment();
        fragments.add(chatFragment);
        fragments.add(noticeFragment);
        adapter = new MainFragmentsAdapter(getSupportFragmentManager(), fragments);
        vpMessage.setAdapter(adapter);
        vpMessage.setCurrentItem(0);
        tabLayoutMessage.setViewPager(vpMessage, tabTitle);
    }
}

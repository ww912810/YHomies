package com.example.dbh.yhomies.view.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.view.adapter.MainFragmentsAdapter;
import com.example.dbh.yhomies.view.ui.activity.MessageActivity;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    private Context mContext;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chat, null);
        mContext = getActivity();

        initView();
        initFragment();
        initListener();

        return view;
    }

    /**
     * 初始化视图
     */
    private void initView() {

    }

    private void initListener() {

    }

    /**
     * 初始化碎片
     */
    private void initFragment() {

    }

}

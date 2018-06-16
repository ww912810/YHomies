package com.example.dbh.yhomies.view.ui.fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dbh.yhomies.R;

/**
 * 首页
 *
 * @author 段博涵
 */
public class HomeFragment extends Fragment {

    private Context mContext;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        mContext = getActivity();
        return view;
    }
}

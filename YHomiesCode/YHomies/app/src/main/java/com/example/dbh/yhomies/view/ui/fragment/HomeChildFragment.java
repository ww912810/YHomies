package com.example.dbh.yhomies.view.ui.fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dbh.yhomies.R;

public class HomeChildFragment extends Fragment {

    private Context mContext;
    private View view;

    private TextView tvTest;
    private String checkTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_child, null);
        mContext = getActivity();

        Bundle bundle = getArguments();
        checkTitle = bundle.getString("checkTitle");
        initView();

        return view;
    }

    private void initView() {
        tvTest = view.findViewById(R.id.tvTest);
        tvTest.setText(checkTitle);
    }

}

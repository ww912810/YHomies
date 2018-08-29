package com.example.dbh.yhomies.view.ui.fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.mode.Bean.PostBean;
import com.example.dbh.yhomies.view.adapter.OriginalChosenAdapter;
import com.example.dbh.yhomies.view.adapter.OriginalNewAdapter;

import java.util.ArrayList;
import java.util.List;

public class OriginalFragment extends Fragment {

    private Context mContext;
    private View view;

    private RecyclerView rvChosenOriginal,rvNewOriginal;
    private OriginalChosenAdapter originalChosenAdapter;
    private OriginalNewAdapter originalNewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_original, null);
        mContext = getActivity();

        initView();
        initFragment();

        return view;
    }

    private void initView() {
        rvChosenOriginal = view.findViewById(R.id.rvChosenOriginal);
        originalChosenAdapter = new OriginalChosenAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvChosenOriginal.setLayoutManager(manager);
        ArrayList<PostBean> postBeans = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PostBean bean = new PostBean();
            postBeans.add(bean);
        }
        originalChosenAdapter.setNewData(postBeans);
        rvChosenOriginal.setAdapter(originalChosenAdapter);

        rvNewOriginal = view.findViewById(R.id.rvNewOriginal);
        originalNewAdapter = new OriginalNewAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,2);
        rvNewOriginal.setLayoutManager(gridLayoutManager);
        ArrayList<PostBean> postBeans1 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            PostBean bean = new PostBean();
            postBeans1.add(bean);
        }
        originalNewAdapter.setNewData(postBeans1);
        rvNewOriginal.setAdapter(originalNewAdapter);
    }

    private void initFragment() {

    }

}

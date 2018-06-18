package com.example.dbh.yhomies.view.ui.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.view.adapter.MainFragmentsAdapter;
import com.example.dbh.yhomies.view.ui.customize_view.NoScrollViewPager;
import com.flyco.tablayout.SlidingTabLayout;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;

/**
 * 我的
 *
 * @author 段博涵
 */
public class MyFragment extends Fragment {

    private Context mContext;
    private View view;

    private String [] tabTitle = new String[]{"帖子","收藏"};
    private SlidingTabLayout tabLayoutMy;
    private NoScrollViewPager nsMyViewPage;
    private MainFragmentsAdapter adapter;
    private ArrayList<Fragment> fragments;
    private HomeChildFragment homeChildFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, null);
        mContext = getActivity();

        initView();
        initFragment();

        return view;
    }

    /**
     * 初始化视图
     */
    private void initView(){
        tabLayoutMy = view.findViewById(R.id.tabLayoutMy);
        nsMyViewPage = view.findViewById(R.id.nsMyViewPage);
    }

    /**
     * 初始化碎片
     */
    private void initFragment(){
        fragments = new ArrayList<>();
        adapter = new MainFragmentsAdapter(getChildFragmentManager(),fragments);
        for (int i = 0; i < tabTitle.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putString("checkTitle",tabTitle[i]);
            homeChildFragment = new HomeChildFragment();
            homeChildFragment.setArguments(bundle);
            fragments.add(homeChildFragment);
        }
        nsMyViewPage.setAdapter(adapter);
        nsMyViewPage.setCurrentItem(0);
        tabLayoutMy.setViewPager(nsMyViewPage,tabTitle);
    }

}
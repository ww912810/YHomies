package com.example.dbh.yhomies.view.ui.fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.view.adapter.MainFragmentsAdapter;
import com.example.dbh.yhomies.view.customize_view.NoScrollViewPager;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

/**
 * 首页
 *
 * @author 段博涵
 */
public class HomeFragment extends Fragment {

    private Context mContext;
    private View view;

    private MainFragmentsAdapter adapter;
    private ArrayList<Fragment> fragments;
    private HomeChildFragment homeChildFragment;
    private OriginalFragment originalFragment;
    private NoScrollViewPager nsViewPage;
    private String tabTitle[] = new String[]{"推荐", "关注", "附近", "原创"};

    private SlidingTabLayout tabLayoutPosts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        mContext = getActivity();

        initView();
        initFragment();

        return view;
    }

    /**
     * 初始化视图
     */
    private void initView() {
        nsViewPage = view.findViewById(R.id.nsViewPage);
        tabLayoutPosts = view.findViewById(R.id.tabLayoutPosts);
    }

    /**
     * 初始化碎片
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        for (int i = 0; i < tabTitle.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putString("checkTitle", tabTitle[i]);
            if (i == 3) {
                originalFragment = new OriginalFragment();
                fragments.add(originalFragment);
            } else {
                homeChildFragment = new HomeChildFragment();
                homeChildFragment.setArguments(bundle);
                fragments.add(homeChildFragment);
            }
        }
        adapter = new MainFragmentsAdapter(getChildFragmentManager(), fragments);
        nsViewPage.setAdapter(adapter);
        nsViewPage.setCurrentItem(0);
        tabLayoutPosts.setViewPager(nsViewPage, tabTitle);
    }

}

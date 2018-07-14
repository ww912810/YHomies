package com.example.dbh.yhomies.view.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.mode.Bean.VariableStorageBean;
import com.example.dbh.yhomies.view.adapter.MainFragmentsAdapter;
import com.example.dbh.yhomies.view.ui.activity.LoginAndRegisteredActivity;
import com.example.dbh.yhomies.view.ui.activity.SettingActivity;
import com.example.dbh.yhomies.view.customize_view.NoScrollViewPager;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 我的
 *
 * @author 段博涵
 */
public class MyFragment extends Fragment {

    private Context mContext;
    private View view;
    private SharedPreferences spf; //存储文件对象
    private SharedPreferences.Editor editor;

    private String[] tabTitle = new String[]{"帖子", "收藏"};
    private SlidingTabLayout tabLayoutMy;
    private NoScrollViewPager nsMyViewPage;
    private MainFragmentsAdapter adapter;
    private ArrayList<Fragment> fragments;
    private HomeChildFragment homeChildFragment;

    private String userName;
    private String userSex;
    private String userCity;
    private String userSignature;
    private String userLogo;
    private String userBackgroundUrl;

    private ImageView ivSetting;
    private TextView tvUserName, tvUserCity, tvUserSignature;
    private ImageView ivUserSex;
    private CircleImageView civUserLogo;
    private ImageView ivUserBackground;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, null);
        mContext = getActivity();
        spf = mContext.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        editor = spf.edit();

        initView();
        initFragment();
        initListener();

        return view;
    }

    /**
     * 初始化视图
     */
    private void initView() {
        tabLayoutMy = view.findViewById(R.id.tabLayoutMy);
        nsMyViewPage = view.findViewById(R.id.nsMyViewPage);

        ivSetting = view.findViewById(R.id.ivSetting);
        ivUserBackground = view.findViewById(R.id.ivUserBackground);
        tvUserName = view.findViewById(R.id.tvUserName);
        tvUserCity = view.findViewById(R.id.tvUserCity);
        tvUserSignature = view.findViewById(R.id.tvUserSignature);
        ivUserSex = view.findViewById(R.id.ivUserSex);
        civUserLogo = view.findViewById(R.id.civUserLogo);

        userName = spf.getString("userName", "未登录");
        userSex = spf.getString("userSex", "");
        userCity = spf.getString("userCity", "");
        userSignature = spf.getString("userSignature", "");
        userLogo = spf.getString("userLogo", "");
        userBackgroundUrl = spf.getString("userBackgroundUrl", "");
        tvUserName.setText(userName);
        if (userSex.equals("0")) {
            ivUserSex.setImageResource(R.mipmap.ic_girl);
        } else {
            ivUserSex.setImageResource(R.mipmap.ic_boy);
        }
        tvUserCity.setText(userCity);
        tvUserSignature.setText(userSignature);
        Glide.with(mContext).load(userLogo).into(civUserLogo);
        Glide.with(mContext).load(userBackgroundUrl).into(ivUserBackground);
    }

    /**
     * 初始化碎片
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        adapter = new MainFragmentsAdapter(getChildFragmentManager(), fragments);
        for (int i = 0; i < tabTitle.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putString("checkTitle", tabTitle[i]);
            homeChildFragment = new HomeChildFragment();
            homeChildFragment.setArguments(bundle);
            fragments.add(homeChildFragment);
        }
        nsMyViewPage.setAdapter(adapter);
        nsMyViewPage.setCurrentItem(0);
        tabLayoutMy.setViewPager(nsMyViewPage, tabTitle);
    }

    private void initListener() {
        ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, SettingActivity.class));
            }
        });
        tvUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, LoginAndRegisteredActivity.class));
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (spf != null) {
                boolean isLoggedIn = spf.getBoolean("isLoggedIn", false);
                if (isLoggedIn) {
                    userName = spf.getString("userName", "未登录");
                    userSex = spf.getString("userSex", "");
                    userCity = spf.getString("userCity", "");
                    userSignature = spf.getString("userSignature", "");
                    userLogo = spf.getString("userLogo", "");
                    userBackgroundUrl = spf.getString("userBackgroundUrl", "");

                    tvUserName.setText(userName);
                    if (userSex.equals("0")) {
                        ivUserSex.setImageResource(R.mipmap.ic_girl);
                    } else {
                        ivUserSex.setImageResource(R.mipmap.ic_boy);
                    }
                    tvUserCity.setText(userCity);
                    tvUserSignature.setText(userSignature);
                    Glide.with(mContext).load(userLogo).into(civUserLogo);
                    Glide.with(mContext).load(userBackgroundUrl).into(ivUserBackground);
                    editor.putBoolean("isLoggedIn", true);
                    editor.commit();
                } else {
                    Log.d(VariableStorageBean.LOG_TAG, "" + isLoggedIn);
                    startActivity(new Intent(mContext, LoginAndRegisteredActivity.class));
                }
            }

        }
    }

}

package com.example.dbh.yhomies.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by 涵涵 on 2017/1/10.
 */
public class MainFragmentsAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragList;
    FragmentManager fm;

    public MainFragmentsAdapter(FragmentManager fm, ArrayList<Fragment> fragList) {
        super(fm);
        this.fragList = fragList;
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }

    @Override
    public int getCount() {
        return fragList == null ? 0 : fragList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fm.beginTransaction().show(fragment).commit();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = fragList.get(position);
        fm.beginTransaction().hide(fragment).commit();
    }

}

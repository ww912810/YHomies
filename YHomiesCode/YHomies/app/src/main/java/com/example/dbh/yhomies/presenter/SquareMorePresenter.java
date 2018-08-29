package com.example.dbh.yhomies.presenter;

import com.example.dbh.yhomies.mode.Bean.UserBean;
import com.example.dbh.yhomies.mode.SquareMoreUserMode;
import com.example.dbh.yhomies.mode.SquareUserMode;
import com.example.dbh.yhomies.mode.m_interface.ISquarePopularityMode;
import com.example.dbh.yhomies.view.v_interface.ISquareMoreView;
import com.example.dbh.yhomies.view.v_interface.ISquareView;

import java.util.ArrayList;

public class SquareMorePresenter {

    private ISquareMoreView iSquareMoreView;

    public SquareMorePresenter(ISquareMoreView iSquareMoreView) {
        this.iSquareMoreView = iSquareMoreView;
    }

    /**
     * 获取广场二级界面更多人气列表数据
     * @param pageNo
     * @param countPage
     * @param uid
     * @param city
     * @param filer
     */
    public void getMorePopularity(int pageNo, int countPage, String uid, String city, String filer) {
        SquareMoreUserMode.getSquareMorePopularity(new ISquarePopularityMode() {
            @Override
            public void onSuccess(ArrayList<UserBean> data) {
                iSquareMoreView.getSquareMorePopularityData(data);
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onError() {

            }
        }, pageNo, countPage, uid, city, filer);
    }

    /**
     * 释放引用，防止内存泄露
     */
    public void destroy() {
        iSquareMoreView = null;
    }

}

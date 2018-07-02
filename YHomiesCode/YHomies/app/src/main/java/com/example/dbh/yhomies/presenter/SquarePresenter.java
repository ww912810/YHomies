package com.example.dbh.yhomies.presenter;

import com.example.dbh.yhomies.mode.Bean.UserBean;
import com.example.dbh.yhomies.mode.SquareMoreUserMode;
import com.example.dbh.yhomies.mode.SquareUserMode;
import com.example.dbh.yhomies.mode.m_interface.ISquarePopularityMode;
import com.example.dbh.yhomies.view.ui.activity.MorePopularityActivity;
import com.example.dbh.yhomies.view.v_interface.ISquareMoreView;
import com.example.dbh.yhomies.view.v_interface.ISquareView;

import java.util.ArrayList;

public class SquarePresenter {

    private ISquareView iSquareView;
    private ISquareMoreView iSquareMoreView;

    public SquarePresenter(ISquareView iSquareView) {
        this.iSquareView = iSquareView;
    }

    /**
     * 获取广场主页人气数据
     */
    public void getPopularityData() {
        SquareUserMode.getSquarePopularity(new ISquarePopularityMode() {
            @Override
            public void onSuccess(ArrayList<UserBean> data) {
                iSquareView.getSquarePopularityData(data);
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onError() {

            }
        });
    }

    /**
     * 获取广场主页附近人数据
     */
    public void getNearbyData() {
        SquareUserMode.getSquareNearby(new ISquarePopularityMode() {
            @Override
            public void onSuccess(ArrayList<UserBean> data) {
                iSquareView.getSquareNearbyData(data);
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onError() {

            }
        });
    }

    /**
     * 获取广场二级界面更多附近人数据
     *
     * @param pageNo
     * @param pageSize
     */
    public void getMoreNearbyData(String pageNo, String pageSize) {
        SquareMoreUserMode.getSquareNearby(new ISquarePopularityMode() {
            @Override
            public void onSuccess(ArrayList<UserBean> data) {
                iSquareView.getSquareNearbyData(data);
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onError() {

            }
        }, pageNo, pageSize);
    }

    /**
     * 获取广场二级界面更多人气-入驻用户数据
     */
    public void getMoreSettleIn() {
        SquareMoreUserMode.getSquareMoreSettleIn(new ISquarePopularityMode() {
            @Override
            public void onSuccess(ArrayList<UserBean> data) {
                iSquareView.getSquarePopularityData(data);
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onError() {

            }
        });
    }

}

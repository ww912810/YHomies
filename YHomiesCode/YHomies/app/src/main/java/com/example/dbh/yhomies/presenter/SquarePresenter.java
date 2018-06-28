package com.example.dbh.yhomies.presenter;

import com.example.dbh.yhomies.mode.Bean.UserBean;
import com.example.dbh.yhomies.mode.SquareMoreUserMode;
import com.example.dbh.yhomies.mode.SquareUserMode;
import com.example.dbh.yhomies.mode.m_interface.ISquarePopularityMode;
import com.example.dbh.yhomies.view.v_interface.ISquareView;

import java.util.ArrayList;

public class SquarePresenter {

    private ISquareView iSquareView;

    public SquarePresenter(ISquareView iSquareView){
        this.iSquareView = iSquareView;
    }

    public void getPopularityData(){
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

    public void getNearbyData(){
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

    public void getMoreNearbyData(String pageNo,String pageSize){
        SquareMoreUserMode.getSquareNearby(new ISquarePopularityMode(){
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
        },pageNo,pageSize);
    }

}

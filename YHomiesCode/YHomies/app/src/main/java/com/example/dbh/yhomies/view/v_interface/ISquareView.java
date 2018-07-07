package com.example.dbh.yhomies.view.v_interface;

import com.example.dbh.yhomies.mode.Bean.UserBean;

import java.util.List;

public interface ISquareView {

    /**
     * 调用此接口显示人气数据
     * @param list
     */
    void getSquarePopularityData(List<UserBean> list);

    /**
     * 调用此接口显示附近数据
     * @param data
     */
    void getSquareNearbyData(List<UserBean> data);

}

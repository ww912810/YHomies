package com.example.dbh.yhomies.view.v_interface;

import com.example.dbh.yhomies.mode.Bean.UserBean;

import java.util.List;

public interface ISquareMoreView {

    /**
     * 调用此接口显示更多人气数据
     * @param list
     */
    void getSquareMorePopularityData(List<UserBean> list);


}

package com.example.dbh.yhomies.mode.m_interface;

import com.example.dbh.yhomies.mode.Bean.FieldBean;

import java.util.ArrayList;

public interface ISquareFieldMode {

    /**
     * 数据请求成功
     * @param data 请求到的数据
     */
    void onSuccess(ArrayList<FieldBean> data);
}

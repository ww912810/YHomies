package com.example.dbh.yhomies.mode.m_interface;

import com.example.dbh.yhomies.mode.Bean.AudioFileBean;

import java.util.ArrayList;

public interface IGetAudioMode {

    /**
     * 数据请求成功
     *
     * @param beans 请求到的数据
     */
    void onSuccess(ArrayList<AudioFileBean> beans);

//    /**
//     * 请求数据失败，指在请求网络API接口请求方式时，出现无法联网、
//     * 缺少权限，内存泄露等原因导致无法连接到请求数据源。
//     */
//    void onError();

}

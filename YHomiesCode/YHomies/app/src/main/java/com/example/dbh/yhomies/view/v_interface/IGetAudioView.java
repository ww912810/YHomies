package com.example.dbh.yhomies.view.v_interface;

import com.example.dbh.yhomies.mode.Bean.AudioFileBean;

import java.util.ArrayList;

public interface IGetAudioView {

    /**
     * 获取验证码成功
     *
     * @param beans 数据集合
     */
    void getAudioOnSuccess(ArrayList<AudioFileBean> beans);

    void getAudioOnError();

}

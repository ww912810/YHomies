package com.example.dbh.yhomies.view.v_interface;

import com.example.dbh.yhomies.mode.Bean.UserBean;

public interface IPasswordLoginView {


    /**
     * 密码登陆
     *
     * @param userBean 用户信息
     */
    void passwordLoginOnSuccess(UserBean userBean);

    /**
     * 使用网络API接口请求方式时，虽然已经请求成功但是由
     * 于{@code msg}的原因无法正常返回数据。
     */
    void passwordLoginOnFailure(String msg);

    /**
     * 请求数据失败，指在请求网络API接口请求方式时，出现无法联网、
     * 缺少权限，内存泄露等原因导致无法连接到请求数据源。
     */
    void passwordLoginOnError();

}

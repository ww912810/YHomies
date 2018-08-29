package com.example.dbh.yhomies.mode.m_interface;

public interface IRegisterMode {

    /**
     * 请求中
     */
    void onLoading();

    /**
     * 数据请求成功
     * @param userPhone 手机号
     * @param userPwd   密码
     */
    void onSuccess(String userPhone,String userPwd);

    /**
     * 使用网络API接口请求方式时，虽然已经请求成功但是由
     * 于{@code msg}的原因无法正常返回数据。
     */
    void onFailure(String msg);

    /**
     * 请求数据失败，指在请求网络API接口请求方式时，出现无法联网、
     * 缺少权限，内存泄露等原因导致无法连接到请求数据源。
     */
    void onError();

}

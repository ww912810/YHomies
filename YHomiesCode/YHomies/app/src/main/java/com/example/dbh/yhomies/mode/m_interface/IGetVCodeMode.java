package com.example.dbh.yhomies.mode.m_interface;


/**
 * 获取验证码mode层接口
 */
public interface IGetVCodeMode {

    /**
     * 数据请求成功
     *
     * @param vCodeValue 请求到的数据-验证码
     */
    void onSuccess(String vCodeValue);

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
